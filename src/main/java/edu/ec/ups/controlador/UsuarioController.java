package edu.ec.ups.controlador;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioAnadirView usuarioAnadirView, UsuarioListarView usuarioListarView, UsuarioActualizarView usuarioActualizarView, UsuarioEliminarView usuarioEliminarView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas(){
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        if (username.isEmpty() || contrasenia.isEmpty()) {
            loginView.mostrarMensaje("Por favor, llena todos los campos.");
            return;
        }

        usuario = usuarioDAO.autenticar(username, contrasenia);

        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
            return;
        }

        loginView.mostrarMensaje("Sesión iniciada correctamente.");
        loginView.dispose();

    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
    private void registrar() {
        String username = loginView.getTxtUsername().getText();
        String contrasena = loginView.getTxtContrasenia().getText();

        if (username.isEmpty() || contrasena.isEmpty()) {
            loginView.mostrarMensaje("Llena los campos para crear tu usuario");
            return;
        }

        Usuario usuarioExistente = usuarioDAO.autenticar(username, contrasena);
        if (usuarioExistente != null) {
            loginView.mostrarMensaje("Ese nombre de usuario ya está en uso.");
            return;
        }

        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setContrasenia(contrasena);
        nuevo.setRol(Rol.USUARIO);

        usuarioDAO.crear(nuevo);
        loginView.mostrarMensaje("Usuario registrado correctamente. Ahora puedes iniciar sesión.");

    }
    public void cerrarSesion(){
        usuario = null;
        loginView.setVisible(true);

    }

}
