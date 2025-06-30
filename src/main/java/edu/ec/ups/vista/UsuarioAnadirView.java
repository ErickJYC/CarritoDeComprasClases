package edu.ec.ups.vista;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import javax.swing.*;

public class UsuarioAnadirView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JComboBox cbxRol;
    private UsuarioDAO usuarioDAO;

    public UsuarioAnadirView (UsuarioDAO usuarioDAO) {
        setContentPane(panelPrincipal);
        setTitle("Crear Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        this.usuarioDAO = usuarioDAO;
        cargarRoles();

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
    public void crearUsuario() {
        String username = txtUsuario.getText();
        String password = txtContrasena.getText();
        Rol rol = Rol.valueOf(cbxRol.getSelectedItem().toString());

        if (txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty() || rol == null) {
            mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            mostrarMensaje("Ese nombre de usuario ya está en uso.");
            return;
        }

        Usuario usuario = new Usuario(username, password, rol);
        usuarioDAO.crear(usuario);

        mostrarMensaje("Usuario creado exitosamente: " + username);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        cbxRol.setSelectedIndex(0);
    }

    public void cargarRoles() {
        cbxRol.removeAllItems();
        cbxRol.addItem("ADMINISTRADOR");
        cbxRol.addItem("USUARIO");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
