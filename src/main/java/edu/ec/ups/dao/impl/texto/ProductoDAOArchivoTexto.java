package edu.ec.ups.dao.impl.texto;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de la interfaz ProductoDAO que utiliza un archivo de texto
 * como medio de almacenamiento persistente para los productos.
 *
 * Cada producto se guarda como una línea de texto en el archivo con el siguiente formato:
 * codigo;nombre;precio
 *
 * Esta clase permite crear, buscar, actualizar, eliminar y listar productos desde archivo.
 */
public class ProductoDAOArchivoTexto implements ProductoDAO {
    /** Ruta del archivo donde se almacenan los productos */
    private String rutaArchivo;
    /**
     * Constructor que recibe la ruta del archivo donde se almacenarán los productos.
     * Si el archivo no existe, lo crea automáticamente.
     *
     * @param rutaArchivo Ruta del archivo de almacenamiento
     */
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

    /**
     * Carga todos los productos almacenados en el archivo de texto.
     * Cada línea representa un producto con formato: codigo;nombre;precio
     *
     * @return Lista de productos leída desde el archivo
     */
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

    /**
     * Guarda todos los productos en el archivo de texto.
     * El archivo se sobrescribe completamente con la nueva lista.
     *
     * @param productos Lista de productos a guardar
     */
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
    /**
     * Agrega un nuevo producto al archivo.
     *
     * @param producto Producto a guardar
     */
    @Override
    public void crear(Producto producto) {
        List<Producto> productos = cargarProductos();
        productos.add(producto);
        guardarProductos(productos);
    }
    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto a buscar
     * @return Producto encontrado o null si no existe
     */
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
    /**
     * Busca productos cuyo nombre comience con el texto indicado.
     *
     * @param nombre Nombre (o parte inicial) del producto
     * @return Lista de productos que coinciden con el criterio
     */
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
    /**
     * Actualiza un producto existente, reemplazándolo si se encuentra por código.
     *
     * @param producto Producto actualizado
     */
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
    /**
     * Elimina un producto del archivo según su código.
     *
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> productos = cargarProductos();
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarProductos(productos);
    }
    /**
     * Lista todos los productos disponibles en el archivo.
     *
     * @return Lista completa de productos
     */
    @Override
    public List<Producto> listarTodos() {
        return cargarProductos();
    }
}
