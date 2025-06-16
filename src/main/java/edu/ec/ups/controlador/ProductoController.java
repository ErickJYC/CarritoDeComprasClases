package edu.ec.ups.controlador;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.vista.ProductoAnadirView;
import edu.ec.ups.vista.ProductoEliminarView;
import edu.ec.ups.vista.ProductoListaView;
import edu.ec.ups.vista.ProductoModificarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private  ProductoAnadirView productoAnadirView;
    private  ProductoListaView productoListaView;
    private  ProductoDAO productoDAO;
    private  ProductoEliminarView productoEliminarView;
    private  ProductoModificarView productoModificarView;

    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
        configureEventos();
    }

    public ProductoAnadirView getProductoAnadirView() {
        return productoAnadirView;
    }

    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
    }

    public ProductoListaView getProductoListaView() {
        return productoListaView;
    }

    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public ProductoEliminarView getProductoEliminarView() {
        return productoEliminarView;
    }

    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
    }

    public ProductoModificarView getProductoModificarView() {
        return productoModificarView;
    }

    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
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
        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(productoEliminarView.getTxtBuscar().getText());

                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                if (productoEncontrado  != null) {
                    productoEliminarView.cargarDatos(productoEncontrado);
                }else{
                    productoEliminarView.mostrarMensaje("Producto no encontrado");
                }
            }
        });
        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(Integer.parseInt(productoEliminarView.getTxtBuscar().getText()));
                eliminarProducto(Integer.parseInt(productoEliminarView.getTxtBuscar().getText()));
                productoEliminarView.mostrarMensaje("Producto eliminado");

            }
        });

        productoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               actualizarProducto(Integer.parseInt(productoModificarView.getTxtCodigo().getText()));
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
            int confirmacion = JOptionPane.showConfirmDialog(
                    productoModificarView,
                    "¿Está seguro de que desea modificar este producto?",
                    "Confirmar modificación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                int nuevoCodigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
                String nuevoNombre = productoModificarView.getTxtNombre().getText();
                double nuevoPrecio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

                // Verificar si el nuevo código ya existe
                if (productoDAO.buscarPorCodigo(nuevoCodigo) != null && nuevoCodigo != codigo) {
                    productoModificarView.mostrarMensaje("Código ya existente.");
                    return;
                }

                Producto productoActualizado = new Producto(nuevoCodigo, nuevoNombre, nuevoPrecio);

                // Eliminar el antiguo y crear el nuevo
                productoDAO.eliminar(codigo);
                productoDAO.crear(productoActualizado);

                productoModificarView.mostrarMensaje("Producto modificado exitosamente.");
                productoModificarView.limpiarCampos();
            } else {
                productoModificarView.mostrarMensaje("Modificación cancelada.");
            }
        } else {
            productoModificarView.mostrarMensaje("Producto no encontrado.");
        }
    }
}

