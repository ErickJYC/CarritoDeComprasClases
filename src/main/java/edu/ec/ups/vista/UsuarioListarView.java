package edu.ec.ups.vista;

import edu.ec.ups.modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioListarView  extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblUsuario;
    private JLabel LblUsuario;
    private JLabel LblTitulo;
    private DefaultTableModel modelo;

    public UsuarioListarView() {
        setContentPane(panelPrincipal);
        setTitle("Listar Usuarios");
        setSize(500, 500);
        setClosable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Username", "Password", "Rol"};
        modelo.setColumnIdentifiers(columnas);
        tblUsuario.setModel(modelo);
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

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public JTable getTblUsuario() {
        return tblUsuario;
    }

    public void setTblUsuario(JTable tblUsuario) {
        this.tblUsuario = tblUsuario;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    public void listarUsuarios(List<Usuario> listaUsuarios) {
        modelo.setRowCount(0);

        for (Usuario usuario : listaUsuarios) {
            Object[] fila = new Object[3];
            fila[0] = usuario.getUsername();
            fila[1] = usuario.getContrasenia();
            fila[2] = usuario.getRol().toString();
            modelo.addRow(fila);
        }

    }

    public void listarUsuariosPorUsername(List<Usuario> listaUsuarios, String username) {
        modelo.setRowCount(0);

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                Object[] fila = new Object[3];
                fila[0] = usuario.getUsername();
                fila[1] = usuario.getContrasenia();
                fila[2] = usuario.getRol().toString();
                modelo.addRow(fila);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
