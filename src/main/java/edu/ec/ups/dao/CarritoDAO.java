package edu.ec.ups.dao;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.Usuario;

import java.util.List;

public interface CarritoDAO {

    // Crea un nuevo carrito
    void crear(Carrito carrito);

    // Busca un carrito por su código único
    Carrito buscarPorCodigo(int codigo);

    // Busca todos los carritos asociados a un usuario específico
    List<Carrito> buscarPorUsuario(Usuario usuario);

    // Actualiza los datos de un carrito existente
    void actualizar(Carrito carrito);

    // Elimina un carrito por su código
    void eliminar(int codigo);

    // Lista todos los carritos almacenados
    List<Carrito> listarTodos();

}
