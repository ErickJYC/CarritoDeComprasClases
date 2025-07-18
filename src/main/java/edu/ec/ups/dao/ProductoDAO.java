package edu.ec.ups.dao;

import edu.ec.ups.modelo.Producto;
import java.util.List;
/**
 * Interfaz DAO (Data Access Object) para definir las operaciones CRUD
 * relacionadas con los objetos {@link Producto}.
 */
public interface ProductoDAO {
    /**
     * Crea y guarda un nuevo producto en el sistema de almacenamiento.
     *
     * @param producto El objeto {@link Producto} a crear
     */
    void crear(Producto producto);

    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código identificador del producto
     * @return El {@link Producto} encontrado, o {@code null} si no existe
     */
    Producto buscarPorCodigo(int codigo);

    /**
     * Busca todos los productos cuyo nombre comience con el texto especificado.
     * La búsqueda no distingue entre mayúsculas y minúsculas.
     *
     * @param nombre Nombre o prefijo del nombre del producto
     * @return Lista de productos que coinciden con el criterio de búsqueda
     */
    List<Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un producto existente.
     * La identificación del producto se basa en su código.
     *
     * @param producto Producto con la información actualizada
     */
    void actualizar(Producto producto);

    /**
     * Elimina un producto del sistema según su código.
     *
     * @param codigo Código del producto a eliminar
     */
    void eliminar(int codigo);

    /**
     * Lista todos los productos disponibles en el sistema de almacenamiento.
     *
     * @return Lista completa de productos
     */
    List<Producto> listarTodos();
}
