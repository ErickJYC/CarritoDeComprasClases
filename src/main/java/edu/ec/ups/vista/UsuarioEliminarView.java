package edu.ec.ups.vista;

import edu.ec.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsuarioEliminarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblResultadosBuscar;
    private JButton btnEliminar;
    private DefaultTableModel modelo;

    public UsuarioEliminarView() {
        setTitle("Eliminar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(700,250);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);
//        setResizable(false);
//        setVisible(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modelo.setColumnIdentifiers(columnas);
        tblResultadosBuscar.setModel(modelo);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblResultadosBuscar() {
        return tblResultadosBuscar;
    }

    public void setTblResultadosBuscar(JTable tblResultadosBuscar) {
        this.tblResultadosBuscar = tblResultadosBuscar;
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
