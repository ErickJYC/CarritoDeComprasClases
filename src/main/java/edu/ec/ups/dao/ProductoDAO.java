package edu.ec.ups.dao;

import edu.ec.ups.modelo.Producto;
import java.util.List;

public interface ProductoDAO {
    // Crea un nuevo producto
    void crear(Producto producto);

    // Busca un producto por su código
    Producto buscarPorCodigo(int codigo);

    // Busca productos cuyo nombre empieza con el texto dado
    List<Producto> buscarPorNombre(String nombre);

    // Actualiza un producto existente
    void actualizar(Producto producto);

    // Elimina un producto por su código
    void eliminar(int codigo);

    // Lista todos los productos existentes
    List<Producto> listarTodos();
}
