package edu.ec.ups.vista;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioEliminarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCarrito;
    private JButton btnBuscar;
    private JTable tblResultadosBuscar;
    private JButton btnEliminar;
    private JLabel LblCondigocarrito;
    private DefaultTableModel modelo;
    private UsuarioDAO usuarioDAO;

    public UsuarioEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Usuario");
        setSize(500, 500);
        setResizable(true);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Username", "Password", "Rol"};
        modelo.setColumnIdentifiers(columnas);
        tblResultadosBuscar.setModel(modelo);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtUsuario() {
        return txtCarrito;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtCarrito = txtUsuario;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblResultadosBuscar() {
        return tblResultadosBuscar;
    }

    public void setTblResultadosBuscar(JTable tblResultadosBuscar) {
        this.tblResultadosBuscar = tblResultadosBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    public void cargarUsuario(List<Usuario> usuarios) {
        modelo.setRowCount(0);

        for (Usuario usuario : usuarios) {
            Object[] fila = {
                    usuario.getUsername(),
                    usuario.getContrasenia(),
                    usuario.getRol()
            };
            modelo.addRow(fila);
        }
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el usuario?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void limpiarCampos() {
        txtCarrito.setText("");
        modelo.setRowCount(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
