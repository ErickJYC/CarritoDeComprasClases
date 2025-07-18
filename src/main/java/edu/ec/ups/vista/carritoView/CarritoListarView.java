package edu.ec.ups.vista.carritoView;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;
/**
 * Ventana interna que permite listar todos los carritos existentes y mostrar sus detalles.
 * También permite buscar un carrito específico por su código.
 */
public class CarritoListarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCarrito;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblProductos;
    private JLabel lblListar;
    private JLabel lblCodigo;
    private JButton btnMostrardetalle;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;
    /**
     * Constructor que inicializa la vista con los elementos gráficos y textos internacionalizados.
     * @param mi Manejador de internacionalización.
     */
    public CarritoListarView(MensajeInternacionalizacionHandler mi) {
        super(mi.get("carrito.listar.titulo"), true, true, false, true);
        this.mi = mi;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel(new Object[]{
                mi.get("carrito.listar.columna.codigo"),
                mi.get("carrito.listar.columna.fecha"),
                mi.get("carrito.listar.columna.subtotal"),
                mi.get("carrito.listar.columna.iva"),
                mi.get("carrito.listar.columna.total")
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProductos.setModel(modelo);

        cambiarIdioma();

        URL buscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if(buscar != null){
            ImageIcon icono = new ImageIcon(buscar);
            btnBuscar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL listar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if(listar != null){
            ImageIcon icono = new ImageIcon(listar);
            btnListar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL mostrar = LoginView.class.getClassLoader().getResource("imagenes/mostrar.png");
        if(mostrar != null){
            ImageIcon icono = new ImageIcon(mostrar);
            btnMostrardetalle.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
    }
    /**
     * Cambia los textos de la interfaz gráfica al idioma correspondiente.
     */
    public void cambiarIdioma() {
        setTitle(mi.get("carrito.listar.titulo"));
        lblListar.setText(mi.get("carrito.listar.etiqueta"));
        lblCodigo.setText(mi.get("carrito.listar.codigo"));
        btnBuscar.setText(mi.get("carrito.listar.boton.mostrar"));
        btnMostrardetalle.setText(mi.get("carrito.listar.boton.detalle"));
        btnListar.setText(mi.get("carrito.listar.boton.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mi.get("carrito.listar.columna.codigo"),
                mi.get("carrito.listar.columna.fecha"),
                mi.get("carrito.listar.columna.subtotal"),
                mi.get("carrito.listar.columna.iva"),
                mi.get("carrito.listar.columna.total")
        });
    }
    /**
     * Carga en la tabla los datos de una lista de carritos.
     * @param carritos Lista de objetos {@link Carrito}.
     */
    public void cargarDatos(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            modelo.addRow(new Object[]{
                    carrito.getCodigo(),
                    carrito.getFechaFormateada(),
                    carrito.calcularTotal(),
                    carrito.calcularIVA(),
                    carrito.calcularTotalConIVA()
            });
        }
    }
    /**
     * Muestra un mensaje emergente al usuario.
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    /**
     * Limpia los campos de texto y la tabla.
     */
    public void limpiarCampos() {
        txtCarrito.setText("");
        modelo.setNumRows(0);
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

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JLabel getLblListar() {
        return lblListar;
    }

    public void setLblListar(JLabel lblListar) {
        this.lblListar = lblListar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JButton getBtnMostrardetalle() {
        return btnMostrardetalle;
    }

    public void setBtnMostrardetalle(JButton btnMostrardetalle) {
        this.btnMostrardetalle = btnMostrardetalle;
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
}
