package edu.ec.ups.vista;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoDetalleView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCodigocarrito;
    private JTable tblDetallecarrito;
    private JButton btnBuscar;
    private Carrito carrito;
    DefaultTableModel modelo;

    public CarritoDetalleView() {
        setContentPane(panelPrincipal);
        setTitle("Detalles del Carrito");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setClosable(true);
        setResizable(true);
        setIconifiable(true);


        modelo = new DefaultTableModel(new Object[]{"Código", "Producto", "Cantidad", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
            tblDetallecarrito.setModel(modelo);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtCodigocarrito() {
        return txtCodigocarrito;
    }

    public void setTxtCodigocarrito(JTextField txtCodigocarrito) {
        this.txtCodigocarrito = txtCodigocarrito;
    }

    public JTable getTblDetallecarrito() {
        return tblDetallecarrito;
    }

    public void setTblDetallecarrito(JTable tblDetallecarrito) {
        this.tblDetallecarrito = tblDetallecarrito;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    public void cargarDatosCarrito(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            for (ItemCarrito itemCarrito : carrito.obtenerItems()) {
                Producto producto = itemCarrito.getProducto();
                Object[] fila = {
                        producto.getCodigo(),
                        producto.getNombre(),
                        itemCarrito.getCantidad(),
                        producto.getPrecio() * itemCarrito.getCantidad()
                };
                modelo.addRow(fila);
            }
        }
    }

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
