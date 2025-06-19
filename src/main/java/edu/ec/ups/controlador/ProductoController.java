package edu.ec.ups.controlador;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoModificarView productoModificarView;
    private final ProductoEliminarView productoEliminarView;

    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoModificarView productoModificarView,
                              ProductoEliminarView productoEliminarView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoModificarView = productoModificarView;
        this.productoEliminarView = productoEliminarView;
        this.configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
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

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });
        carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
                String nombre = carritoAnadirView.getTxtNombre().getText();
                double precio = Double.parseDouble(carritoAnadirView.getTxtPrecio().getText());

                DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
                modelo.addRow(new Object[]{codigo, nombre, precio});

                actualizarTotales();
            }
        });
        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();

                for (int i = 0; i < modelo.getRowCount(); i++) {
                    int codigo = (int) modelo.getValueAt(i, 0);
                    String nombre = (String) modelo.getValueAt(i, 1);
                    double precio = (double) modelo.getValueAt(i, 2);

                    Producto producto = new Producto(codigo, nombre, precio);
                    productoDAO.crear(producto);
                }

                carritoAnadirView.mostrarMensaje("Productos guardados correctamente.");
            }
        });
        carritoAnadirView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoAnadirView.getTxtCodigo().setText("");
                carritoAnadirView.getTxtNombre().setText("");
                carritoAnadirView.getTxtPrecio().setText("");
                carritoAnadirView.getTxtSubtotal().setText("");
                carritoAnadirView.getTxtIva().setText("");
                carritoAnadirView.getTxtTotal().setText("");
                carritoAnadirView.getCbxCantidad().setSelectedIndex(0);

                DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
                modelo.setRowCount(0);
            }
        });
        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(productoEliminarView.getTxtBuscar().getText());

                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                if (productoEncontrado != null) {
                    productoEliminarView.cargarDatos(productoEncontrado);
                } else {
                    productoEliminarView.mostrarMensaje("Producto no se ha encontrado");
                }
            }
        });
        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(Integer.parseInt(productoEliminarView.getTxtBuscar().getText()));
                eliminarProducto(Integer.parseInt(productoEliminarView.getTxtBuscar().getText()));
                productoEliminarView.mostrarMensaje("Producto ha sido eliminado");

            }
        });

        productoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto(Integer.parseInt(productoModificarView.getTxtCodigo().getText()));
                productoModificarView.mostrarMensaje("Producto ha sido modificado");
                productoModificarView.limpiarCampos();
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

    private void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontro el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }

    private void actualizarProducto(int codigo) {
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

                if (productoDAO.buscarPorCodigo(nuevoCodigo) != null && nuevoCodigo != codigo) {
                    productoModificarView.mostrarMensaje("Código ya existente.");
                    return;
                }

                Producto productoActualizado = new Producto(nuevoCodigo, nuevoNombre, nuevoPrecio);

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

    private void actualizarTotales() {
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        double subtotal = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            subtotal += (double) modelo.getValueAt(i, 2);
        }

        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        carritoAnadirView.getTxtSubtotal().setText(String.format("%.2f", subtotal));
        carritoAnadirView.getTxtIva().setText(String.format("%.2f", iva));
        carritoAnadirView.getTxtTotal().setText(String.format("%.2f", total));
    }
}

