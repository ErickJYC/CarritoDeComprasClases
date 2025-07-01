package edu.ec.ups;


import edu.ec.ups.controlador.CarritoController;
import edu.ec.ups.controlador.ProductoController;
import edu.ec.ups.controlador.UsuarioController;
import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.dao.impl.CarritoDAOMemoria;
import edu.ec.ups.dao.impl.ProductoDAOMemoria;
import edu.ec.ups.dao.impl.UsuarioDAOMemoria;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
        ProductoDAO productoDAO = new ProductoDAOMemoria();
        CarritoDAO carritoDAO = new CarritoDAOMemoria();

        MensajeInternacionalizacionHandler mIH = new MensajeInternacionalizacionHandler("es", "EC");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {


                LoginView loginView = new LoginView(mIH);
                UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView(usuarioDAO,mIH);
                UsuarioListarView usuarioListarView = new UsuarioListarView(mIH);
                UsuarioActualizarView usuarioActualizarView = new UsuarioActualizarView(usuarioDAO,mIH);
                UsuarioEliminarView  usuarioEliminarView = new UsuarioEliminarView(mIH);
                loginView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO,loginView,
                        usuarioAnadirView, usuarioListarView,
                        usuarioActualizarView, usuarioEliminarView);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {

                        Usuario usuarioAuntenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAuntenticado != null) {

                            MenuPrincipalView principalView = new MenuPrincipalView(mIH);

                            ProductoAnadirView productoAnadirView = new ProductoAnadirView(mIH);
                            ProductoListaView productoListaView = new ProductoListaView(mIH);
                            ProductoModificarView productoModificarView = new ProductoModificarView(mIH);
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView(mIH);

                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(usuarioAuntenticado,mIH);
                            CarritoListarView carritoListarView = new CarritoListarView(mIH);
                            CarritoModificarView carritoModificarView = new CarritoModificarView(carritoDAO,mIH);
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView(mIH);
                            CarritoDetalleView carritoDetalleView = new CarritoDetalleView(mIH);

                            ProductoController productoController = new ProductoController(productoDAO, productoAnadirView,
                                    productoListaView, productoModificarView, productoEliminarView);

                            CarritoController carritoController = new CarritoController(carritoDAO, productoDAO,
                                    carritoAnadirView, carritoListarView, carritoModificarView, carritoEliminarView,
                                    carritoDetalleView, mIH);

                            principalView.mostrarMensaje(mIH.get("app.bienvenida") + ": " + usuarioAuntenticado.getUsername());

                            if (usuarioAuntenticado.getRol().equals(Rol.USUARIO)) {
                                principalView.deshabilitarMenusAdministrador();
                            }

                            // 👉 Aquí viene el cambio de idioma con Runnable
                            Runnable cambiarIdiomaRunnable = () -> {
                                String lang = mIH.getLocale().getLanguage();
                                String pais = mIH.getLocale().getCountry();

                                principalView.cambiarIdioma(lang, pais);
                                productoAnadirView.cambiarIdioma(lang, pais);
                                productoListaView.cambiarIdioma(lang, pais);
                                productoModificarView.cambiarIdioma(lang, pais);
                                productoEliminarView.cambiarIdioma(lang, pais);

                                carritoAnadirView.cambiarIdioma(lang, pais);
                                carritoListarView.cambiarIdioma(lang, pais);
                                carritoModificarView.cambiarIdioma(lang, pais);
                                carritoEliminarView.cambiarIdioma(lang, pais);
                                carritoDetalleView.cambiarIdioma(lang, pais);

                                usuarioAnadirView.cambiarIdioma(lang, pais);
                                usuarioListarView.cambiarIdioma(lang, pais);
                                usuarioActualizarView.cambiarIdioma(lang, pais);
                                usuarioEliminarView.cambiarIdioma(lang, pais);
                            };

                            principalView.getMenuItemEspanol().addActionListener(event -> {
                                mIH.setLenguaje("es", "EC");
                                cambiarIdiomaRunnable.run();
                            });

                            principalView.getMenuItemIngles().addActionListener(event -> {
                                mIH.setLenguaje("en", "US");
                                cambiarIdiomaRunnable.run();
                            });

                            principalView.getMenuItemFrances().addActionListener(event -> {
                                mIH.setLenguaje("fr", "FR");
                                cambiarIdiomaRunnable.run();
                            });

                            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoAnadirView.isVisible()){
                                        productoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoAnadirView);
                                    }
                                }
                            });

                            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoListaView.isVisible()){
                                        productoListaView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoListaView);
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
                                    if (!productoModificarView.isVisible()) {
                                        productoModificarView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoModificarView);
                                    }
                                }
                            });

                            principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoAnadirView.isVisible()){
                                        carritoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });

                            principalView.getMenuItemActualizarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoListarView.isVisible()){
                                        carritoModificarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoModificarView);
                                    }
                                }
                            });

                            principalView.getMenuItemListarCarritos().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoListaView.isVisible()){
                                        carritoListarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoListarView);
                                    }
                                }
                            });

                            principalView.getMenuItemDetalleCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoDetalleView.isVisible()) {
                                        carritoDetalleView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoDetalleView);
                                    }
                                }
                            });

                            principalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioAnadirView.isVisible()) {
                                        usuarioAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioAnadirView);
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

                            principalView.getMenuItemActualizarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioActualizarView.isVisible()) {
                                        usuarioActualizarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioActualizarView);
                                    }
                                }
                            });

                            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioEliminarView.isVisible()) {
                                        usuarioEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioEliminarView);
                                        System.out.println("Se activó");
                                    }
                                }
                            });

                            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.dispose();
                                    usuarioController.cerrarSesion();
                                }
                            });

                            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoAnadirView.isVisible()) {
                                        carritoEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoEliminarView);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}