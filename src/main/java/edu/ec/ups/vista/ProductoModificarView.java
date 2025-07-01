package edu.ec.ups.vista;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

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
    private MensajeInternacionalizacionHandler mIH;

    public ProductoModificarView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        aplicarTextos();
        setTitle(mIH.get("producto.modificar.titulo"));
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("producto.modificar.titulo"));
        LblCodigobusqueda.setText(mIH.get("producto.buscar.codigo"));
        LblCodigo.setText(mIH.get("producto.codigo"));
        LblNombre.setText(mIH.get("producto.nombre"));
        LblPrecio.setText(mIH.get("producto.precio"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnEditar.setText(mIH.get("boton.editar"));
        btnModificar.setText(mIH.get("boton.modificar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("producto.modificar.titulo"));
        aplicarTextos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                mIH.get("mensaje.confirmacion.actualizar.producto"),
                mIH.get("mensaje.confirmacion"),
                JOptionPane.YES_NO_OPTION
        );
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCodigo.setText("");
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

    public JLabel getLblCodigobusqueda() {
        return LblCodigobusqueda;
    }

    public void setLblCodigobusqueda(JLabel lblCodigobusqueda) {
        LblCodigobusqueda = lblCodigobusqueda;
    }

    public JLabel getLblCodigo() {
        return LblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        LblCodigo = lblCodigo;
    }

    public JLabel getLblNombre() {
        return LblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        LblNombre = lblNombre;
    }

    public JLabel getLblPrecio() {
        return LblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        LblPrecio = lblPrecio;
    }

    public JLabel getLblTitulo() {
        return LblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        LblTitulo = lblTitulo;
    }

}
