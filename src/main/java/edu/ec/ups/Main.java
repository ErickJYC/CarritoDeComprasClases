package edu.ec.ups;


import edu.ec.ups.controlador.CarritoController;
import edu.ec.ups.controlador.ProductoController;
import edu.ec.ups.controlador.UsuarioController;
import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.dao.impl.*;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.MenuPrincipalView;
import edu.ec.ups.vista.SelectorAlmacenamientoDialog;
import edu.ec.ups.vista.carritoView.CarritoAnadirView;
import edu.ec.ups.vista.carritoView.CarritoEliminarView;
import edu.ec.ups.vista.carritoView.CarritoListarView;
import edu.ec.ups.vista.carritoView.CarritoModificarView;
import edu.ec.ups.vista.loginView.LoginView;
import edu.ec.ups.vista.loginView.RegistrarView;
import edu.ec.ups.vista.productoView.ProductoActualizarView;
import edu.ec.ups.vista.productoView.ProductoAnadirView;
import edu.ec.ups.vista.productoView.ProductoEliminarView;
import edu.ec.ups.vista.productoView.ProductoListaView;
import edu.ec.ups.vista.usuarioView.UsuarioCrearView;
import edu.ec.ups.vista.usuarioView.UsuarioEliminarView;
import edu.ec.ups.vista.usuarioView.UsuarioListarView;
import edu.ec.ups.vista.usuarioView.UsuarioModificarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                //Iniciar sesión
                MensajeInternacionalizacionHandler mi = new MensajeInternacionalizacionHandler("es", "EC");


                // Mostrar ventana para elegir almacenamiento
                SelectorAlmacenamientoDialog selector = new SelectorAlmacenamientoDialog(null, mi);
                selector.setVisible(true);

                int opcion = selector.getOpcionSeleccionada();
                String rutaArchivo = selector.getCarpetaSeleccionada();

                UsuarioDAO usuarioDAO;
                ProductoDAO productoDAO;
                CarritoDAO carritoDAO;
                PreguntaDAO cuestionarioDAO;

                switch (opcion) {
                    case 2 -> {
                        usuarioDAO = new UsuarioDAOArchivoTexto(rutaArchivo + "/usuarios.txt");
                        productoDAO = new ProductoDAOArchivoTexto(rutaArchivo + "/productos.txt");
                        carritoDAO = new CarritoDAOArchivoTexto(rutaArchivo + "/carritos.txt");
                        cuestionarioDAO = new PreguntaDAOArchivoTexto(rutaArchivo + "/preguntas.txt");
                        inicializarProductos(productoDAO);
                    }
                    case 3 -> {
                        usuarioDAO = new UsuarioDAOArchivoBinario(rutaArchivo + "/usuarios.dat");
                        productoDAO = new ProductoDAOArchivoBinario(rutaArchivo + "/productos.dat");
                        carritoDAO = new CarritoDAOArchivoBinario(rutaArchivo + "/carritos.dat");
                        cuestionarioDAO = new PreguntaDAOArchivoBinario(rutaArchivo + "/preguntas.dat");
                        inicializarProductos(productoDAO);
                    }
                    default -> {
                        cuestionarioDAO = new PreguntaDAOMemoria(); // primero creamos esto
                        usuarioDAO = new UsuarioDAOMemoria(cuestionarioDAO); // ya no es null
                        productoDAO = new ProductoDAOMemoria();
                        carritoDAO = new CarritoDAOMemoria();
                    }
                }

                LoginView loginView = new LoginView(mi);
                loginView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, cuestionarioDAO, mi);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {

                        Usuario usuarioAuntenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAuntenticado != null) {
                            //instanciamos DAO (Singleton)
                            // Asignar rol por defecto si es null
                            if (usuarioAuntenticado.getRol() == null) {
                                System.err.println("Atención: Usuario sin rol asignado. Se asigna Rol.USUARIO por defecto.");
                                usuarioAuntenticado.setRol(Rol.USUARIO);
                            }

                            //instancio Vistas
                            MenuPrincipalView principalView = new MenuPrincipalView(mi);
                            ProductoAnadirView productoAnadirView = new ProductoAnadirView(mi);
                            ProductoListaView productoListaView = new ProductoListaView(mi);
                            ProductoActualizarView productoActualizarView = new ProductoActualizarView(mi);
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView(mi);

                            //instancio Vistas de Carrito
                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mi);
                            CarritoListarView carritoListarView = new CarritoListarView(mi);
                            CarritoModificarView carritoModificarView = new CarritoModificarView(mi);
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView(mi);


                            //instanciamos las vistas de Usuario
                            UsuarioCrearView usuarioCrearView = new UsuarioCrearView(mi);
                            UsuarioListarView usuarioListarView = new UsuarioListarView(mi);
                            UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(mi);
                            UsuarioModificarView usuarioModificarView = new UsuarioModificarView(mi);

                            RegistrarView registrarView = new RegistrarView(mi);


                            //instanciamos Controladores
                            ProductoController productoController = new ProductoController(productoDAO, productoAnadirView, productoListaView, carritoAnadirView, productoEliminarView, productoActualizarView, mi);
                            CarritoController carritoController = new CarritoController(carritoDAO, carritoAnadirView, productoDAO, carritoListarView, usuarioAuntenticado, carritoModificarView, carritoEliminarView, mi);
                            UsuarioController usuarioController = new UsuarioController(usuarioDAO, usuarioCrearView, usuarioListarView, usuarioEliminarView, usuarioModificarView, mi, registrarView);


                            principalView.mostrarMensaje("Bienvenido: " + usuarioAuntenticado.getUsername());
                            if (usuarioAuntenticado.getRol().equals(Rol.USUARIO)) {
                                principalView.deshabilitarMenusAdministrador();
                            }
                            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoAnadirView.isVisible()) {
                                        productoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoAnadirView);
                                    }
                                }
                            });

                            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoListaView.isVisible()) {
                                        productoListaView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoListaView);
                                    }
                                }
                            });

                            principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoAnadirView.isVisible()) {
                                        carritoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });
                            principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoEliminarView.isVisible()) {
                                        productoEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoEliminarView);
                                    }
                                }
                            });
                            principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoActualizarView.isVisible()) {
                                        productoActualizarView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoActualizarView);
                                    }
                                }
                            });
                            principalView.getMenuItemBuscarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoListarView.isVisible()) {
                                        carritoListarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoListarView);
                                    }
                                }
                            });

                            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    boolean confirmado = principalView.mostrarMensajePregunta(mi.get("login.main_cerrarSesion"));
                                    if (confirmado) {
                                        principalView.dispose();
                                        loginView.setVisible(true);
                                        loginView.limpiarCampos();
                                    }
                                }
                            });
                            principalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioCrearView.isVisible()) {
                                        usuarioCrearView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioCrearView);
                                    }
                                }
                            });
                            principalView.getMenuItemListarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioListarView.isVisible()) {
                                        usuarioListarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioListarView);
                                    }
                                }
                            });
                            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioEliminarView.isVisible()) {
                                        usuarioEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioEliminarView);
                                    }
                                }
                            });
                            principalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoModificarView.isVisible()) {
                                        carritoModificarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoModificarView);
                                    }
                                }
                            });
                            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoEliminarView.isVisible()) {
                                        carritoEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoEliminarView);
                                    }
                                }
                            });
                            principalView.getMenuItemSalir().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    boolean confirmado = principalView.mostrarMensajePregunta(mi.get("login.main_salir"));
                                    if (confirmado) {
                                        principalView.dispose();
                                        System.exit(0);
                                    }
                                }
                            });
                            principalView.getMenuItemActualizarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioModificarView.isVisible()) {
                                        usuarioModificarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioModificarView);
                                    }
                                }
                            });
                            principalView.getMenuItemEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mi.setLenguaje("es", "EC");
                                    principalView.cambiarIdioma();
                                    carritoAnadirView.cambiarIdioma();
                                    carritoEliminarView.cambiarIdioma();
                                    carritoListarView.cambiarIdioma();
                                    carritoModificarView.cambiarIdioma();
                                    productoActualizarView.cambiarIdioma();
                                    productoAnadirView.cambiarIdioma();
                                    productoEliminarView.cambiarIdioma();
                                    productoListaView.cambiarIdioma();
                                    usuarioModificarView.cambiarIdioma();

                                }
                            });
                            principalView.getMenuItemIngles().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mi.setLenguaje("en", "US");
                                    principalView.cambiarIdioma();
                                    carritoAnadirView.cambiarIdioma();
                                    carritoEliminarView.cambiarIdioma();
                                    carritoListarView.cambiarIdioma();
                                    carritoModificarView.cambiarIdioma();
                                    productoActualizarView.cambiarIdioma();
                                    productoAnadirView.cambiarIdioma();
                                    productoEliminarView.cambiarIdioma();
                                    productoListaView.cambiarIdioma();
                                    usuarioModificarView.cambiarIdioma();
                                }
                            });
                            principalView.getMenuItemFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mi.setLenguaje("fr", "FR");
                                    principalView.cambiarIdioma();
                                    carritoAnadirView.cambiarIdioma();
                                    carritoEliminarView.cambiarIdioma();
                                    carritoListarView.cambiarIdioma();
                                    carritoModificarView.cambiarIdioma();
                                    productoActualizarView.cambiarIdioma();
                                    productoAnadirView.cambiarIdioma();
                                    productoEliminarView.cambiarIdioma();
                                    productoListaView.cambiarIdioma();
                                    usuarioModificarView.cambiarIdioma();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
    private static void inicializarProductos(ProductoDAO productoDAO) {
        if (productoDAO.listarTodos() == null || productoDAO.listarTodos().isEmpty()) {
            System.out.println("Cargando productos predefinidos...");
            productoDAO.crear(new Producto(100, "Laptop", 150.99));
            productoDAO.crear(new Producto(200, "Monitor", 200.98));
            productoDAO.crear(new Producto(300, "Teclado", 100.99));
            productoDAO.crear(new Producto(400, "Mouse", 120.99));
            productoDAO.crear(new Producto(500, "Smartphone", 1000.99));
            productoDAO.crear(new Producto(600, "Tablet", 1500.99));
            productoDAO.crear(new Producto(700, "Cinta de Retina", 100.99));
            productoDAO.crear(new Producto(800, "Pulsera", 0.99));
            productoDAO.crear(new Producto(900, "Medias", 10.99));
            System.out.println("✔ Productos predefinidos creados.");
        } else {
            System.out.println("✔ Productos ya existen, no se cargan nuevamente.");
        }
    }
}