package edu.ec.ups.controlador;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.vista.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView) {
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

        usuario = usuarioDAO.autenticar(username, contrasenia);
        loginView.mostrarMensaje("Llene toda la informacion");

        if(usuario == null){
            loginView.mostrarMensaje("Usuario e contraseña incorrectos.");
        }else{
            loginView.dispose();
        }
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
    private void registrar() {
        if(loginView.getTxtUsername().getText().isEmpty() || loginView.getTxtContrasenia().getText().isEmpty()){
            loginView.mostrarMensaje("Llena los campos para crear tu usuario");
        }else{
            Usuario usuarioRegistrado = new Usuario();
            usuarioRegistrado.setUsername(loginView.getTxtUsername().getText());
            usuarioRegistrado.setContrasenia(loginView.getTxtContrasenia().getText());
            usuarioRegistrado.setRol(Rol.USUARIO);
            usuarioDAO.crear(usuarioRegistrado);
        }
    }

}
