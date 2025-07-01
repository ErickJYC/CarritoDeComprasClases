package edu.ec.ups.vista;

import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductoEliminarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblResultadoBuscar;
    private JButton btnEliminar;
    private JLabel LblCodigo;
    private JLabel LblTitulo;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler mIH;

    public ProductoEliminarView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setTitle(mIH.get("producto.eliminar.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(700, 250);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mIH.get("producto.tabla.codigo"),
                mIH.get("producto.tabla.nombre"),
                mIH.get("producto.tabla.precio")
        };
        modelo.setColumnIdentifiers(columnas);
        tblResultadoBuscar.setModel(modelo);

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("producto.eliminar.titulo"));
        LblCodigo.setText(mIH.get("producto.codigo"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnEliminar.setText(mIH.get("boton.eliminar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("producto.eliminar.titulo"));

        modelo.setColumnIdentifiers(new Object[]{
                mIH.get("producto.tabla.codigo"),
                mIH.get("producto.tabla.nombre"),
                mIH.get("producto.tabla.precio")
        });

        aplicarTextos();
    }

    public void cargarDatos(Producto producto) {
        modelo.setRowCount(0);
        if (producto != null) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                mIH.get("mensaje.confirmacion.eliminar.producto"),
                mIH.get("mensaje.confirmacion"),
                JOptionPane.YES_NO_OPTION
        );
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
    }


    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblResultadoBuscar() {
        return tblResultadoBuscar;
    }

    public void setTblResultadoBuscar(JTable tblResultadoBuscar) {
        this.tblResultadoBuscar = tblResultadoBuscar;
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

}
