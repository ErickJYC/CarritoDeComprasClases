package edu.ec.ups.vista;

import javax.swing.*;

public class ProductoModificarView extends JInternalFrame{
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnModificar;
    private JPanel panelPrincipal;
    private JTextField txtCodigobusqueda;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JLabel LblCodigobusqueda;
    private JLabel LblCodigo;
    private JLabel LblNombre;
    private JLabel LblPrecio;
    private JLabel LblTitulo;

    public ProductoModificarView(){
        setContentPane(panelPrincipal);
        setTitle("Actualizar Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

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

    public JTextField getTxtCodigobusqueda() {
        return txtCodigobusqueda;
    }

    public void setTxtCodigobusqueda(JTextField txtCodigobusqueda) {
        this.txtCodigobusqueda = txtCodigobusqueda;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
    public void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCodigo.setText("");
    }
}
