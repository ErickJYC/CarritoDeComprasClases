package edu.ec.ups.vista.productoView;

import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
/**
 * Vista interna para añadir un nuevo producto al sistema.
 * Permite ingresar código, nombre y precio del producto.
 * Incluye soporte para internacionalización y manejo de eventos.
 */
public class ProductoAnadirView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lblNuevoP;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private MensajeInternacionalizacionHandler mi;
    /**
     * Constructor que inicializa la vista de añadir producto.
     * @param mi Manejador de internacionalización.
     */
    public ProductoAnadirView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        cambiarIdioma(); // Cargar textos internacionalizados

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        URL aceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if(aceptar != null){
            ImageIcon icono = new ImageIcon(aceptar);
            btnAceptar.setIcon(icono);
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
    /**
     * Cambia los textos visibles según el idioma actual del sistema.
     */
    public void cambiarIdioma() {
        setTitle(mi.get("producto.anadir.titulo"));
        lblNuevoP.setText(mi.get("producto.anadir.encabezado"));
        lblCodigo.setText(mi.get("producto.anadir.etiqueta.codigo"));
        lblNombre.setText(mi.get("producto.anadir.etiqueta.nombre"));
        lblPrecio.setText(mi.get("producto.anadir.etiqueta.precio"));
        btnAceptar.setText(mi.get("producto.anadir.boton.aceptar"));
        btnLimpiar.setText(mi.get("producto.anadir.boton.limpiar"));
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JLabel getLblNuevoP() {
        return lblNuevoP;
    }

    public void setLblNuevoP(JLabel lblNuevoP) {
        this.lblNuevoP = lblNuevoP;
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

    public MensajeInternacionalizacionHandler getMi() {
        return mi;
    }

    public void setMi(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }
    /**
     * Muestra un mensaje emergente al usuario.
     * @param mensaje Mensaje que se desea mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    /**
     * Limpia todos los campos de texto del formulario.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
    /**
     * Muestra por consola los productos registrados (solo para pruebas).
     * @param productos Lista de productos a mostrar.
     */
    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

}
