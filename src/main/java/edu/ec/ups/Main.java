package edu.ec.ups;


import edu.ec.ups.controlador.CarritoController;
import edu.ec.ups.controlador.ProductoController;
import edu.ec.ups.controlador.UsuarioController;
import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.dao.impl.CarritoDAOMemoria;
import edu.ec.ups.dao.impl.PreguntaDAOMemoria;
import edu.ec.ups.dao.impl.ProductoDAOMemoria;
import edu.ec.ups.dao.impl.UsuarioDAOMemoria;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.*;
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

                    ProductoDAO productoDAO = new ProductoDAOMemoria();
                    CarritoDAO carritoDAO = new CarritoDAOMemoria();

                    PreguntaDAO cuestionarioDAO = new PreguntaDAOMemoria();
                    UsuarioDAO usuarioDAO = new UsuarioDAOMemoria(cuestionarioDAO);

                    LoginView loginView = new LoginView(mi);
                    loginView.setVisible(true);

                    UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, cuestionarioDAO, mi);

                    loginView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {

                            Usuario usuarioAuntenticado = usuarioController.getUsuarioAutenticado();
                            if (usuarioAuntenticado != null) {
                                //instanciamos DAO (Singleton)

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
}