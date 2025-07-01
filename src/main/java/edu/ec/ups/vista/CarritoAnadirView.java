package edu.ec.ups.vista;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoAnadirView  extends JInternalFrame {
    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JPanel panelPrincipal;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JLabel LblTitulo;
    private JLabel LblCodigo;
    private JLabel LblNombre;
    private JLabel LblPrecio;
    private JLabel LblCantidad;
    private JLabel LblSubtotal;
    private JLabel LblIva;
    private JLabel LblTotal;
    private Carrito carrito;
    DefaultTableModel modelo = new DefaultTableModel();
    private MensajeInternacionalizacionHandler mIH;

    public CarritoAnadirView(Usuario usuario, MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("carrito.anadir.titulo"), true, true, false, true);
        this.mIH = mIH;
        this.carrito = new Carrito(usuario);

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        Object[] columnas = {
                mIH.get("carrito.tabla.codigo"),
                mIH.get("carrito.tabla.nombre"),
                mIH.get("carrito.tabla.precio"),
                mIH.get("carrito.tabla.cantidad"),
                mIH.get("carrito.tabla.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatosProductos();
        aplicarTextos();
    }

    private void cargarDatosProductos() {
        cbxCantidad.removeAllItems();
        for (int i = 0; i < 20; i++) {
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("carrito.anadir.titulo"));
        LblCodigo.setText(mIH.get("carrito.codigo"));
        LblNombre.setText(mIH.get("carrito.nombre"));
        LblPrecio.setText(mIH.get("carrito.precio"));
        LblCantidad.setText(mIH.get("carrito.cantidad"));
        LblSubtotal.setText(mIH.get("carrito.subtotal"));
        LblIva.setText(mIH.get("carrito.iva"));
        LblTotal.setText(mIH.get("carrito.total"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnAnadir.setText(mIH.get("boton.anadir"));
        btnGuardar.setText(mIH.get("boton.guardar"));
        btnLimpiar.setText(mIH.get("boton.limpiar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("carrito.anadir.titulo"));

        String[] columnas = {
                mIH.get("carrito.tabla.codigo"),
                mIH.get("carrito.tabla.nombre"),
                mIH.get("carrito.tabla.precio"),
                mIH.get("carrito.tabla.cantidad"),
                mIH.get("carrito.tabla.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);

        aplicarTextos();
    }

    public void cargarDatosProductos(Carrito carrito) {
        modelo.setRowCount(0);
        for (ItemCarrito item : carrito.obtenerItems()) {
            Object[] fila = {
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getSubtotal()
            };
            modelo.addRow(fila);
        }

    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JLabel getLblTitulo() {
        return LblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        LblTitulo = lblTitulo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
