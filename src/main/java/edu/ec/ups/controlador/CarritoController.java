package edu.ec.ups.controlador;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.carritoView.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Controlador que gestiona toda la lógica relacionada al carrito de compras:
 * agregar productos, modificar, eliminar, listar, etc.
 */
public class CarritoController {

    // DAO para manipular los carritos
    private final CarritoDAO carritoDAO;

    // DAO para acceder a productos
    private final ProductoDAO productoDAO;

    // Usuario que está usando el sistema
    private final Usuario usuario;

    // Carrito en uso (temporal)
    private final Carrito carritoActual;

    // Vistas involucradas
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoListarView carritoListarView;
    private final CarritoModificarView carritoModificarView;
    private final CarritoEliminarView carritoEliminarView;
    private CarritoDetalleView carritoDetalleView;

    // Manejador de internacionalización
    private final MensajeInternacionalizacionHandler mi;

    // Constructor del controlador
    public CarritoController(CarritoDAO carritoDAO, CarritoAnadirView carritoAnadirView,
                             ProductoDAO productoDAO, CarritoListarView carritoListarView,
                             Usuario usuario, CarritoModificarView carritoModificarView,
                             CarritoEliminarView carritoEliminarView,
                             MensajeInternacionalizacionHandler mi) {
        this.carritoDAO = carritoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoActual = new Carrito();
        this.productoDAO = productoDAO;
        this.usuario = usuario;
        this.carritoListarView = carritoListarView;
        this.carritoModificarView = carritoModificarView;
        this.carritoEliminarView = carritoEliminarView;
        this.mi = mi;

        // Configura todos los eventos (botones, acciones, etc.)
        configurarEventos();
    }

    // Asocia los botones de cada vista con sus respectivas acciones
    public void configurarEventos() {
        carritoAnadirView.getBtnAnadir().addActionListener(e -> agregarProductoAlCarrito());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        carritoAnadirView.getBtnLimpiar().addActionListener(e -> vaciarCarrito());

        carritoListarView.getBtnBuscar().addActionListener(e -> buscarCarritos());
        carritoListarView.getBtnListar().addActionListener(e -> mostrarTodosLosCarritos());
        carritoListarView.getBtnMostrardetalle().addActionListener(e -> mostrarDetalle());

        carritoModificarView.getBtnBuscar().addActionListener(e -> buscarCarritoParaModificar());
        carritoModificarView.getBtnActualizar().addActionListener(e -> modificarCarrito());
        carritoModificarView.getBtnLimpiar().addActionListener(e -> carritoModificarView.limpiarCampos());

        carritoEliminarView.getBtnBuscar().addActionListener(e -> buscarCarritoParaEliminar());
        carritoEliminarView.getBtnEliminar().addActionListener(e -> eliminarCarrito());
    }

    // Elimina un carrito por código
    private void eliminarCarrito() {
        String textoCodigo = carritoEliminarView.getTxtCodigo().getText().trim();

        if (textoCodigo.isEmpty() || !textoCodigo.chars().allMatch(Character::isDigit)) {
            carritoEliminarView.mostrarMensaje(mi.get("carrito.eliminar.error.numero"));
            return;
        }

        int codigo = Integer.parseInt(textoCodigo);
        carritoDAO.eliminar(codigo);
        carritoEliminarView.mostrarMensaje(mi.get("carrito.eliminar.exito"));
        carritoEliminarView.limpiarCampos();
    }

    // Busca un carrito para eliminar
    private void buscarCarritoParaEliminar() {
        String codigo = carritoEliminarView.getTxtCodigo().getText();
        if (!codigo.isEmpty()) {
            int codigoCarrito = Integer.parseInt(codigo);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
            if (carrito != null) {
                carritoEliminarView.cargarDatos(carrito);
                carritoEliminarView.setVisible(true);
                carritoEliminarView.moveToFront();
                carritoEliminarView.requestFocusInWindow();
            } else {
                carritoEliminarView.mostrarMensaje(mi.get("carrito.no.encontrado") + codigoCarrito);
                carritoEliminarView.limpiarCampos();
            }
        } else {
            carritoEliminarView.mostrarMensaje(mi.get("mensaje.codigo.invalido"));
        }
    }

    // Modifica la cantidad de un producto en el carrito
    private void modificarCarrito() {
        int filaSeleccionada = carritoModificarView.getTblProductos().getSelectedRow();
        if (filaSeleccionada == -1) {
            carritoModificarView.mostrarMensaje(mi.get("mensaje.seleccionar.producto"));
            return;
        }

        String textoCantidad = carritoModificarView.getCbxCantidad().getSelectedItem().toString();
        if (!textoCantidad.matches("\\d+")) {
            carritoModificarView.mostrarMensaje(mi.get("mensaje.numero.invalido"));
            return;
        }

        int cantidad = Integer.parseInt(textoCantidad);
        if (cantidad < 1 || cantidad > 20) {
            carritoModificarView.mostrarMensaje(mi.get("mensaje.cantidad.rango"));
            return;
        }

        int codigoCarrito = Integer.parseInt(carritoModificarView.getTxtCarrito().getText());
        Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);

        if (carrito == null) {
            carritoModificarView.mostrarMensaje(mi.get("carrito.no.encontrado"));
            return;
        }

        int codigoProducto = (int) carritoModificarView.getModelo().getValueAt(filaSeleccionada, 0);

        for (ItemCarrito item : carrito.obtenerItems()) {
            if (item.getProducto().getCodigo() == codigoProducto) {
                item.setCantidad(cantidad);
                break;
            }
        }

        carritoModificarView.cargarDatos(carrito);
        carritoModificarView.getTxtSubtotal().setText(String.format("%.2f", carrito.calcularTotal()));
        carritoModificarView.getTxtIva().setText(String.format("%.2f", carrito.calcularIVA()));
        carritoModificarView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotalConIVA()));
        carritoModificarView.mostrarMensaje(mi.get("mensaje.cantidad.actualizada"));
    }

    // Busca un carrito específico para modificar
    private void buscarCarritoParaModificar() {
        String codigo = carritoModificarView.getTxtCarrito().getText();
        if (!codigo.isEmpty()) {
            int codigoCarrito = Integer.parseInt(codigo);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
            if (carrito != null) {
                carritoModificarView.getTxtSubtotal().setText(String.format("%.2f", carrito.calcularTotal()));
                carritoModificarView.getTxtIva().setText(String.format("%.2f", carrito.calcularIVA()));
                carritoModificarView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotalConIVA()));
                carritoModificarView.cargarDatos(carrito);
                carritoModificarView.setVisible(true);
                carritoModificarView.moveToFront();
                carritoModificarView.requestFocusInWindow();
            } else {
                carritoModificarView.mostrarMensaje(mi.get("carrito.no.encontrado") + codigoCarrito);
                carritoModificarView.limpiarCampos();
            }
        } else {
            carritoModificarView.mostrarMensaje(mi.get("mensaje.codigo.invalido"));
        }
    }

    // Muestra los detalles de un carrito seleccionado
    private void mostrarDetalle() {
        int filaSeleccionada = carritoListarView.getTblProductos().getSelectedRow();
        if (filaSeleccionada != -1) {
            int codigoCarrito = (int) carritoListarView.getModelo().getValueAt(filaSeleccionada, 0);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
            if (carrito != null) {
                if (carritoDetalleView == null || carritoDetalleView.isClosed()) {
                    carritoDetalleView = new CarritoDetalleView(mi);
                    carritoListarView.getDesktopPane().add(carritoDetalleView);
                }

                carritoDetalleView.cargarDatos(carrito);
                carritoDetalleView.getTxtSubtotal().setText(String.format("%.2f", carrito.calcularTotal()));
                carritoDetalleView.getTxtIva().setText(String.format("%.2f", carrito.calcularIVA()));
                carritoDetalleView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotalConIVA()));

                carritoDetalleView.setVisible(true);
                carritoDetalleView.moveToFront();
                carritoDetalleView.requestFocusInWindow();
            } else {
                JOptionPane.showMessageDialog(carritoListarView, mi.get("carrito.no.encontrado"));
            }
        } else {
            JOptionPane.showMessageDialog(carritoListarView, mi.get("mensaje.seleccionar.carrito"));
        }
    }

    // Guarda el carrito actual y lo limpia
    private void guardarCarrito() {
        if (carritoActual.estaVacio()) {
            carritoAnadirView.mostrarMensaje(mi.get("carrito.vacio"));
            return;
        }

        carritoActual.setUsuario(usuario);
        carritoActual.setFechaCreacion(new GregorianCalendar());
        carritoDAO.crear(carritoActual);
        carritoAnadirView.mostrarMensaje(mi.get("carrito.guardado") + carritoActual.getCodigo());

        carritoActual.vaciarCarrito();
        agregarProductoAlCarrito();
        cargarProductos();
        carritoAnadirView.limpiarCampos();
    }

    // Agrega un producto al carrito actual
    private void agregarProductoAlCarrito() {
        int codigoProducto = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigoProducto);
        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carritoActual.agregarProducto(producto, cantidad);

        cargarProductos();
        mostrarTotal();
    }

    // Carga los productos en la tabla del carrito
    private void cargarProductos() {
        List<ItemCarrito> items = carritoActual.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad()
            });
        }
    }

    // Muestra los totales (subtotal, IVA, total)
    private void mostrarTotal() {
        carritoAnadirView.getTxtSubtotal().setText(String.format("%.2f", carritoActual.calcularTotal()));
        carritoAnadirView.getTxtIva().setText(String.format("%.2f", carritoActual.calcularIVA()));
        carritoAnadirView.getTxtTotal().setText(String.format("%.2f", carritoActual.calcularTotalConIVA()));
    }

    // Vacía el carrito actual
    public void vaciarCarrito() {
        carritoActual.vaciarCarrito();
        cargarProductos();
        mostrarTotal();
    }

    // Busca carritos por código
    public void buscarCarritos() {
        String codigo = carritoListarView.getTxtCarrito().getText();
        if (!codigo.isEmpty()) {
            int codigoCarrito = Integer.parseInt(codigo);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
            if (carrito != null) {
                carritoListarView.cargarDatos(List.of(carrito));
                carritoListarView.setVisible(true);
                carritoListarView.moveToFront();
                carritoListarView.requestFocusInWindow();
            } else {
                carritoListarView.mostrarMensaje(mi.get("carrito.no.encontrado") + codigoCarrito);
                carritoListarView.limpiarCampos();
            }
        } else {
            carritoListarView.mostrarMensaje(mi.get("mensaje.codigo.invalido"));
        }
    }

    // Muestra todos los carritos disponibles
    public void mostrarTodosLosCarritos() {
        List<Carrito> carritos = carritoDAO.listarTodos();
        if (carritos.isEmpty()) {
            carritoListarView.mostrarMensaje(mi.get("carrito.lista.vacia"));
            carritoListarView.limpiarCampos();
        } else {
            carritoListarView.cargarDatos(carritos);
        }
    }
}