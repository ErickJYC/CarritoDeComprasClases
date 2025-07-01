package edu.ec.ups.vista;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class UsuarioAnadirView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JComboBox cbxRol;
    private JLabel LblTitulo;
    private JLabel LblUsuario;
    private JLabel LblContrasena;
    private JLabel LblRol;
    private UsuarioDAO usuarioDAO;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioAnadirView(UsuarioDAO usuarioDAO, MensajeInternacionalizacionHandler mIH) {
        this.usuarioDAO = usuarioDAO;
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setTitle(mIH.get("usuario.crear.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        cargarRoles();
        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("usuario.crear.titulo"));
        LblUsuario.setText(mIH.get("usuario.nombre"));
        LblContrasena.setText(mIH.get("usuario.contrasena"));
        LblRol.setText(mIH.get("usuario.rol"));

        btnRegistrar.setText(mIH.get("boton.registrar"));
        btnCancelar.setText(mIH.get("boton.cancelar"));

        cbxRol.removeAllItems();
        cbxRol.addItem(mIH.get("rol.admin"));
        cbxRol.addItem(mIH.get("rol.usuario"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("usuario.crear.titulo"));
        aplicarTextos();
    }

    public void crearUsuario() {
        String username = txtUsuario.getText();
        String password = txtContrasena.getText();
        String rolString = cbxRol.getSelectedItem().toString();
        Rol rol = rolString.equals(mIH.get("rol.admin")) ? Rol.ADMINISTRADOR : Rol.USUARIO;

        if (username.isEmpty() || password.isEmpty()) {
            mostrarMensaje(mIH.get("mensaje.campos.obligatorios"));
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            mostrarMensaje(mIH.get("mensaje.usuario.duplicado"));
            return;
        }

        Usuario usuario = new Usuario(username, password, rol);
        usuarioDAO.crear(usuario);

        mostrarMensaje(mIH.get("mensaje.usuario.creado") + ": " + username);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        cbxRol.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }


    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
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

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JComboBox getCbxRol() {
        return cbxRol;
    }

    public void setCbxRol(JComboBox cbxRol) {
        this.cbxRol = cbxRol;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cargarRoles() {
        cbxRol.removeAllItems();
        cbxRol.addItem("ADMINISTRADOR");
        cbxRol.addItem("USUARIO");
    }

}
