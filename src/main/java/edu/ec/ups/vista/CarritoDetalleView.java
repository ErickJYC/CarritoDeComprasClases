package edu.ec.ups.vista;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoDetalleView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCodigocarrito;
    private JTable tblDetallecarrito;
    private JButton btnBuscar;
    private JLabel LblTitulo;
    private JLabel LblCodigocarrito;
    private Carrito carrito;
    DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoDetalleView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;

        setTitle(mIH.get("carrito.detalle.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setClosable(true);
        setResizable(true);
        setIconifiable(true);

        modelo = new DefaultTableModel(
                new Object[]{
                        mIH.get("carrito.tabla.codigo"),
                        mIH.get("carrito.tabla.producto"),
                        mIH.get("carrito.tabla.cantidad"),
                        mIH.get("carrito.tabla.total")
                }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDetallecarrito.setModel(modelo);

        aplicarTextos();
    }

    private void aplicarTextos() {
        LblTitulo.setText(mIH.get("carrito.detalle.titulo"));
        LblCodigocarrito.setText(mIH.get("carrito.detalle.codigo"));
        btnBuscar.setText(mIH.get("boton.buscar"));
    }

    public void cambiarIdioma(String lang, String pais) {
        mIH.setLenguaje(lang, pais);
        setTitle(mIH.get("carrito.detalle.titulo"));

        modelo.setColumnIdentifiers(new Object[]{
                mIH.get("carrito.tabla.codigo"),
                mIH.get("carrito.tabla.producto"),
                mIH.get("carrito.tabla.cantidad"),
                mIH.get("carrito.tabla.total")
        });

        aplicarTextos();
    }

    public void cargarDatosCarrito(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            for (ItemCarrito itemCarrito : carrito.obtenerItems()) {
                Producto producto = itemCarrito.getProducto();
                Object[] fila = {
                        producto.getCodigo(),
                        producto.getNombre(),
                        itemCarrito.getCantidad(),
                        producto.getPrecio() * itemCarrito.getCantidad()
                };
                modelo.addRow(fila);
            }
        }
    }

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s, mIH.get("mensaje.informacion"), JOptionPane.INFORMATION_MESSAGE);
    }



    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtCodigocarrito() {
        return txtCodigocarrito;
    }

    public void setTxtCodigocarrito(JTextField txtCodigocarrito) {
        this.txtCodigocarrito = txtCodigocarrito;
    }

    public JTable getTblDetallecarrito() {
        return tblDetallecarrito;
    }

    public void setTblDetallecarrito(JTable tblDetallecarrito) {
        this.tblDetallecarrito = tblDetallecarrito;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public JLabel getLblTitulo() {
        return LblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        LblTitulo = lblTitulo;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

}
