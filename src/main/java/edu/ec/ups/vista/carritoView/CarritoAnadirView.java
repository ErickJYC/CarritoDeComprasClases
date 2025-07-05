package edu.ec.ups.vista.carritoView;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

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
    private JLabel lblCarritoAnadir;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private Carrito carrito;
    DefaultTableModel modelo = new DefaultTableModel();
    private MensajeInternacionalizacionHandler mi;

    public CarritoAnadirView(MensajeInternacionalizacionHandler mi) {
        super("Carrito de Compras", true, true, false, true);
        this.mi = mi;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        cargarDatos();

        modelo = new DefaultTableModel();
        tblProductos.setModel(modelo);

        cambiarIdioma();

        URL buscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if(buscar != null){
            ImageIcon icono = new ImageIcon(buscar);
            btnBuscar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL anadir = LoginView.class.getClassLoader().getResource("imagenes/anadir.png");
        if(anadir != null){
            ImageIcon icono = new ImageIcon(anadir);
            btnAnadir.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL guardar = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
        if(guardar != null){
            ImageIcon icono = new ImageIcon(guardar);
            btnGuardar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL limpiar = LoginView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if(limpiar != null){
            ImageIcon icono = new ImageIcon(limpiar);
            btnLimpiar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
    }

    private void cargarDatos() {
        cbxCantidad.removeAllItems();
        for (int i = 0; i < 20; i++) {
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public void setBtnAnadir(JButton btnAnadir) {
        this.btnAnadir = btnAnadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public void setTxtSubtotal(JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
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

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    public JLabel getLblCarritoAnadir() {
        return lblCarritoAnadir;
    }

    public void setLblCarritoAnadir(JLabel lblCarritoAnadir) {
        this.lblCarritoAnadir = lblCarritoAnadir;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
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

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        cbxCantidad.setSelectedIndex(0);
        modelo.setRowCount(0);
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
    }

    public void cambiarIdioma() {
        mi.setLenguaje(mi.getLocale().getLanguage(), mi.getLocale().getCountry());

        lblCarritoAnadir.setText(mi.get("carrito.añadir.titulo"));
        lblCodigo.setText(mi.get("carrito.añadir.codigo"));
        lblNombre.setText(mi.get("carrito.añadir.nombre"));
        lblPrecio.setText(mi.get("carrito.añadir.precio"));
        lblCantidad.setText(mi.get("carrito.añadir.cantidad"));
        lblSubtotal.setText(mi.get("carrito.añadir.subtotal"));
        lblIva.setText(mi.get("carrito.añadir.iva"));
        lblTotal.setText(mi.get("carrito.añadir.total"));
        btnBuscar.setText(mi.get("carrito.añadir.boton.buscar"));
        btnAnadir.setText(mi.get("carrito.añadir.boton.anadir"));
        btnGuardar.setText(mi.get("carrito.añadir.boton.guardar"));
        btnLimpiar.setText(mi.get("carrito.añadir.boton.limpiar"));

        // Actualizar encabezados de la tabla
        modelo.setColumnIdentifiers(new Object[]{
                mi.get("carrito.añadir.tabla.codigo"),
                mi.get("carrito.añadir.tabla.nombre"),
                mi.get("carrito.añadir.tabla.precio"),
                mi.get("carrito.añadir.tabla.cantidad"),
                mi.get("carrito.añadir.tabla.subtotal")
        });
    }
}
