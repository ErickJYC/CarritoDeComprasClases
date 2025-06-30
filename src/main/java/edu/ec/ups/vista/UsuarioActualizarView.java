package edu.ec.ups.vista;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Usuario;

import javax.swing.*;

public class UsuarioActualizarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuariobusqueda;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JComboBox cbxRol;
    private JButton btnGuardar;
    private JLabel LblUsuariobusqueda;
    private JLabel LblUsuario;
    private JLabel LblContrasena;
    private JLabel LblRol;
    private JLabel LblTitulo;
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioSeleccionado;

    public UsuarioActualizarView(UsuarioDAO usuarioDAO) {
        setContentPane(panelPrincipal);
        setTitle("Actualizar Usuario");
        setSize(500, 500);
        setClosable(true);
        setResizable(true);
        setIconifiable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.usuarioDAO = usuarioDAO;
        cargarDatosRol();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtUsuariobusqueda() {
        return txtUsuariobusqueda;
    }

    public void setTxtUsuariobusqueda(JTextField txtUsuariobusqueda) {
        this.txtUsuariobusqueda = txtUsuariobusqueda;
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

    public JComboBox getCbxRol() {
        return cbxRol;
    }

    public void setCbxRol(JComboBox cbxRol) {
        this.cbxRol = cbxRol;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    public void cargarDatosRol () {
        cbxRol.removeAllItems();
        cbxRol.addItem("ADMINISTRADOR");
        cbxRol.addItem("USUARIO");
    }

    public void buscarUsuario() {
        String username = txtUsuariobusqueda.getText();
        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario == null) {
            mostrarMensaje("Usuario no encontrado");
            return;
        }

        txtUsuario.setText(usuario.getUsername());
        txtContrasena.setText(usuario.getContrasenia());
        cbxRol.setSelectedItem(usuario.getRol());
    }

    public void editarValoresActualizarTrue() {
        txtContrasena.setEnabled(true);
    }

    public void editarValoresActualizarFalse() {
        txtUsuario.setEnabled(false);
        txtContrasena.setEnabled(false);
        cbxRol.setEnabled(false);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtUsuariobusqueda.setText("");
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar el usuario?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
