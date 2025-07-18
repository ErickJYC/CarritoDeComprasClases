package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOArchivoBinario implements ProductoDAO {

    private String rutaArchivo;
    private List<Producto> productos;

    public ProductoDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
                productos = new ArrayList<>();
                guardarProductos(productos);
            } else {
                productos = cargarProductos();
            }
        } catch (IOException e) {
            e.printStackTrace();
            productos = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Producto> cargarProductos() {
        List<Producto> productos;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            productos = (List<Producto>) ois.readObject();
        } catch (EOFException e) {
            productos = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            productos = new ArrayList<>();
        }
        return productos;
    }

    private void guardarProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void crear(Producto producto) {
        productos.add(producto);
        guardarProductos(productos);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().startsWith(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
        guardarProductos(productos);
    }

    @Override
    public void eliminar(int codigo) {
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarProductos(productos);
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }
}
