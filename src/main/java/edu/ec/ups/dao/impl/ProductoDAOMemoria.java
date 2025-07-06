package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDAOMemoria implements ProductoDAO {
    // Lista que almacena los productos en memoria
    private List<Producto> productos;

    // Constructor que inicializa la lista con productos predefinidos
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

    // Añade un producto a la lista
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    // Busca un producto por código
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    // Busca productos cuyo nombre inicia con la cadena proporcionada
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

    // Actualiza un producto existente en la lista
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    // Elimina un producto por código
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

    // Devuelve la lista completa de productos
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
