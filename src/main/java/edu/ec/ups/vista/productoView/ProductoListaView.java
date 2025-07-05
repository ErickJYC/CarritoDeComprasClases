package edu.ec.ups.vista.productoView;

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
    private JLabel lblNombre;
    private JLabel lblLista;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public ProductoListaView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle(mi.get("producto.lista.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mi.get("producto.lista.columna.codigo"),
                mi.get("producto.lista.columna.nombre"),
                mi.get("producto.lista.columna.precio")
        };
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cambiarIdioma();
    }

    public void cambiarIdioma() {
        setTitle(mi.get("producto.lista.titulo"));

        lblLista.setText(mi.get("producto.lista.etiqueta.lista"));
        lblNombre.setText(mi.get("producto.lista.etiqueta.nombre"));

        btnBuscar.setText(mi.get("producto.lista.boton.buscar"));
        btnListar.setText(mi.get("producto.lista.boton.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mi.get("producto.lista.columna.codigo"),
                mi.get("producto.lista.columna.nombre"),
                mi.get("producto.lista.columna.precio")
        });
    }

    // Getters y setters


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

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
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

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblLista() {
        return lblLista;
    }

    public void setLblLista(JLabel lblLista) {
        this.lblLista = lblLista;
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

    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }
}
