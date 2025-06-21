package edu.ec.ups.dao;

import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {

    Usuario autenticar(String username, String contrasenia);

    void crear(Usuario usuario);

    Usuario buscarPorUsername(String username);

    void eliminar(Usuario username);

    Usuario buscarPorUserName(String userName);

    void eliminar(String username);

    void actualizar(Usuario usuario);

    List<Usuario> listarTodos();

    List<Usuario> listarAdministradores();

    List<Usuario> listarUsuarios();

    List<Usuario> listarPorRol(Rol rol);


}
