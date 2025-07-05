package edu.ec.ups.vista.productoView;

import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;

public class ProductoEliminarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton btnBuscar;
    private JTable table1;
    private JButton btnEliminar;
    private JLabel lblCodigo;
    private JLabel lblEliminar;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public ProductoEliminarView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle(mi.get("producto.eliminar.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mi.get("producto.eliminar.columna.codigo"),
                mi.get("producto.eliminar.columna.nombre"),
                mi.get("producto.eliminar.columna.precio")
        };
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);

        cambiarIdioma();

        URL buscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if(buscar != null){
            ImageIcon icono = new ImageIcon(buscar);
            btnBuscar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL eliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if(eliminar != null){
            ImageIcon icono = new ImageIcon(eliminar);
            btnEliminar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
    }

    public void cambiarIdioma() {
        setTitle(mi.get("producto.eliminar.titulo"));

        lblCodigo.setText(mi.get("producto.eliminar.etiqueta.codigo"));
        lblEliminar.setText(mi.get("producto.eliminar.etiqueta.eliminar"));

        btnBuscar.setText(mi.get("producto.eliminar.boton.buscar"));
        btnEliminar.setText(mi.get("producto.eliminar.boton.eliminar"));

        // Actualizar encabezados de tabla
        modelo.setColumnIdentifiers(new Object[]{
                mi.get("producto.eliminar.columna.codigo"),
                mi.get("producto.eliminar.columna.nombre"),
                mi.get("producto.eliminar.columna.precio")
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblEliminar() {
        return lblEliminar;
    }

    public void setLblEliminar(JLabel lblEliminar) {
        this.lblEliminar = lblEliminar;
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        textField1.setText("");
        modelo.setNumRows(0);
    }

}
