package edu.ec.ups.vista;

import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoAnadirView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel LblTitulo;
    private JLabel LblCodigo;
    private JLabel LblNombre;
    private JLabel LblPrecio;

    private MensajeInternacionalizacionHandler mIH;

    public ProductoAnadirView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setContentPane(panelPrincipal);
        setTitle(mIH.get("producto.anadir.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setIconifiable(true);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("producto.anadir.titulo"));
        LblCodigo.setText(mIH.get("producto.codigo"));
        LblNombre.setText(mIH.get("producto.nombre"));
        LblPrecio.setText(mIH.get("producto.precio"));
        btnAceptar.setText(mIH.get("boton.aceptar"));
        btnLimpiar.setText(mIH.get("boton.limpiar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("producto.anadir.titulo"));
        aplicarTextos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarProdutos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
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

}
