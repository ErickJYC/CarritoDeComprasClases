package edu.ec.ups.vista;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoListarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblCarritos;
    private JLabel LblTitulo;
    private JLabel LblCodigo;

    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoListarView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setTitle(mIH.get("carrito.listar.titulo"));
        setContentPane(panelPrincipal);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setResizable(true);

        modelo = new DefaultTableModel(new Object[]{
                mIH.get("carrito.tabla.codigo"),
                mIH.get("carrito.tabla.fecha"),
                mIH.get("carrito.tabla.usuario"),
                mIH.get("carrito.tabla.subtotal"),
                mIH.get("carrito.tabla.total")
        }, 0);
        tblCarritos.setModel(modelo);

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("carrito.listar.titulo"));
        LblCodigo.setText(mIH.get("carrito.codigo"));
        btnBuscar.setText(mIH.get("boton.buscar"));
        btnListar.setText(mIH.get("boton.listar"));

    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("carrito.listar.titulo"));

        modelo.setColumnIdentifiers(new Object[]{
                mIH.get("carrito.tabla.codigo"),
                mIH.get("carrito.tabla.fecha"),
                mIH.get("carrito.tabla.usuario"),
                mIH.get("carrito.tabla.subtotal"),
                mIH.get("carrito.tabla.total")
        });

        aplicarTextos();
    }

    public void cargargaDatosLista(List<Carrito> carritos) {
        modelo.setNumRows(0);
        for (Carrito carrito : carritos) {
            Object[] fila = {
                    carrito.getCodigo(),
                    carrito.getFechaFormateada(),
                    carrito.getUsuario().getUsername(),
                    carrito.calcularSubtotal(),
                    carrito.calcularTotal()
            };
            modelo.addRow(fila);
        }
    }

    public void cargargaDatos(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            String username = carrito.getUsuario() != null ? carrito.getUsuario().getUsername() : mIH.get("carrito.usuario.sin");
            Object[] fila = {
                    carrito.getCodigo(),
                    carrito.getFechaFormateada(),
                    username,
                    carrito.calcularSubtotal(),
                    carrito.calcularTotal()
            };
            modelo.addRow(fila);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
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

    public JTable getTblCarritos() {
        return tblCarritos;
    }

    public void setTblCarritos(JTable tblCarritos) {
        this.tblCarritos = tblCarritos;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
}
