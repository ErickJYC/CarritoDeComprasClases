package edu.ec.ups.dao.impl.binario;

import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de la interfaz ProductoDAO que utiliza archivos binarios
 * para almacenar y gestionar productos de manera persistente.
 *
 * Esta clase permite realizar operaciones CRUD sobre productos y guarda
 * automáticamente los datos en el archivo especificado mediante serialización.
 *
 * Al inicializarse, intenta cargar los productos existentes desde el archivo.
 * Si no existe el archivo, lo crea vacío.
 *
 */
public class ProductoDAOArchivoBinario implements ProductoDAO {
    /** Ruta del archivo binario donde se almacenan los productos */
    private String rutaArchivo;
    /** Lista de productos cargados desde el archivo */
    private List<Producto> productos;
    /**
     * Constructor que inicializa el DAO con la ruta del archivo.
     * Si el archivo no existe, lo crea y guarda una lista vacía.
     *
     * @param rutaArchivo Ruta del archivo binario donde se almacenarán los productos
     */
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
        System.out.println("Productos cargados desde archivo: " + productos.size());
    }
    /**
     * Carga la lista de productos desde el archivo binario.
     *
     * @return Lista de productos almacenados; vacía si no hay datos o hay error
     */
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
    /**
     * Guarda la lista completa de productos en el archivo binario.
     *
     * @param productos Lista de productos a guardar
     */
    private void guardarProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Crea un nuevo producto y lo guarda en el archivo.
     *
     * @param producto Producto a crear
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
        guardarProductos(productos);
    }
    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto
     * @return Producto encontrado o null si no existe
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    /**
     * Busca productos que empiecen con el nombre proporcionado (ignorando mayúsculas).
     *
     * @param nombre Nombre parcial del producto
     * @return Lista de productos que coinciden
     */
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
    /**
     * Actualiza un producto existente si coincide su código.
     *
     * @param producto Producto con la información actualizada
     */
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
    /**
     * Elimina un producto por su código.
     *
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarProductos(productos);
    }
    /**
     * Devuelve la lista completa de productos cargados desde el archivo.
     *
     * @return Lista de productos
     */
    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }
}
