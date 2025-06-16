package edu.ec.ups.vista;

import edu.ec.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductoEliminarView extends JFrame{
    private JPanel panelPrincipal;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblResultadoBuscar;
    private JButton btnEliminar;
    private DefaultTableModel modelo;

    public ProductoEliminarView() {
        setTitle("Modificar o Eliminar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modelo.setColumnIdentifiers(columnas);
        tblResultadoBuscar.setModel(modelo);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblResultadoBuscar() {
        return tblResultadoBuscar;
    }

    public void setTblResultadoBuscar(JTable tblResultadoBuscar) {
        this.tblResultadoBuscar = tblResultadoBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }


    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cargarDatos(Producto producto) {
        modelo.setNumRows(0);
        if (producto != null) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }


}
