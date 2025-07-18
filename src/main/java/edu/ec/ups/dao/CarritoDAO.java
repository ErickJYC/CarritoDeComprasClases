package edu.ec.ups.dao;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.Usuario;

import java.util.List;
/**
 * Interfaz DAO (Data Access Object) para gestionar operaciones CRUD relacionadas con la entidad {@link Carrito}.
 * Define los métodos que deben implementarse para persistencia en memoria, archivos, bases de datos, etc.
 */
public interface CarritoDAO {

    /**
     * Crea un nuevo carrito y lo almacena en el medio correspondiente.
     *
     * @param carrito Objeto {@link Carrito} a ser creado
     */
    void crear(Carrito carrito);

    /**
     * Busca un carrito específico utilizando su código único.
     *
     * @param codigo Código identificador del carrito
     * @return El {@link Carrito} encontrado o {@code null} si no existe
     */
    Carrito buscarPorCodigo(int codigo);

    /**
     * Busca y devuelve todos los carritos asociados a un usuario específico.
     *
     * @param usuario Objeto {@link Usuario} con el que están asociados los carritos
     * @return Lista de carritos del usuario
     */
    List<Carrito> buscarPorUsuario(Usuario usuario);

    /**
     * Actualiza los datos de un carrito existente.
     *
     * @param carrito Objeto {@link Carrito} con los datos actualizados
     */
    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito del sistema utilizando su código único.
     *
     * @param codigo Código identificador del carrito a eliminar
     */
    void eliminar(int codigo);

    /**
     * Devuelve una lista con todos los carritos almacenados.
     *
     * @return Lista de todos los objetos {@link Carrito}
     */
    List<Carrito> listarTodos();

}
