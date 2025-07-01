package edu.ec.ups.vista;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class LoginView extends JFrame{

    private JPanel panelSecundario;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JLabel LblUsuario;
    private JLabel LblContrasena;
    private JLabel LblTitulo;

    private MensajeInternacionalizacionHandler mIH;

    public LoginView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        aplicarTextos();  // aplica los textos traducidos
    }

    private void aplicarTextos() {
        setTitle(mIH.get("login.titulo"));
        LblTitulo.setText(mIH.get("login.titulo"));
        LblUsuario.setText(mIH.get("login.usuario"));
        LblContrasena.setText(mIH.get("login.contrasena"));
        btnIniciarSesion.setText(mIH.get("boton.iniciar"));
        btnRegistrarse.setText(mIH.get("boton.registrarse"));
        btnRegistrar.setText(mIH.get("boton.registrar"));

    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        aplicarTextos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }



    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public JLabel getLblTitulo() {
        return LblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        LblTitulo = lblTitulo;
    }

    public JLabel getLblContrasena() {
        return LblContrasena;
    }

    public void setLblContrasena(JLabel lblContrasena) {
        LblContrasena = lblContrasena;
    }

    public JLabel getLblUsuario() {
        return LblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        LblUsuario = lblUsuario;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

}
