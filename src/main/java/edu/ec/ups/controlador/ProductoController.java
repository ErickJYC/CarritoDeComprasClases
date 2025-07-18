package edu.ec.ups.controlador;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.carritoView.CarritoAnadirView;
import edu.ec.ups.vista.productoView.ProductoActualizarView;
import edu.ec.ups.vista.productoView.ProductoAnadirView;
import edu.ec.ups.vista.productoView.ProductoEliminarView;
import edu.ec.ups.vista.productoView.ProductoListaView;

import javax.swing.*;
import java.util.List;

/**
 * Controlador encargado de gestionar todas las operaciones relacionadas con productos,
 * incluyendo creación, búsqueda, listado, actualización y eliminación.
 * Se conecta con las vistas correspondientes del módulo producto y también con el carrito.
 * Utiliza un DAO para interactuar con la base de datos y un manejador para internacionalización.
 *
 * @author Erick Yunga
 */
public class ProductoController {

    /** Vista para añadir productos */
    private final ProductoAnadirView productoAnadirView;
    /** Vista para listar productos */
    private final ProductoListaView productoListaView;
    /** Vista del carrito para añadir productos desde allí */
    private final CarritoAnadirView carritoAnadirView;
    /** Vista para eliminar productos */
    private final ProductoEliminarView productoEliminarView;
    /** Vista para actualizar productos */
    private final ProductoActualizarView productoActualizarView;
    /** DAO que gestiona operaciones CRUD de productos */
    private final ProductoDAO productoDAO;
    /** Manejador de internacionalización para los mensajes */
    private final MensajeInternacionalizacionHandler mi;

    /**
     * Constructor principal del controlador de productos. Configura vistas y eventos.
     *
     * @param productoDAO DAO que gestiona productos
     * @param productoAnadirView Vista para añadir productos
     * @param productoListaView Vista para listar productos
     * @param carritoAnadirView Vista del carrito para búsquedas de productos
     * @param productoEliminarView Vista para eliminar productos
     * @param productoActualizarView Vista para actualizar productos
     * @param mi Manejador de internacionalización
     */
    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoEliminarView productoEliminarView,
                              ProductoActualizarView productoActualizarView,
                              MensajeInternacionalizacionHandler mi) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoEliminarView = productoEliminarView;
        this.productoActualizarView = productoActualizarView;
        this.mi = mi;

        configurarEventosEnVistas();
    }

    /**
     * Configura todos los eventos (botones) de las vistas del módulo producto y carrito.
     */
    private void configurarEventosEnVistas() {
        // Añadir producto
        productoAnadirView.getBtnAceptar().addActionListener(e -> guardarProducto());

        // Buscar y listar productos
        productoListaView.getBtnBuscar().addActionListener(e -> buscarProducto());
        productoListaView.getBtnListar().addActionListener(e -> listarProductos());

        // Buscar producto desde el carrito
        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoPorCodigo());

        // Buscar y eliminar producto
        productoEliminarView.getBtnBuscar().addActionListener(e -> buscarProductoPorCodigoEliminar());
        productoEliminarView.getBtnEliminar().addActionListener(e -> eliminarProducto());

        // Buscar y actualizar producto
        productoActualizarView.getBtnBuscar().addActionListener(e -> buscarProductoPorCodigoActualizar());
        productoActualizarView.getBtnActualizar().addActionListener(e -> actualizarProducto());
    }

    /**
     * Guarda un nuevo producto en la base de datos a partir de los datos ingresados en la vista.
     * Realiza validaciones básicas antes de guardar.
     */
    private void guardarProducto() {
        try {
            String codTexto = productoAnadirView.getTxtCodigo().getText().trim();
            String nombre = productoAnadirView.getTxtNombre().getText().trim();
            String precioTexto = productoAnadirView.getTxtPrecio().getText().trim();

            if (codTexto.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty()) {
                productoAnadirView.mostrarMensaje("Todos los campos son obligatorios.");
                return;
            }

            int codigo = Integer.parseInt(codTexto);
            double precio = Double.parseDouble(precioTexto.replace(",", "."));

            // 🔎 Verificar si ya existe un producto con ese código
            if (productoDAO.buscarPorCodigo(codigo) != null) {
                productoAnadirView.mostrarMensaje("Ya existe un producto con ese código. Usa otro diferente.");
                return;
            }

            Producto nuevoProducto = new Producto();
            nuevoProducto.setCodigo(codigo);
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);

            if (nuevoProducto.getCodigo() <= 0 || nuevoProducto.getNombre() == null || nuevoProducto.getPrecio() <= 0) {
                productoAnadirView.mostrarMensaje("Corrige los campos inválidos antes de guardar.");
                return;
            }

            productoDAO.crear(nuevoProducto);
            productoAnadirView.mostrarMensaje(mi.get("producto.guardado"));
            productoAnadirView.limpiarCampos();
            productoAnadirView.mostrarProductos(productoDAO.listarTodos());

        } catch (NumberFormatException e) {
            productoAnadirView.mostrarMensaje("Código y precio deben ser números válidos.");
        }
    }

    /**
     * Busca productos por nombre y los muestra en la tabla de la vista de lista.
     */
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);

        if (productosEncontrados.isEmpty()) {
            productoListaView.mostrarMensaje("❌ No se encontró ningún producto con ese nombre.");
            productoListaView.cargarDatos(List.of()); // Limpia la tabla si no hay resultados
            return;
        }

        productoListaView.cargarDatos(productosEncontrados);
    }


    /**
     * Lista todos los productos registrados y los carga en la tabla de la vista de lista.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    /**
     * Busca un producto por código desde la vista del carrito para mostrar nombre y precio.
     */
    private void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            carritoAnadirView.mostrarMensaje(mi.get("producto.no_encontrado"));
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }
    }

    /**
     * Elimina un producto por código si existe y el usuario confirma la acción.
     */
    private void eliminarProducto() {
        String cod = productoEliminarView.getTextField1().getText().trim();

        if (cod.isEmpty()) {
            productoEliminarView.mostrarMensaje(mi.get("producto.error.codigo_vacio"));
            return;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(cod);
        } catch (NumberFormatException e) {
            productoEliminarView.mostrarMensaje(mi.get("producto.error.codigo_invalido"));
            return;
        }

        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            productoEliminarView.mostrarMensaje(mi.get("producto.no_encontrado"));
            return;
        }

        // Confirmar antes de eliminar
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar el producto con código " + codigo + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            productoDAO.eliminar(codigo);
            productoEliminarView.mostrarMensaje(mi.get("producto.eliminado"));
            productoEliminarView.limpiarCampos();
        } else {
            productoEliminarView.mostrarMensaje("Eliminación cancelada.");
        }
    }


    /**
     * Busca un producto por código desde la vista de eliminación para mostrar su información.
     */
    private void buscarProductoPorCodigoEliminar() {
        String code = productoEliminarView.getTextField1().getText();
        if (!code.isEmpty()) {
            int codigo = Integer.parseInt(code);
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                productoEliminarView.cargarDatos(List.of(producto));
            } else {
                productoEliminarView.mostrarMensaje(mi.get("producto.no_encontrado"));
                productoEliminarView.cargarDatos(List.of());
                productoEliminarView.limpiarCampos();
            }
        }
    }

    /**
     * Busca un producto por código desde la vista de actualización para mostrar su información.
     */
    private void buscarProductoPorCodigoActualizar() {
        String code = productoActualizarView.getTextField1().getText();
        if (!code.isEmpty()) {
            int codigo = Integer.parseInt(code);
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                productoActualizarView.cargarDatos(List.of(producto));
            } else {
                productoActualizarView.mostrarMensaje(mi.get("producto.no_encontrado"));
                productoActualizarView.cargarDatos(List.of());
                productoActualizarView.limpiarCampos();
            }
        }
    }

    /**
     * Actualiza los datos de un producto existente con los valores ingresados en la vista.
     */
    private void actualizarProducto() {
        String cod = productoActualizarView.getTextField1().getText();
        int codigo = Integer.parseInt(cod);
        String nombre = productoActualizarView.getTextField2().getText();
        double precio = Double.parseDouble(productoActualizarView.getTextField3().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.actualizar(producto);
        productoActualizarView.mostrarMensaje(mi.get("producto.modificado"));
    }
}
