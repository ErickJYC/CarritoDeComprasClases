package edu.ec.ups.dao;

import edu.ec.ups.modelo.Usuario;
import java.util.List;
/**
 * Interfaz DAO (Data Access Object) para la gestión de usuarios.
 * Define operaciones CRUD y de autenticación sobre objetos {@link Usuario}.
 */
public interface UsuarioDAO {
    /**
     * Autentica un usuario verificando que el nombre de usuario y la contraseña coincidan.
     *
     * @param username Nombre de usuario (username)
     * @param contrasenia Contraseña del usuario
     * @return El {@link Usuario} autenticado si existe y coincide la contraseña, o {@code null} si no coincide o no existe
     */
    Usuario autenticar(String username, String contrasenia);

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario Objeto {@link Usuario} a registrar
     */
    void crear(Usuario usuario);

    /**
     * Busca un usuario existente por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar
     * @return El {@link Usuario} encontrado, o {@code null} si no existe
     */
    Usuario buscarPorUsername(String username);

    /**
     * Elimina un usuario del sistema según su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario a eliminar
     */
    void eliminar(String username);

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario Usuario con los datos actualizados
     */
    void actualizar(Usuario usuario);

    /**
     * Retorna una lista de todos los usuarios registrados.
     *
     * @return Lista completa de usuarios
     */
    List<Usuario> listarTodos();

    /**
     * Lista los usuarios cuyo nombre de usuario comience con una cadena específica.
     * No distingue entre mayúsculas y minúsculas.
     *
     * @param username Prefijo del nombre de usuario
     * @return Lista de usuarios que coincidan con el prefijo
     */
    List<Usuario> listarPorUsername(String username);

}
