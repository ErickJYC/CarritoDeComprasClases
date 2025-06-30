package edu.ec.ups.vista;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoModificarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JButton btnActualizar;
    private JComboBox cbxCantidad;
    private Carrito carritoActual;
    private CarritoDAO carritoDAO;
    DefaultTableModel modelo;


    public CarritoModificarView(CarritoDAO carritoDAO) {
        setContentPane(panelPrincipal);
        setTitle("Actualizar Carrito");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setResizable(true);
        this.carritoDAO = carritoDAO;
        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Producto", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatosProductos();int cantidad = Integer.parseInt((String) cbxCantidad.getSelectedItem());
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

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    public Carrito getCarritoActual() {
        return carritoActual;
    }

    public void setCarritoActual(Carrito carritoActual) {
        this.carritoActual = carritoActual;
    }

    public CarritoDAO getCarritoDAO() {
        return carritoDAO;
    }

    public void setCarritoDAO(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void obtenerCarritoSeleccionado() {
        int codigo = Integer.parseInt(getTxtCodigo().getText());
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
        setCarritoActual(carrito);
        System.out.println("Carrito seleccionado: " + carrito);
    }

    private void cargarDatosProductos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
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

    public Producto itemSeleccionado(int fila) {
        if (fila >= 0 && fila < modelo.getRowCount()) {
            int codigoProducto = (int) modelo.getValueAt(fila, 0);
            String nombreProducto = (String) modelo.getValueAt(fila, 1);
            int cantidad = (int) modelo.getValueAt(fila, 2);
            double total = (double) modelo.getValueAt(fila, 3);

            return new Producto(codigoProducto, nombreProducto, total / cantidad);
        }
        return null;
    }

    public void actualizaCantidadProductoSeleccionado(Producto producto){
        int filaSeleccionada = tblProductos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            int nuevaCantidad = Integer.parseInt(cbxCantidad.getSelectedItem().toString());
            double total = producto.getPrecio() * nuevaCantidad;

            modelo.setValueAt(producto.getCodigo(), filaSeleccionada, 0);
            modelo.setValueAt(producto.getNombre(), filaSeleccionada, 1);
            modelo.setValueAt(nuevaCantidad, filaSeleccionada, 2);
            modelo.setValueAt(total, filaSeleccionada, 3);

            if (carritoActual != null) {
                for (ItemCarrito item : carritoActual.obtenerItems()) {
                    if (item.getProducto().getCodigo() == producto.getCodigo()) {
                        item.setCantidad(nuevaCantidad);
                        break;
                    }
                }
            }
        } else {
            mostrarMensaje("Seleccione un producto para actualizar la cantidad.");
        }
    }


    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
