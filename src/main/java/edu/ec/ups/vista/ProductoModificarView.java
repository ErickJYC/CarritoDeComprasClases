package edu.ec.ups.vista;

import javax.swing.*;

public class ProductoModificarView extends JInternalFrame{
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnModificar;
    private JPanel panelPrincipal;

    public ProductoModificarView(){
        setTitle("Modificar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(700,250);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);
//        setResizable(false);
//        setVisible(true);

    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCodigo.setText("");
    }
}
