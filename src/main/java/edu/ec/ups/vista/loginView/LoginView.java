package edu.ec.ups.vista.loginView;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

/**
 * Ventana de inicio de sesión del sistema.
 * Permite ingresar usuario y contraseña, seleccionar idioma,
 * registrarse, recuperar contraseña y salir.
 */
public class LoginView extends JFrame{

    private JPanel panelSecundario;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JPanel panelPrincipal;
    private JButton btnOlvidar;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblIniciarSesion;
    private JComboBox cbxIdiomas;
    private JButton btnSalir;
    private MensajeInternacionalizacionHandler mi;
    /**
     * Constructor que inicializa la vista de login y sus componentes gráficos.
     * @param mi Manejador de mensajes internacionalizados.
     */
    public LoginView( MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        inicializarComponentes();

        URL iniciar = LoginView.class.getClassLoader().getResource("imagenes/iniciar.png");
        if(iniciar != null){
            ImageIcon icono = new ImageIcon(iniciar);
            btnIniciarSesion.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL registrar = LoginView.class.getClassLoader().getResource("imagenes/registrar.png");
        if(registrar != null){
            ImageIcon icono = new ImageIcon(registrar);
            btnRegistrarse.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL olvidar = LoginView.class.getClassLoader().getResource("imagenes/olvidar.png");
        if(olvidar != null){
            ImageIcon icono = new ImageIcon(olvidar);
            btnOlvidar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL salir = LoginView.class.getClassLoader().getResource("imagenes/salir.png");
        if(salir != null){
            ImageIcon icono = new ImageIcon(salir);
            btnSalir.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
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

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnOlvidar() {
        return btnOlvidar;
    }

    public void setBtnOlvidar(JButton btnOlvidar) {
        this.btnOlvidar = btnOlvidar;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    public JLabel getLblIniciarSesion() {
        return lblIniciarSesion;
    }

    public void setLblIniciarSesion(JLabel lblIniciarSesion) {
        this.lblIniciarSesion = lblIniciarSesion;
    }

    public JComboBox getCbxIdiomas() {
        return cbxIdiomas;
    }

    public void setCbxIdiomas(JComboBox cbxIdiomas) {
        this.cbxIdiomas = cbxIdiomas;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public MensajeInternacionalizacionHandler getMi() {
        return mi;
    }

    public void setMi(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }
    /**
     * Muestra un mensaje emergente.
     * @param mensaje Contenido del mensaje.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    /**
     * Muestra una ventana de confirmación al usuario.
     * @param mensaje Texto de la pregunta.
     * @return true si el usuario presiona "Sí", false si presiona "No".
     */
    public boolean mostrarMensajePregunta(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(this, mensaje, "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }
    /**
     * Limpia los campos de texto de usuario y contraseña.
     */
    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
    }
    /**
     * Inicializa los componentes del ComboBox de idiomas y textos en pantalla.
     */
    public void inicializarComponentes() {
        cbxIdiomas.removeAllItems();
        cbxIdiomas.addItem("Español");
        cbxIdiomas.addItem("English");
        cbxIdiomas.addItem("Français");
        actualizarTextos(mi);

    }
    /**
     * Actualiza los textos de la interfaz gráfica con base en el idioma seleccionado.
     * @param mi Manejador actualizado de idioma.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mi) {
        this.mi = mi; // actualizamos el handler si cambia

        // Etiquetas
        lblIniciarSesion.setText(mi.get("login.titulo"));
        lblUsuario.setText(mi.get("login.usuario"));
        lblContrasena.setText(mi.get("login.contrasenia"));

        // Botones
        btnIniciarSesion.setText(mi.get("login.iniciar"));
        btnRegistrarse.setText(mi.get("login.registrar"));
        btnOlvidar.setText(mi.get("login.olvidar"));
        btnSalir.setText(mi.get("login.salir"));

        // Título de la ventana
        setTitle(mi.get("login.titulo"));
    }

}