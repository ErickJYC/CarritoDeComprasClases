package edu.ec.ups.vista;

import edu.ec.ups.modelo.Rol;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioCrearView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField TxtNombreCompleto;
    private JTextField TxtUsername;
    private JButton BtnRegistrar;
    private JButton BtnLimpiar;
    private JComboBox CbxRol;
    private JLabel lblNuevoUsuario;
    private JLabel lblNombreC;
    private JLabel lblUsuario;
    private JLabel lblRol;
    private JLabel lblContrasena;
    private JTextField TxtPassword;
    private JTextField TxtCorreo;
    private JTextField TxtCelular;
    private JComboBox cbxDia;
    private JComboBox cbxMes;
    private JComboBox cbxAño;
    private JLabel lblCorreo;
    private JLabel lblFechaN;
    private MensajeInternacionalizacionHandler mi;

    public UsuarioCrearView ( MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Crear Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        cargarRoles();

        BtnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        for (int i = 1; i <= 31; i++) cbxDia.addItem(i);
        for (int i = 1980; i <= 2025; i++) cbxAño.addItem(i);
        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        for (String mes : meses) {
            cbxMes.addItem(mes);
        }
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtNombreCompleto() {
        return TxtNombreCompleto;
    }

    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        TxtNombreCompleto = txtNombreCompleto;
    }

    public JTextField getTxtUsername() {
        return TxtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        TxtUsername = txtUsername;
    }

    public JButton getBtnRegistrar() {
        return BtnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        BtnRegistrar = btnRegistrar;
    }

    public JButton getBtnLimpiar() {
        return BtnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        BtnLimpiar = btnLimpiar;
    }

    public JComboBox getCbxRol() {
        return CbxRol;
    }

    public void setCbxRol(JComboBox cbxRol) {
        CbxRol = cbxRol;
    }

    public JLabel getLblNuevoUsuario() {
        return lblNuevoUsuario;
    }

    public void setLblNuevoUsuario(JLabel lblNuevoUsuario) {
        this.lblNuevoUsuario = lblNuevoUsuario;
    }

    public JLabel getLblNombreC() {
        return lblNombreC;
    }

    public void setLblNombreC(JLabel lblNombreC) {
        this.lblNombreC = lblNombreC;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JLabel getLblRol() {
        return lblRol;
    }

    public void setLblRol(JLabel lblRol) {
        this.lblRol = lblRol;
    }

    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    public JTextField getTxtPassword() {
        return TxtPassword;
    }

    public void setTxtPassword(JTextField txtPassword) {
        TxtPassword = txtPassword;
    }

    public JTextField getTxtCorreo() {
        return TxtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        TxtCorreo = txtCorreo;
    }

    public JTextField getTxtCelular() {
        return TxtCelular;
    }

    public void setTxtCelular(JTextField txtCelular) {
        TxtCelular = txtCelular;
    }

    public JComboBox getCbxDia() {
        return cbxDia;
    }

    public void setCbxDia(JComboBox cbxDia) {
        this.cbxDia = cbxDia;
    }

    public JComboBox getCbxMes() {
        return cbxMes;
    }

    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    public JComboBox getCbxAño() {
        return cbxAño;
    }

    public void setCbxAño(JComboBox cbxAño) {
        this.cbxAño = cbxAño;
    }

    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    public JLabel getLblFechaN() {
        return lblFechaN;
    }

    public void setLblFechaN(JLabel lblFechaN) {
        this.lblFechaN = lblFechaN;
    }

    public MensajeInternacionalizacionHandler getMi() {
        return mi;
    }

    public void setMi(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }

    public void limpiarCampos() {
        TxtUsername.setText("");
        TxtPassword.setText("");
        TxtCorreo.setText("");
        TxtCelular.setText("");
        TxtNombreCompleto.setText("");
        CbxRol.setSelectedIndex(0);
        cbxDia.setSelectedIndex(0);
        cbxMes.setSelectedIndex(0);
        cbxAño.setSelectedIndex(0);
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void cargarRoles() {
        CbxRol.removeAllItems();
        CbxRol.addItem("ADMINISTRADOR");
        CbxRol.addItem("USUARIO");
    }

    public Rol getRolSeleccionado() {
        String rolSeleccionado = (String) CbxRol.getSelectedItem();
        if (rolSeleccionado.equals("ADMINISTRADOR")) {
            return Rol.ADMINISTRADOR;
        } else if (rolSeleccionado.equals("USUARIO")) {
            return Rol.USUARIO;
        } else {
            return null;
        }
    }
}
