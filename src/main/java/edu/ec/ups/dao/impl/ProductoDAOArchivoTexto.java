package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOArchivoTexto implements ProductoDAO {

    private String rutaArchivo;

    public ProductoDAOArchivoTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lee todos los productos del archivo texto
    private List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Cada línea: codigo;nombre;precio
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    try {
                        int codigo = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        double precio = Double.parseDouble(partes[2]);
                        Producto p = new Producto(codigo, nombre, precio);
                        productos.add(p);
                    } catch (NumberFormatException e) {
                        // línea mal formada, ignora
                        System.err.println("Error al parsear línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Guarda toda la lista de productos en el archivo (sobrescribe)
    private void guardarProductos(List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            for (Producto p : productos) {
                bw.write(p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void crear(Producto producto) {
        List<Producto> productos = cargarProductos();
        productos.add(producto);
        guardarProductos(productos);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        List<Producto> productos = cargarProductos();
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productos = cargarProductos();
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
        List<Producto> productos = cargarProductos();
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
        List<Producto> productos = cargarProductos();
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarProductos(productos);
    }

    @Override
    public List<Producto> listarTodos() {
        return cargarProductos();
    }
}
