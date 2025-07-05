package edu.ec.ups.vista.carritoView;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTable tblCarritos;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel lblEliminar;
    private JLabel lblCodigo;
    private CarritoDAO carritoDAO;
    DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public CarritoEliminarView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle(mi.get("carrito.eliminar.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mi.get("carrito.eliminar.columna.codigo"),
                mi.get("carrito.eliminar.columna.nombre"),
                mi.get("carrito.eliminar.columna.precio"),
                mi.get("carrito.eliminar.columna.cantidad"),
                mi.get("carrito.eliminar.columna.subtotal"),
                mi.get("carrito.eliminar.columna.total")
        };
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);

        cambiarIdioma();
    }

    public void cambiarIdioma() {
        setTitle(mi.get("carrito.eliminar.titulo"));
        lblEliminar.setText(mi.get("carrito.eliminar.etiqueta"));
        lblCodigo.setText(mi.get("carrito.eliminar.etiqueta.codigo"));
        btnBuscar.setText(mi.get("carrito.eliminar.boton.buscar"));
        btnEliminar.setText(mi.get("carrito.eliminar.boton.eliminar"));

        modelo.setColumnIdentifiers(new Object[]{
                mi.get("carrito.eliminar.columna.codigo"),
                mi.get("carrito.eliminar.columna.nombre"),
                mi.get("carrito.eliminar.columna.precio"),
                mi.get("carrito.eliminar.columna.cantidad"),
                mi.get("carrito.eliminar.columna.subtotal"),
                mi.get("carrito.eliminar.columna.total")
        });
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

    public JTable getTblCarritos() {
        return tblCarritos;
    }

    public void setTblCarritos(JTable tblCarritos) {
        this.tblCarritos = tblCarritos;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JLabel getLblEliminar() {
        return lblEliminar;
    }

    public void setLblEliminar(JLabel lblEliminar) {
        this.lblEliminar = lblEliminar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public CarritoDAO getCarritoDAO() {
        return carritoDAO;
    }

    public void setCarritoDAO(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
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

    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);

        for (ItemCarrito itemCarrito : carrito.obtenerItems()) {
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itemCarrito.getCantidad(),
                    itemCarrito.getSubtotal(),
                    itemCarrito.getTotal()
            };
            modelo.addRow(fila);
        }
    }

    public void limpiarCampos() {
        modelo.setRowCount(0);
        txtCodigo.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
