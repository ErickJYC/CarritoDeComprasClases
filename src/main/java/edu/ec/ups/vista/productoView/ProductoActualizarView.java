package edu.ec.ups.vista.productoView;

import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;
/**
 * Ventana interna de Swing que permite actualizar productos existentes.
 * Utiliza internacionalización para soportar múltiples idiomas.
 */
public class ProductoActualizarView extends JInternalFrame{
    private JTextField textField2;
    private JTextField textField3;
    private JButton btnActualizar;
    private JPanel panelPrincipal;
    private JButton btnBuscar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblActualizar;
    private JTable table1;
    private JTextField textField1;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;
    /**
     * Constructor que inicializa los componentes de la vista y aplica internacionalización.
     * @param mi Manejador de internacionalización para textos multilingües.
     */
    public ProductoActualizarView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        cambiarIdioma(); // Traduce etiquetas y columnas al idioma actual

        URL buscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if(buscar != null){
            ImageIcon icono = new ImageIcon(buscar);
            btnBuscar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL actualizar = LoginView.class.getClassLoader().getResource("imagenes/actualizar.png");
        if(actualizar != null){
            ImageIcon icono = new ImageIcon(actualizar);
            btnActualizar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
    }
    /**
     * Cambia los textos de la interfaz al idioma actual usando el manejador de internacionalización.
     */
    public void cambiarIdioma() {
        setTitle(mi.get("producto.actualizar.titulo"));
        lblActualizar.setText(mi.get("producto.actualizar.encabezado"));
        lblCodigo.setText(mi.get("producto.actualizar.etiqueta.codigo"));
        lblNombre.setText(mi.get("producto.actualizar.etiqueta.nombre"));
        lblPrecio.setText(mi.get("producto.actualizar.etiqueta.precio"));
        btnBuscar.setText(mi.get("producto.actualizar.boton.buscar"));
        btnActualizar.setText(mi.get("producto.actualizar.boton.actualizar"));

        // Traducción de los encabezados de la tabla
        String[] columnas = {
                mi.get("producto.actualizar.columna.codigo"),
                mi.get("producto.actualizar.columna.nombre"),
                mi.get("producto.actualizar.columna.precio")
        };
        modelo.setColumnIdentifiers(columnas);
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
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

    public JLabel getLblActualizar() {
        return lblActualizar;
    }

    public void setLblActualizar(JLabel lblActualizar) {
        this.lblActualizar = lblActualizar;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
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
    /**
     * Carga la lista de productos en la tabla.
     * @param listaProductos Lista de productos a mostrar.
     */
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
    /**
     * Muestra un mensaje al usuario en forma de cuadro de diálogo.
     * @param mensaje El texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    /**
     * Limpia los campos del formulario y la tabla.
     */
    public void limpiarCampos() {
        textField1.setText("");
        modelo.setNumRows(0);
    }
}
