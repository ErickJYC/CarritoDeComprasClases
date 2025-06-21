package edu.ec.ups.controlador;

import edu.ec.ups.dao.UsuarioDAO;
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
    }

    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }else{
            loginView.dispose();
        }
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}
