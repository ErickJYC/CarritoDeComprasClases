package edu.ec.ups.vista;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {

    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuIdioma;
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

    private JMenuItem menuItemEspanol;
    private JMenuItem menuItemIngles;
    private JMenuItem menuItemFrances;

    private JMenuItem menuItemCerrarSesion;

    private MiDesktopPane jDesktopPane;
    private MensajeInternacionalizacionHandler mIH;


    public MenuPrincipalView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        jDesktopPane = new MiDesktopPane();
        setTitle(this.mIH.get("app.titulo"));

        menuBar = new JMenuBar();
        menuProducto = new JMenu(this.mIH.get("menu.producto"));
        menuCarrito = new JMenu(this.mIH.get("menu.carrito"));
        menuUsuario = new JMenu(this.mIH.get("menu.usuario"));
        menuIdioma = new JMenu(this.mIH.get("menu.idioma"));
        menuSalir = new JMenu(this.mIH.get("menu.salir"));

        menuItemCrearProducto = new JMenuItem(this.mIH.get("menu.producto.crear"));
        menuItemActualizarProducto = new JMenuItem(this.mIH.get("menu.producto.actualizar"));
        menuItemEliminarProducto = new JMenuItem(this.mIH.get("menu.producto.eliminar"));
        menuItemBuscarProducto = new JMenuItem(this.mIH.get("menu.producto.buscar"));

        menuItemCrearCarrito = new JMenuItem(this.mIH.get("menu.carrito.crear"));
        menuItemListarCarritos = new JMenuItem(this.mIH.get("menu.carrito.buscar"));
        menuItemDetalleCarrito = new JMenuItem(this.mIH.get("menu.carrito.detalle"));
        menuItemActualizarCarrito = new JMenuItem(this.mIH.get("menu.carrito.actualizar"));
        menuItemEliminarCarrito = new JMenuItem(this.mIH.get("menu.carrito.eliminar"));

        menuItemCrearUsuario = new JMenuItem(this.mIH.get("menu.usuario.crear"));
        menuItemActualizarUsuario = new JMenuItem(this.mIH.get("menu.usuario.actualizar"));
        menuItemListarUsuario = new JMenuItem(this.mIH.get("menu.usuario.buscar"));
        menuItemEliminarUsuario = new JMenuItem(this.mIH.get("menu.usuario.eliminar"));

        menuItemCerrarSesion = new JMenuItem(this.mIH.get("menu.salir.cerrar"));

        menuItemEspanol = new JMenuItem("Español"); // o mIH.get("idioma.es")
        menuItemIngles = new JMenuItem("English");  // o mIH.get("idioma.en")
        menuItemFrances = new JMenuItem("Français"); // o mIH.get("idioma.fr")


        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
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

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);

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

    public JMenuItem getMenuItemEspanol() {
        return menuItemEspanol;
    }

    public void setMenuItemEspanol(JMenuItem menuItemEspanol) {
        this.menuItemEspanol = menuItemEspanol;
    }

    public JMenuItem getMenuItemIngles() {
        return menuItemIngles;
    }

    public void setMenuItemIngles(JMenuItem menuItemIngles) {
        this.menuItemIngles = menuItemIngles;
    }

    public JMenuItem getMenuItemFrances() {
        return menuItemFrances;
    }

    public void setMenuItemFrances(JMenuItem menuItemFrances) {
        this.menuItemFrances = menuItemFrances;
    }

    public MensajeInternacionalizacionHandler getmIH() {
        return mIH;
    }

    public void setmIH(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
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
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("app.titulo"));

        menuProducto.setText(mIH.get("menu.producto"));
        menuCarrito.setText(mIH.get("menu.carrito"));
        menuUsuario.setText(mIH.get("menu.usuario"));
        menuSalir.setText(mIH.get("menu.salir"));

        menuItemCrearProducto.setText(mIH.get("menu.producto.crear"));
        menuItemActualizarProducto.setText(mIH.get("menu.producto.actualizar"));
        menuItemEliminarProducto.setText(mIH.get("menu.producto.eliminar"));
        menuItemBuscarProducto.setText(mIH.get("menu.producto.buscar"));

        menuItemCrearCarrito.setText(mIH.get("menu.carrito.crear"));
        menuItemActualizarCarrito.setText(mIH.get("menu.carrito.actualizar"));
        menuItemListarCarritos.setText(mIH.get("menu.carrito.buscar"));
        menuItemDetalleCarrito.setText(mIH.get("menu.carrito.detalle"));
        menuItemEliminarCarrito.setText(mIH.get("menu.carrito.eliminar"));

        menuItemCrearUsuario.setText(mIH.get("menu.usuario.crear"));
        menuItemActualizarUsuario.setText(mIH.get("menu.usuario.actualizar"));
        menuItemListarUsuario.setText(mIH.get("menu.usuario.buscar"));
        menuItemEliminarUsuario.setText(mIH.get("menu.usuario.eliminar"));

        menuItemCerrarSesion.setText(mIH.get("menu.salir.cerrar"));
    }

}