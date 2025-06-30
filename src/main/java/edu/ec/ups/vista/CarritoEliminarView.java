package edu.ec.ups.vista;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoEliminarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCarrito;
    private JTable tblCarritos;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel LblTitulo;
    private JLabel LblCodigo;
    private CarritoDAO carritoDAO;
    DefaultTableModel modelo;


    public CarritoEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Carrito");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código","Fecha","Usuario","Subtotal","Total"};
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);
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

    public JTable getTblCarritos() {
        return tblCarritos;
    }

    public void setTblCarritos(JTable tblCarritos) {
        this.tblCarritos = tblCarritos;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public CarritoDAO getCarritoDAO() {
        return carritoDAO;
    }

    public void setCarritoDAO(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
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
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargargaDatosBusqueda(List<Carrito> carritos) {
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

    public void buscarPorCodigoCarrito(int codigo) {
        codigo = Integer.parseInt(txtCarrito.getText());
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
        if (carrito == null && getTxtCarrito().getText().isEmpty()) {
            mostrarMensaje("No existe el carrito");
        } else  {
            cargargaDatosBusqueda(List.of(carrito));
        }
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el carrito?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void limpiarCampos() {
        txtCarrito.setText("");
        modelo.setRowCount(0);
    }
}
