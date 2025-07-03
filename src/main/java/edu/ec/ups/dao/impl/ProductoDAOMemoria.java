package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDAOMemoria  implements ProductoDAO {
    private List<Producto> productos;

    public ProductoDAOMemoria() {
        productos = new ArrayList<Producto>();
        crear(new Producto(1, "Laptop", 150.99));
        crear(new Producto(2, "Monitor", 200.98));
        crear(new Producto(3, "Teclado", 100.99));
        crear(new Producto(4, "Mouse", 120.99));
        crear(new Producto(5, "Smartphone", 1000.99));
        crear(new Producto(6, "Tablet", 1500.99));
        crear(new Producto(7, "Cinta de Retina", 100.99));
        crear(new Producto(8, "Pulsera", 0.99));
        crear(new Producto(9, "Medias", 10.99));
    }

    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

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

    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
