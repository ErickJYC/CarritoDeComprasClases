package edu.ec.ups.vista;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {

    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenuItem menuSalir;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemListarUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemActualizarUsuario;

    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemActualizarCarrito;
    private JMenuItem menuItemListarCarritos;
    private JMenuItem menuItemDetalleCarrito;
    private JMenuItem menuItemEliminarCarrito;

    private JMenuItem menuItemCerrarSesion;

    private MiDesktopPane jDesktopPane;

    public MenuPrincipalView() {
        jDesktopPane = new MiDesktopPane();
        menuBar = new JMenuBar();

        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");
        menuUsuario = new JMenu("Usuario");
        menuSalir = new JMenu("Salir");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");

        menuItemCrearCarrito = new JMenuItem("Crear Carrito");
        menuItemListarCarritos = new JMenuItem("Buscar Carritos");
        menuItemDetalleCarrito = new JMenuItem("Detalle Carrito");
        menuItemActualizarCarrito = new JMenuItem("Actualizar Carrito");
        menuItemEliminarCarrito = new JMenuItem("Eliminar Carrito");

        menuItemCrearUsuario = new JMenuItem("Crear Usuario");
        menuItemActualizarUsuario = new JMenuItem("Actualizar Usuario");
        menuItemListarUsuario = new JMenuItem("Buscar Usuario");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");

        menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuSalir);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemActualizarCarrito);
        menuCarrito.add(menuItemListarCarritos);
        menuCarrito.add(menuItemDetalleCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemActualizarUsuario);
        menuUsuario.add(menuItemListarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuSalir.add(menuItemCerrarSesion);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        jDesktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras En Línea");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    public JMenuItem getMenuSalir() {
        return menuSalir;
    }

    public void setMenuSalir(JMenuItem menuSalir) {
        this.menuSalir = menuSalir;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public void setMenuItemActualizarProducto(JMenuItem menuItemActualizarProducto) {
        this.menuItemActualizarProducto = menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }

    public void setMenuItemCrearUsuario(JMenuItem menuItemCrearUsuario) {
        this.menuItemCrearUsuario = menuItemCrearUsuario;
    }

    public JMenuItem getMenuItemListarUsuario() {
        return menuItemListarUsuario;
    }

    public void setMenuItemListarUsuario(JMenuItem menuItemListarUsuario) {
        this.menuItemListarUsuario = menuItemListarUsuario;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public void setMenuItemEliminarUsuario(JMenuItem menuItemEliminarUsuario) {
        this.menuItemEliminarUsuario = menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemActualizarUsuario() {
        return menuItemActualizarUsuario;
    }

    public void setMenuItemActualizarUsuario(JMenuItem menuItemActualizarUsuario) {
        this.menuItemActualizarUsuario = menuItemActualizarUsuario;
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public void setMenuItemCrearCarrito(JMenuItem menuItemCrearCarrito) {
        this.menuItemCrearCarrito = menuItemCrearCarrito;
    }

    public JMenuItem getMenuItemActualizarCarrito() {
        return menuItemActualizarCarrito;
    }

    public void setMenuItemActualizarCarrito(JMenuItem menuItemActualizarCarrito) {
        this.menuItemActualizarCarrito = menuItemActualizarCarrito;
    }

    public JMenuItem getMenuItemDetalleCarrito() {
        return menuItemDetalleCarrito;
    }

    public void setMenuItemDetalleCarrito(JMenuItem menuItemDetalleCarrito) {
        this.menuItemDetalleCarrito = menuItemDetalleCarrito;
    }

    public JMenuItem getMenuItemListarCarritos() {
        return menuItemListarCarritos;
    }

    public void setMenuItemListarCarritos(JMenuItem menuItemListarCarritos) {
        this.menuItemListarCarritos = menuItemListarCarritos;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) {
        this.menuItemEliminarCarrito = menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(MiDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemActualizarProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
        getMenuItemListarCarritos().setEnabled(false);
        getMenuItemCrearUsuario().setEnabled(false);
        getMenuItemListarUsuario().setEnabled(false);
        getMenuItemActualizarUsuario().setEnabled(false);
        getMenuItemEliminarUsuario().setEnabled(false);
    }
}