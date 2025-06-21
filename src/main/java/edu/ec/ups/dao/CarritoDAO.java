package edu.ec.ups.dao;

import edu.ec.ups.modelo.Carrito;

import java.util.List;

public interface CarritoDAO {

    void crear(Carrito carrito);

    Carrito buscarPorCodigo(int codigo);

    void actualizar(Carrito carrito);

    void eliminar(int codigo);

    List<Carrito> listarTodos();

    List<Carrito> buscarPorUsuario(String username);

}
