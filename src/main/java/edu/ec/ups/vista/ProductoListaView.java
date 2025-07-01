package edu.ec.ups.vista;

import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JButton btnListar;
    private JPanel panelPrincipal;
    private JLabel LblNombre;
    private JLabel LblTitulo;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public ProductoListaView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setTitle(mIH.get("producto.lista.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);

        modelo = new DefaultTableModel(new Object[]{
                mIH.get("producto.tabla.codigo"),
                mIH.get("producto.tabla.nombre"),
                mIH.get("producto.tabla.precio")
        }, 0);
        tblProductos.setModel(modelo);

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("producto.lista.titulo"));
        LblNombre.setText(mIH.get("producto.nombre"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnListar.setText(mIH.get("boton.listar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("producto.lista.titulo"));
        modelo.setColumnIdentifiers(new Object[]{
                mIH.get("producto.tabla.codigo"),
                mIH.get("producto.tabla.nombre"),
                mIH.get("producto.tabla.precio")
        });
        aplicarTextos();
    }

    public void cargarDatos(List<Producto> listProductos) {
        modelo.setRowCount(0);
        for (Producto producto : listProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }


    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTable1() {
        return tblProductos;
    }

    public void setTable1(JTable table1) {
        this.tblProductos = table1;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JLabel getLblNombre() {
        return LblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        LblNombre = lblNombre;
    }

    public JLabel getLblTitulo() {
        return LblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        LblTitulo = lblTitulo;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

}
