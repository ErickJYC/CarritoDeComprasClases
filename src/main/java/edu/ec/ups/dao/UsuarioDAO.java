package edu.ec.ups.dao;

import edu.ec.ups.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {

    // Método para autenticar un usuario con username y contraseña
    Usuario autenticar(String username, String contrasenia);

    // Crear un nuevo usuario
    void crear(Usuario usuario);

    // Buscar un usuario por su username
    Usuario buscarPorUsername(String username);

    // Eliminar un usuario dado su username
    void eliminar(String username);

    // Actualizar un usuario existente
    void actualizar(Usuario usuario);

    // Listar todos los usuarios
    List<Usuario> listarTodos();

    // Listar usuarios cuyo username empiece con el texto dado
    List<Usuario> listarPorUsername(String username);

}
