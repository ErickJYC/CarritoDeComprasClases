package edu.ec.ups.vista;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

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
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioEliminarView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setTitle(mIH.get("usuario.eliminar.titulo"));
        setSize(500, 500);
        setResizable(true);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new Object[]{
                mIH.get("usuario.tabla.nombre"),
                mIH.get("usuario.tabla.password"),
                mIH.get("usuario.tabla.rol")
        }, 0);
        tblResultadosBuscar.setModel(modelo);

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblCondigocarrito.setText(mIH.get("usuario.buscar.titulo"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnEliminar.setText(mIH.get("boton.eliminar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("usuario.eliminar.titulo"));
        modelo.setColumnIdentifiers(new Object[]{
                mIH.get("usuario.tabla.nombre"),
                mIH.get("usuario.tabla.password"),
                mIH.get("usuario.tabla.rol")
        });
        aplicarTextos();
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
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                mIH.get("mensaje.confirmacion.eliminar.usuario"),
                mIH.get("mensaje.confirmacion"),
                JOptionPane.YES_NO_OPTION
        );
        return respuesta == JOptionPane.YES_OPTION;
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


    public void limpiarCampos() {
        txtCarrito.setText("");
        modelo.setRowCount(0);
    }

}
