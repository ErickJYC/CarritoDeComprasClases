package edu.ec.ups.vista.loginView;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class RegistrarView extends JFrame {
    private JLabel lblNombreCompleto;
    private JTextField txtNombreCompleto;
    private JLabel lblFechaDeNacimiento;
    private JComboBox cbxDia;
    private JComboBox cbxMes;
    private JComboBox cbxAño;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JTextField txtCelular;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JLabel lblRegistrar;
    private JPanel panelPrincipal;
    private JLabel lblContrasena;
    private JLabel lblCelular;
    private MensajeInternacionalizacionHandler mi;

    public RegistrarView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Recuperar Contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        for (int i = 1; i <= 31; i++) cbxDia.addItem(i);
        for (int i = 1980; i <= 2025; i++) cbxAño.addItem(i);

        cambiarIdioma();


    }

    private void cambiarIdioma() {
        lblRegistrar.setText(mi.get("registrar.titulo"));
        lblNombreCompleto.setText(mi.get("registrar.nombre"));
        lblUsuario.setText(mi.get("registrar.usuario"));
        lblContrasena.setText(mi.get("registrar.contrasena"));
        lblCelular.setText(mi.get("registrar.celular"));
        lblCorreo.setText(mi.get("registrar.correo"));
        lblFechaDeNacimiento.setText(mi.get("registrar.fechaNacimiento"));

        btnRegistrar.setText(mi.get("registrar.boton.registrar"));
        btnLimpiar.setText(mi.get("registrar.boton.limpiar"));

        cbxMes.removeAllItems(); // Limpiar meses actuales
        String[] meses = {
                mi.get("mes.enero"), mi.get("mes.febrero"), mi.get("mes.marzo"),
                mi.get("mes.abril"), mi.get("mes.mayo"), mi.get("mes.junio"),
                mi.get("mes.julio"), mi.get("mes.agosto"), mi.get("mes.septiembre"),
                mi.get("mes.octubre"), mi.get("mes.noviembre"), mi.get("mes.diciembre")
        };
        for (String mes : meses) {
            cbxMes.addItem(mes);
        }
    }

    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    public void setLblNombreCompleto(JLabel lblNombreCompleto) {
        this.lblNombreCompleto = lblNombreCompleto;
    }

    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    public JLabel getLblFechaDeNacimiento() {
        return lblFechaDeNacimiento;
    }

    public void setLblFechaDeNacimiento(JLabel lblFechaDeNacimiento) {
        this.lblFechaDeNacimiento = lblFechaDeNacimiento;
    }

    public JComboBox getCbxDia() {
        return cbxDia;
    }

    public void setCbxDia(JComboBox cbxDia) {
        this.cbxDia = cbxDia;
    }

    public JComboBox getCbxMes() {
        return cbxMes;
    }

    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    public JComboBox getCbxAño() {
        return cbxAño;
    }

    public void setCbxAño(JComboBox cbxAño) {
        this.cbxAño = cbxAño;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JTextField getTxtCelular() {
        return txtCelular;
    }

    public void setTxtCelular(JTextField txtCelular) {
        this.txtCelular = txtCelular;
    }

    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JLabel getLblRegistrar() {
        return lblRegistrar;
    }

    public void setLblRegistrar(JLabel lblRegistrar) {
        this.lblRegistrar = lblRegistrar;
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
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        cbxDia.setSelectedIndex(0);
        cbxMes.setSelectedIndex(0);
        cbxAño.setSelectedIndex(0);

    }
}
