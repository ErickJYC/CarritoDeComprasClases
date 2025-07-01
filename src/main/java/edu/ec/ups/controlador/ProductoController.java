package edu.ec.ups.controlador;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.vista.ProductoAnadirView;
import edu.ec.ups.vista.ProductoEliminarView;
import edu.ec.ups.vista.ProductoListaView;
import edu.ec.ups.vista.ProductoModificarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoModificarView productoModificarView;
    private final ProductoEliminarView productoEliminarView;
    //private final CarritoAnadirView carritoAnadirView;

    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoModificarView productoModificarView,
                              ProductoEliminarView productoEliminarView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoModificarView = productoModificarView;
        this.productoEliminarView = productoEliminarView;
        //this.carritoAnadirView = carritoAnadirView;
        this.configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigoEliminar();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoListar();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });


        productoModificarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigoActualizar();
            }
        });

        productoModificarView.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarValoresActualizar();
            }
        });

        productoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProdutos(productoDAO.listarTodos());
    }

    private void buscarProductoListar() {
        int codigo = Integer.parseInt(productoListaView.getTxtBuscar().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            productoListaView.mostrarMensaje("No se encontro el producto");
        } else {
            productoListaView.cargarDatos(List.of(producto));
        }
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    public void buscarProductoPorCodigoActualizar() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigobusqueda().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            productoModificarView.mostrarMensaje("No se encontró el producto");
            productoModificarView.getTxtNombre().setText("");
            productoModificarView.getTxtPrecio().setText("");
        } else {
            productoModificarView.getTxtCodigo().setText(String.valueOf(producto.getCodigo()));
            productoModificarView.getTxtNombre().setText(producto.getNombre());
            productoModificarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));

            // 🔒 Bloquear edición al cargar
            productoModificarView.getTxtCodigo().setEnabled(false);
            productoModificarView.getTxtNombre().setEnabled(false);
            productoModificarView.getTxtPrecio().setEnabled(false);
        }

    }

    public void actualizarProducto() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        String nombre = productoModificarView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());
        boolean confirmacion = productoModificarView.confirmarEliminacion();

        if (confirmacion) {
            Producto producto = new Producto(codigo, nombre, precio);
            productoDAO.actualizar(producto);
            productoModificarView.mostrarMensaje("Producto actualizado correctamente");
            productoModificarView.limpiarCampos();
        } else {
            productoModificarView.mostrarMensaje("No se actualizó el producto");
        }

    }

    public void editarValoresActualizar() {
        productoModificarView.getTxtNombre().setEnabled(true);
    }

    private void buscarProductoPorCodigoEliminar() {
        int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            productoEliminarView.mostrarMensaje("No se encontro el producto");
        } else productoEliminarView.cargarDatos(producto);
    }

    private void eliminarProducto() {
        String textoCodigo = productoEliminarView.getTxtCodigo().getText();

        if (textoCodigo == null || textoCodigo.isBlank()) {
            productoEliminarView.mostrarMensaje("Ingrese un código antes de eliminar.");
            return;
        }

        if (!textoCodigo.matches("\\d+")) {
            productoEliminarView.mostrarMensaje("Código inválido. Use solo números.");
            return;
        }

        int codigo = Integer.parseInt(textoCodigo);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            productoEliminarView.mostrarMensaje("Producto no encontrado.");
            return;
        }

        if (productoEliminarView.confirmarEliminacion()) {
            productoDAO.eliminar(codigo);
            productoEliminarView.mostrarMensaje("Producto eliminado correctamente.");
            productoEliminarView.limpiarCampos();
            productoEliminarView.getModelo().setRowCount(0); // Limpia la tabla visual también
        } else {
            productoEliminarView.mostrarMensaje("Eliminación cancelada.");
        }

    }
}


