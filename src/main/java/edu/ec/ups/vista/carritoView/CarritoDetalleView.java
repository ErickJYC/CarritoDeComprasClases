package edu.ec.ups.vista.carritoView;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoDetalleView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtTotal;
    private JTable tblProductos;
    private JLabel lblDetalle;
    private JLabel lblTotal;
    private JTextField txtSubtotal;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JTextField txtIva;
    DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public CarritoDetalleView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle(mi.get("carrito.detalle.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        tblProductos.setModel(modelo);

        cambiarIdioma();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JLabel getLblDetalle() {
        return lblDetalle;
    }

    public void setLblDetalle(JLabel lblDetalle) {
        this.lblDetalle = lblDetalle;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public void setTxtSubtotal(JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }

    public void setLblSubtotal(JLabel lblSubtotal) {
        this.lblSubtotal = lblSubtotal;
    }

    public JLabel getLblIva() {
        return lblIva;
    }

    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
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

    public void limpiarTabla() {
        modelo.setRowCount(0);
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);

        for (ItemCarrito itemCarrito : carrito.obtenerItems()) {
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itemCarrito.getCantidad()
            };
            modelo.addRow(fila);
        }
    }

    public void cambiarIdioma() {
        setTitle(mi.get("carrito.detalle.titulo"));
        lblDetalle.setText(mi.get("carrito.detalle.etiqueta"));
        lblSubtotal.setText(mi.get("carrito.detalle.subtotal"));
        lblIva.setText(mi.get("carrito.detalle.iva"));
        lblTotal.setText(mi.get("carrito.detalle.total"));

        modelo.setColumnIdentifiers(new Object[]{
                mi.get("carrito.detalle.columna.codigo"),
                mi.get("carrito.detalle.columna.nombre"),
                mi.get("carrito.detalle.columna.precio"),
                mi.get("carrito.detalle.columna.cantidad")
        });
    }
}
