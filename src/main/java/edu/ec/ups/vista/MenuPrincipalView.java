package edu.ec.ups.vista;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import java.net.URL;
/**
 * Clase que representa la ventana principal del sistema.
 * Contiene menús para gestionar productos, carritos, usuarios e idioma.
 * Integra internacionalización para cambiar dinámicamente los textos.
 */
public class MenuPrincipalView extends JFrame {

    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuIdioma;
    private JMenu menuSalir;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemBuscarCarrito;
    private JMenuItem menuItemModificarCarrito;
    private JMenuItem menuItemEliminarCarrito;


    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemListarUsuario;
    private JMenuItem menuItemActualizarUsuario;
    private JMenuItem menuItemEliminarUsuario;

    private JMenuItem menuItemEspanol;
    private JMenuItem menuItemIngles;
    private JMenuItem menuItemFrances;

    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemSalir;


    private MiDesktopPane jDesktopPane;
    private MensajeInternacionalizacionHandler mi;
    /**
     * Constructor que inicializa los menús, ítems y configuración principal de la ventana.
     *
     * @param mi Manejador de internacionalización que permite cambiar dinámicamente el idioma.
     */
    public MenuPrincipalView(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;

        jDesktopPane = new MiDesktopPane();
        menuBar = new JMenuBar();

        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");
        menuUsuario = new JMenu("Usuario");
        menuIdioma = new JMenu("Idioma");
        menuSalir = new JMenu("Salir");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");

        menuItemCrearCarrito = new JMenuItem("Crear Carrito");
        menuItemBuscarCarrito = new JMenuItem("Buscar Carrito");
        menuItemModificarCarrito = new JMenuItem("Actualizar Carrito");
        menuItemEliminarCarrito = new JMenuItem("Eliminar Carrito");

        menuItemCrearUsuario = new JMenuItem("Crear Usuario");
        menuItemListarUsuario = new JMenuItem("Buscar Usuario");
        menuItemActualizarUsuario = new JMenuItem("Actualizar Usuario");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");

        menuItemEspanol = new JMenuItem("Español");
        menuItemIngles = new JMenuItem("Inglés");
        menuItemFrances = new JMenuItem("Francés");

        menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");
        menuItemSalir = new JMenuItem("Salir");


        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
        menuBar.add(menuSalir);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto.add(menuItemActualizarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemBuscarCarrito);
        menuCarrito.add(menuItemModificarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemEliminarUsuario);
        menuUsuario.add(menuItemListarUsuario);
        menuUsuario.add(menuItemActualizarUsuario);

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);

        menuSalir.add(menuItemCerrarSesion);
        menuSalir.add(menuItemSalir);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras En Línea");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        cambiarIdioma();

        URL carrito = LoginView.class.getClassLoader().getResource("imagenes/carrito.png");
        if (carrito != null) {
            ImageIcon icono = new ImageIcon(carrito);
            menuCarrito.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

        URL usuario = LoginView.class.getClassLoader().getResource("imagenes/usuario.png");
        if (usuario != null) {
            ImageIcon icono = new ImageIcon(usuario);
            menuUsuario.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL idioma = LoginView.class.getClassLoader().getResource("imagenes/idioma.png");
        if (idioma != null) {
            ImageIcon icono = new ImageIcon(idioma);
            menuIdioma.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL producto = LoginView.class.getClassLoader().getResource("imagenes/producto.png");
        if (producto != null) {
            ImageIcon icono = new ImageIcon(producto);
            menuProducto.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL salir = LoginView.class.getClassLoader().getResource("imagenes/salir.png");
        if (salir != null) {
            ImageIcon icono = new ImageIcon(salir);
            menuSalir.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL carritoAnadir = LoginView.class.getClassLoader().getResource("imagenes/crear.png");
        if (carritoAnadir != null) {
            ImageIcon icono = new ImageIcon(carritoAnadir);
            menuItemCrearCarrito.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL productoAnadir = LoginView.class.getClassLoader().getResource("imagenes/crear.png");
        if (productoAnadir != null) {
            ImageIcon icono = new ImageIcon(productoAnadir);
            menuItemCrearProducto.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL usuarioAnadir = LoginView.class.getClassLoader().getResource("imagenes/crear.png");
        if (usuarioAnadir != null) {
            ImageIcon icono = new ImageIcon(usuarioAnadir);
            menuItemCrearUsuario.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL carritoEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (carritoEliminar != null) {
            ImageIcon icono = new ImageIcon(carritoEliminar);
            menuItemEliminarCarrito.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL usuarioEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (usuarioEliminar != null) {
            ImageIcon icono = new ImageIcon(usuarioEliminar);
            menuItemEliminarUsuario.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL productoEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (productoEliminar != null) {
            ImageIcon icono = new ImageIcon(productoEliminar);
            menuItemEliminarProducto.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL carritoListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (carritoListar != null) {
            ImageIcon icono = new ImageIcon(carritoListar);
            menuItemBuscarCarrito.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL usuarioListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (usuarioListar != null) {
            ImageIcon icono = new ImageIcon(usuarioListar);
            menuItemListarUsuario.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL productoListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (productoListar != null) {
            ImageIcon icono = new ImageIcon(productoListar);
            menuItemBuscarProducto.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL carritoActualizar = LoginView.class.getClassLoader().getResource("imagenes/actualizar.png");
        if (carritoActualizar != null) {
            ImageIcon icono = new ImageIcon(carritoActualizar);
            menuItemModificarCarrito.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL usuarioActualizar = LoginView.class.getClassLoader().getResource("imagenes/actualizar.png");
        if (usuarioActualizar != null) {
            ImageIcon icono = new ImageIcon(usuarioActualizar);
            menuItemActualizarUsuario.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL productoActualizar = LoginView.class.getClassLoader().getResource("imagenes/actualizar.png");
        if (productoActualizar != null) {
            ImageIcon icono = new ImageIcon(productoActualizar);
            menuItemActualizarProducto.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL idiomaEC = LoginView.class.getClassLoader().getResource("imagenes/espanol.png");
        if (idiomaEC != null) {
            ImageIcon icono = new ImageIcon(idiomaEC);
            menuItemEspanol.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL idiomaUS = LoginView.class.getClassLoader().getResource("imagenes/ingles.png");
        if (idiomaUS != null) {
            ImageIcon icono = new ImageIcon(idiomaUS);
            menuItemIngles.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL idiomaFR = LoginView.class.getClassLoader().getResource("imagenes/frances.png");
        if (idiomaFR != null) {
            ImageIcon icono = new ImageIcon(idiomaFR);
            menuItemFrances.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL cerrarSesion = LoginView.class.getClassLoader().getResource("imagenes/cerrarSesion.png");
        if (cerrarSesion != null) {
            ImageIcon icono = new ImageIcon(cerrarSesion);
            menuItemCerrarSesion.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL salirTodo = LoginView.class.getClassLoader().getResource("imagenes/salirTodo.png");
        if (salirTodo != null) {
            ImageIcon icono = new ImageIcon(salirTodo);
            menuItemSalir.setIcon(icono);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }


    }
    /**
     * Método que desactiva ciertos ítems del menú para usuarios con rol administrador.
     * Esto permite limitar el acceso a funcionalidades según el rol.
     */
    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemActualizarProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
        getMenuItemCrearUsuario().setEnabled(false);
        getMenuItemListarUsuario().setEnabled(false);
        getMenuItemActualizarUsuario().setEnabled(false);
        getMenuItemEliminarUsuario().setEnabled(false);
    }
    /**
     * Cambia los textos de todos los menús e ítems del sistema de acuerdo al idioma seleccionado.
     * Utiliza las claves del archivo de propiedades asociado a la localización actual.
     */
    public void cambiarIdioma() {
        if (mi == null) return;

        // Menús principales
        menuProducto.setText(mi.get("menu.producto"));
        menuCarrito.setText(mi.get("menu.carrito"));
        menuUsuario.setText(mi.get("menu.usuario"));
        menuIdioma.setText(mi.get("menu.idioma"));
        menuSalir.setText(mi.get("menu.salir"));

        // Items de Producto
        menuItemCrearProducto.setText(mi.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mi.get("menu.producto.eliminar"));
        menuItemActualizarProducto.setText(mi.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mi.get("menu.producto.buscar"));

        // Items de Carrito
        menuItemCrearCarrito.setText(mi.get("menu.carrito.crear"));
        menuItemBuscarCarrito.setText(mi.get("menu.carrito.buscar"));
        menuItemModificarCarrito.setText(mi.get("menu.carrito.actualizar"));
        menuItemEliminarCarrito.setText(mi.get("menu.carrito.eliminar"));

        // Items de Usuario
        menuItemCrearUsuario.setText(mi.get("menu.usuario.crear"));
        menuItemListarUsuario.setText(mi.get("menu.usuario.buscar"));
        menuItemActualizarUsuario.setText(mi.get("menu.usuario.actualizar"));
        menuItemEliminarUsuario.setText(mi.get("menu.usuario.eliminar"));

        // Idiomas
        menuItemEspanol.setText(mi.get("Español"));
        menuItemIngles.setText(mi.get("Inglés"));
        menuItemFrances.setText(mi.get("Frances"));

        // Salir
        menuItemCerrarSesion.setText(mi.get("menu.salir.cerrar"));
        menuItemSalir.setText(mi.get("menu.salir.salir"));

        // Título
        setTitle(mi.get("titulo.ventana"));
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

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public void setMenuItemCrearCarrito(JMenuItem menuItemCrearCarrito) {
        this.menuItemCrearCarrito = menuItemCrearCarrito;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    /**
     * Muestra un mensaje de información simple en una ventana emergente.
     *
     * @param mensaje El mensaje que se desea mostrar al usuario.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
    }

    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) {
        this.menuItemBuscarCarrito = menuItemBuscarCarrito;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    public JMenu getMenuSalir() {
        return menuSalir;
    }

    public void setMenuSalir(JMenu menuSalir) {
        this.menuSalir = menuSalir;
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

    public void setMenuItemListarUsuario(JMenuItem menuItemBuscarUsuario) {
        this.menuItemListarUsuario = menuItemBuscarUsuario;
    }

    public JMenuItem getMenuItemActualizarUsuario() {
        return menuItemActualizarUsuario;
    }

    public void setMenuItemActualizarUsuario(JMenuItem menuItemActualizarUsuario) {
        this.menuItemActualizarUsuario = menuItemActualizarUsuario;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public void setMenuItemEliminarUsuario(JMenuItem menuItemEliminarUsuario) {
        this.menuItemEliminarUsuario = menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }

    public void setMenuItemModificarCarrito(JMenuItem menuItemModificarCarrito) {
        this.menuItemModificarCarrito = menuItemModificarCarrito;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) {
        this.menuItemEliminarCarrito = menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemSalir() {
        return menuItemSalir;
    }

    public void setMenuItemSalir(JMenuItem menuItemSalir) {
        this.menuItemSalir = menuItemSalir;
    }

    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    public void setMenuIdioma(JMenu menuIdioma) {
        this.menuIdioma = menuIdioma;
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
    /**
     * Muestra un mensaje de confirmación con opciones Sí/No.
     *
     * @param mensaje El texto de la pregunta a mostrar.
     * @return true si el usuario selecciona \"Sí\", false en caso contrario.
     */
    public boolean mostrarMensajePregunta(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(this, mensaje, "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }
}