package edu.ec.ups.vista.loginView;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class PreguntaView extends JFrame{
    private JComboBox cbxPreguntas;
    private JTextField txtRespuesta;
    private JButton btnGuardar;
    private JLabel lblPregunta;
    private JButton btnTerminar;
    private JLabel lblTitulo;
    private JPanel panelPrincipal;
    private JComboBox cbxIdiomas;
    private MensajeInternacionalizacionHandler mi;

public PreguntaView(MensajeInternacionalizacionHandler mi) {
    this.mi = mi;
    setContentPane(panelPrincipal);
    setTitle("PreguntaCuestionario");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(550, 350);
    setLocationRelativeTo(null);

    URL finalizar = LoginView.class.getClassLoader().getResource("imagenes/finalizar.png");
    if(finalizar != null){
        ImageIcon icono = new ImageIcon(finalizar);
        btnTerminar.setIcon(icono);
    }else {
        System.err.println("Error: No se ha cargado el icono de Login");
    }
    URL guardar = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
    if(guardar != null){
        ImageIcon icono = new ImageIcon(guardar);
        btnGuardar.setIcon(icono);
    }else {
        System.err.println("Error: No se ha cargado el icono de Login");
    }

    // 1. Añadir idiomas
    cbxIdiomas.addItem("Español");
    cbxIdiomas.addItem("Ingles");
    cbxIdiomas.addItem("Frances");

    // 2. Seleccionar idioma actual según el Locale
    if (mi.getLocale().getLanguage().equals("en")) {
        cbxIdiomas.setSelectedItem("Ingles");
    } else if (mi.getLocale().getLanguage().equals("fr")) {
        cbxIdiomas.setSelectedItem("Frances");
    } else {
        cbxIdiomas.setSelectedItem("Español");
    }

    // 3. Acción al cambiar idioma
    cbxIdiomas.addActionListener(e -> {
        String seleccion = (String) cbxIdiomas.getSelectedItem();
        if (seleccion != null) {
            switch (seleccion) {
                case "Español":
                    mi.setLenguaje("es", "EC");
                    break;
                case "Ingles":
                    mi.setLenguaje("en", "US");
                    break;
                case "Frances":
                    mi.setLenguaje("fr", "FR");
                    break;
            }
            cambiarIdioma();
        }
    });

    cambiarIdioma(); // Cargar textos al inicio

}
    public void cambiarIdioma() {
        setTitle(mi.get("cuestionario.pregunta.titulo"));
        lblTitulo.setText(mi.get("cuestionario.pregunta.titulo"));
        lblPregunta.setText(mi.get("cuestionario.pregunta.label"));
        btnGuardar.setText(mi.get("cuestionario.pregunta.boton.guardar"));
        btnTerminar.setText(mi.get("cuestionario.pregunta.boton.terminar"));
//        cbxPreguntas.setToolTipText(mi.get("ciudad_donde_naciste"));
//        cbxPreguntas.setToolTipText(mi.get("nombre_de_tu_mascota_en_la_infancia"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.nombre_de_tu_primer_docente"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.mejor_amigo_de_la_infancia"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.juego_infantil_favorito"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.nombre_de_tu_abuelo_materno"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.deportista_favorito"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.apodo_que_tenias_en_la_escuela"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.nombre_de_tu_primer_amor"));
//        cbxPreguntas.setToolTipText(mi.get("pregunta.lugar_de_vacaciones_mas_memorable"));

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
