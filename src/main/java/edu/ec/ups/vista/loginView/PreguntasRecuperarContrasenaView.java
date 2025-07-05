package edu.ec.ups.vista.loginView;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class PreguntasRecuperarContrasenaView extends JFrame{
    private JTextField txtRespuesta1;
    private JTextField txtRespuesta3;
    private JTextField txtRespuesta2;
    private JButton btnEnviar;
    private JButton btnTerminar;
    private JLabel lblPregunta1;
    private JLabel lblPregunta2;
    private JLabel lblPregunta3;
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private final MensajeInternacionalizacionHandler mi;

    public PreguntasRecuperarContrasenaView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Recuperar Contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        setTitle(mi.get("cuestionario.recuperar.titulo"));
        lblTitulo.setText(mi.get("cuestionario.recuperar.titulo"));
        lblPregunta1.setText(mi.get("cuestionario.recuperar.pregunta1"));
        lblPregunta2.setText(mi.get("cuestionario.recuperar.pregunta2"));
        lblPregunta3.setText(mi.get("cuestionario.recuperar.pregunta3"));
        btnEnviar.setText(mi.get("cuestionario.recuperar.boton.enviar"));
        btnTerminar.setText(mi.get("cuestionario.recuperar.boton.terminar"));
    }

    public JTextField getTxtRespuesta1() {
        return txtRespuesta1;
    }

    public void setTxtRespuesta1(JTextField txtRespuesta1) {
        this.txtRespuesta1 = txtRespuesta1;
    }

    public JTextField getTxtRespuesta3() {
        return txtRespuesta3;
    }

    public void setTxtRespuesta3(JTextField txtRespuesta3) {
        this.txtRespuesta3 = txtRespuesta3;
    }

    public JTextField getTxtRespuesta2() {
        return txtRespuesta2;
    }

    public void setTxtRespuesta2(JTextField txtRespuesta2) {
        this.txtRespuesta2 = txtRespuesta2;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void setBtnEnviar(JButton btnEnviar) {
        this.btnEnviar = btnEnviar;
    }

    public JButton getBtnTerminar() {
        return btnTerminar;
    }

    public void setBtnTerminar(JButton btnTerminar) {
        this.btnTerminar = btnTerminar;
    }

    public JLabel getLblPregunta1() {
        return lblPregunta1;
    }

    public void setLblPregunta1(JLabel lblPregunta1) {
        this.lblPregunta1 = lblPregunta1;
    }

    public JLabel getLblPregunta2() {
        return lblPregunta2;
    }

    public void setLblPregunta2(JLabel lblPregunta2) {
        this.lblPregunta2 = lblPregunta2;
    }

    public JLabel getLblPregunta3() {
        return lblPregunta3;
    }

    public void setLblPregunta3(JLabel lblPregunta3) {
        this.lblPregunta3 = lblPregunta3;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public MensajeInternacionalizacionHandler getMi() {
        return mi;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

