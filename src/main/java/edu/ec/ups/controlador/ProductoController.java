package edu.ec.ups.controlador;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.vista.ProductoAnadirView;
import edu.ec.ups.vista.ProductoListaView;
import edu.ec.ups.vista.ProductoModificarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoDAO productoDAO;
    private final ProductoModificarView productoModificarView;

    public ProductoController(ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoDAO productoDAO,
                              ProductoModificarView productoModificarView) {
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDAO = productoDAO;
        this.productoModificarView = productoModificarView;
        configureEventos();
    }
    private void configureEventos(){
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
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
                int codigo = Integer.parseInt(productoModificarView.getTxtBuscar().getText());

                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                if (productoEncontrado  != null) {
                    productoModificarView.cargarDatos(productoEncontrado);
                }else{
                    productoModificarView.mostrarMensaje("Producto no encontrado");
                }
            }
        });
        productoModificarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(Integer.parseInt(productoModificarView.getTxtBuscar().getText()));
                productoModificarView.mostrarMensaje("Producto eliminado");
                productoModificarView.limpiarCampos();
            }
        });

        productoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto(Integer.parseInt(productoModificarView.getTxtBuscar().getText()));
                productoModificarView.mostrarMensaje("Producto modificado");
                productoModificarView.limpiarCampos();
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());


        productoDAO.crear(new Producto(codigo,nombre,precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProdutos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    private void eliminarProducto(int codigo) {
        productoDAO.eliminar(codigo);
    }

    private void actualizarProducto(int codigo){
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            int nuevoCodigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
            String nuevoNombre = productoModificarView.getTxtNombre().getText();
            double nuevoPrecio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

            producto.setCodigo(nuevoCodigo);
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);

            productoDAO.actualizar(producto);
            productoModificarView.mostrarMensaje("Producto modificado exitosamente.");
            productoModificarView.limpiarCampos();
        } else {
            productoModificarView.mostrarMensaje("Producto no encontrado.");
        }
    }
}

