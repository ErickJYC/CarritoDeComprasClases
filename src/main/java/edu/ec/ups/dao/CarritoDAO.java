package edu.ec.ups.dao;

import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.Usuario;

import java.util.List;

public interface CarritoDAO {

    void crear(Carrito carrito);

    Carrito buscarPorCodigo(int codigo);

    List<Carrito> buscarPorUsuario(Usuario usuario);

    void actualizar(Carrito carrito);

    void eliminar(int codigo);

    List<Carrito> listarTodos();

}
