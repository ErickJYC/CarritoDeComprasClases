package edu.ec.ups.vista.loginView;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class PreguntaView extends JFrame{
    private JComboBox cbxPreguntas;
    private JTextField txtRespuesta;
    private JButton btnGuardar;
    private JLabel lblPregunta;
    private JButton btnTerminar;
    private JLabel lblTitulo;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mi;

public PreguntaView(MensajeInternacionalizacionHandler mi) {
    this.mi = mi;
    setContentPane(panelPrincipal);
    setTitle("PreguntaCuestionario");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(550, 350);
    setLocationRelativeTo(null);
}

    public JComboBox getCbxPreguntas() {
        return cbxPreguntas;
    }

    public void setCbxPreguntas(JComboBox cbxPreguntas) {
        this.cbxPreguntas = cbxPreguntas;
    }

    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    public void setTxtRespuesta(JTextField txtRespuesta) {
        this.txtRespuesta = txtRespuesta;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JLabel getLblPregunta() {
        return lblPregunta;
    }

    public void setLblPregunta(JLabel lblPregunta) {
        this.lblPregunta = lblPregunta;
    }

    public JButton getBtnTerminar() {
        return btnTerminar;
    }

    public void setBtnTerminar(JButton btnTerminar) {
        this.btnTerminar = btnTerminar;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public MensajeInternacionalizacionHandler getMi() {
        return mi;
    }

    public void setMi(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }

    public void mostrarMensaje(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje);
}

}
