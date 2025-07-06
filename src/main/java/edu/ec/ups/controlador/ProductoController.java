package edu.ec.ups.controlador;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.carritoView.CarritoAnadirView;
import edu.ec.ups.vista.productoView.ProductoActualizarView;
import edu.ec.ups.vista.productoView.ProductoAnadirView;
import edu.ec.ups.vista.productoView.ProductoEliminarView;
import edu.ec.ups.vista.productoView.ProductoListaView;

import java.util.List;

/**
 * Controlador encargado de gestionar todas las operaciones relacionadas con productos,
 * incluyendo creación, búsqueda, listado, actualización y eliminación.
 */
public class ProductoController {

    // Vistas de producto y carrito
    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoEliminarView productoEliminarView;
    private final ProductoActualizarView productoActualizarView;

    // Acceso a datos de productos
    private final ProductoDAO productoDAO;

    // Manejador de mensajes internacionalizados
    private final MensajeInternacionalizacionHandler mi;

    /**
     * Constructor principal del controlador de productos.
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
     * Método que configura los listeners para cada acción en las vistas.
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
     * Guarda un nuevo producto en la base de datos.
     */
    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje(mi.get("producto.guardado"));
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    /**
     * Busca productos por nombre y los carga en la tabla de la vista.
     */
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    /**
     * Lista todos los productos existentes en la base de datos.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    /**
     * Busca un producto por su código desde la vista del carrito y lo muestra.
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
     * Elimina un producto si su código es válido.
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

        productoDAO.eliminar(codigo);
        productoEliminarView.mostrarMensaje(mi.get("producto.eliminado"));
    }

    /**
     * Busca un producto para eliminarlo y muestra su información.
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
     * Busca un producto para actualizarlo y muestra sus datos.
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
     * Actualiza los datos de un producto existente.
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
