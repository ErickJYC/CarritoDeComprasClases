package edu.ec.ups.vista;

import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

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
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioListarView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setTitle(mIH.get("usuario.listar.titulo"));
        setSize(500, 500);
        setClosable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new Object[]{
                mIH.get("usuario.tabla.nombre"),
                mIH.get("usuario.tabla.password"),
                mIH.get("usuario.tabla.rol")
        }, 0);
        tblUsuario.setModel(modelo);

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("usuario.listar.titulo"));
        LblUsuario.setText(mIH.get("usuario.nombre"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnListar.setText(mIH.get("boton.listar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("usuario.listar.titulo"));
        modelo.setColumnIdentifiers(new Object[]{
                mIH.get("usuario.tabla.nombre"),
                mIH.get("usuario.tabla.password"),
                mIH.get("usuario.tabla.rol")
        });
        aplicarTextos();
    }

    public void listarUsuarios(List<Usuario> listaUsuarios) {
        modelo.setRowCount(0);
        for (Usuario usuario : listaUsuarios) {
            modelo.addRow(new Object[]{
                    usuario.getUsername(),
                    usuario.getContrasenia(),
                    usuario.getRol().toString()
            });
        }
    }

    public void listarUsuariosPorUsername(List<Usuario> listaUsuarios, String username) {
        modelo.setRowCount(0);
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                modelo.addRow(new Object[]{
                        usuario.getUsername(),
                        usuario.getContrasenia(),
                        usuario.getRol().toString()
                });
            }
        }
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

}
