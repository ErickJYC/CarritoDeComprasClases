package edu.ec.ups.vista;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoModificarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCarrito;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JButton btnActualizar;
    private JComboBox cbxCantidad;
    private JLabel lblCodigo;
    private JLabel lblCantidad;
    private JLabel lblModificar;
    private JButton btnLimpiar;
    private JTextField txtSubtotal;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public CarritoModificarView(CarritoDAO carritoDAO, MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle(mi.get("carrito.modificar.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        cargarDatosCantidad();

        modelo = new DefaultTableModel();
        tblProductos.setModel(modelo);

        cambiarIdioma();
    }

    public void cambiarIdioma() {
        setTitle(mi.get("carrito.modificar.titulo"));
        lblModificar.setText(mi.get("carrito.modificar.etiqueta"));
        lblCodigo.setText(mi.get("carrito.modificar.codigo"));
        lblCantidad.setText(mi.get("carrito.modificar.cantidad"));
        lblSubtotal.setText(mi.get("carrito.modificar.subtotal"));
        lblIva.setText(mi.get("carrito.modificar.iva"));
        lblTotal.setText(mi.get("carrito.modificar.total"));
        btnBuscar.setText(mi.get("boton.buscar"));
        btnActualizar.setText(mi.get("boton.actualizar"));
        btnLimpiar.setText(mi.get("boton.limpiar"));

        modelo.setColumnIdentifiers(new Object[]{
                mi.get("carrito.columna.codigo"),
                mi.get("carrito.columna.nombre"),
                mi.get("carrito.columna.precio"),
                mi.get("carrito.columna.cantidad")
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtCarrito() {
        return txtCarrito;
    }

    public void setTxtCarrito(JTextField txtCarrito) {
        this.txtCarrito = txtCarrito;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public JLabel getLblModificar() {
        return lblModificar;
    }

    public void setLblModificar(JLabel lblModificar) {
        this.lblModificar = lblModificar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
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

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
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

    public void limpiarCampos() {
        modelo.setRowCount(0);
        txtCarrito.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
    }

    private void cargarDatosCantidad() {
        cbxCantidad.removeAllItems();
        for (int i = 0; i < 20; i++) {
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);
        for (ItemCarrito itemCarrito : carrito.obtenerItems()) {
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itemCarrito.getCantidad(),
            };
            modelo.addRow(fila);
        }
    }
}
