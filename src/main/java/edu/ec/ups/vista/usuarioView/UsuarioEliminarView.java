package edu.ec.ups.vista.usuarioView;

import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioEliminarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField TxtUsuario;
    private JButton BtnBuscar;
    private JTable tblUser;
    private JLabel usuarioLabel;
    private JButton BtnEliminar;
    private JLabel lblEliminar;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public UsuarioEliminarView( MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel(new Object[]{"Nombre", "Usuario", "Contraseña", "Correo", "Celular", "Fcha de Nacimiento", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblUser.setModel(modelo);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtUsuario() {
        return TxtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        TxtUsuario = txtUsuario;
    }

    public JButton getBtnBuscar() {
        return BtnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        BtnBuscar = btnBuscar;
    }

    public JTable getTblUser() {
        return tblUser;
    }

    public void setTblUser(JTable tblUser) {
        this.tblUser = tblUser;
    }

    public JLabel getUsuarioLabel() {
        return usuarioLabel;
    }

    public void setUsuarioLabel(JLabel usuarioLabel) {
        this.usuarioLabel = usuarioLabel;
    }

    public JButton getBtnEliminar() {
        return BtnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        BtnEliminar = btnEliminar;
    }

    public JLabel getLblEliminar() {
        return lblEliminar;
    }

    public void setLblEliminar(JLabel lblEliminar) {
        this.lblEliminar = lblEliminar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
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

}
