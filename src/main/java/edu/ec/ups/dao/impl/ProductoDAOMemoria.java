package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de la interfaz ProductoDAO que utiliza almacenamiento en memoria.
 * Permite gestionar productos sin necesidad de persistencia en disco.
 */
public class ProductoDAOMemoria implements ProductoDAO {
    /** Lista que almacena los productos en memoria */
    private List<Producto> productos;

    /**
     * Constructor que inicializa la lista de productos con valores predefinidos.
     */
    public ProductoDAOMemoria() {
        productos = new ArrayList<>();
        crear(new Producto(100, "Laptop", 150.99));
        crear(new Producto(200, "Monitor", 200.98));
        crear(new Producto(300, "Teclado", 100.99));
        crear(new Producto(400, "Mouse", 120.99));
        crear(new Producto(500, "Smartphone", 1000.99));
        crear(new Producto(600, "Tablet", 1500.99));
        crear(new Producto(700, "Cinta de Retina", 100.99));
        crear(new Producto(800, "Pulsera", 0.99));
        crear(new Producto(900, "Medias", 10.99));
    }

    /**
     * Crea y agrega un producto a la lista en memoria.
     *
     * @param producto Producto a agregar
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto a buscar
     * @return Producto encontrado o null si no existe
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca productos cuyo nombre comienza con la cadena especificada.
     *
     * @param nombre Prefijo del nombre del producto
     * @return Lista de productos coincidentes
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().startsWith(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    /**
     * Actualiza un producto existente en la lista, buscándolo por su código.
     *
     * @param producto Producto con los datos actualizados
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    /**
     * Elimina un producto de la lista según su código.
     *
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Retorna una lista de todos los productos almacenados en memoria.
     *
     * @return Lista de productos
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
