package edu.ec.ups.dao.impl.binario;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de la interfaz CarritoDAO que utiliza archivos binarios
 * para almacenar y gestionar objetos de tipo Carrito.
 * Proporciona métodos para crear, buscar, actualizar, eliminar y listar carritos.
 * Cada carrito se guarda en una lista serializada en un archivo binario.
 *
 * Esta clase también se encarga de asignar automáticamente códigos únicos incrementales.
 *
 */
public class CarritoDAOArchivoBinario implements CarritoDAO {
    /** Ruta del archivo binario donde se almacenan los carritos */
    private String rutaArchivo;
    /** Último código utilizado para asignar al próximo carrito */
    private int ultimoCodigo = 0;
    /**
     * Constructor que inicializa la DAO con la ruta del archivo.
     * Si el archivo no existe, lo crea y guarda una lista vacía.
     *
     * @param rutaArchivo Ruta del archivo binario donde se guardarán los carritos
     */
    public CarritoDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
                guardarCarritos(new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarUltimoCodigo();
    }
    /**
     * Carga todos los carritos desde el archivo binario.
     *
     * @return Lista de carritos almacenados; vacía si no hay datos o hay error
     */
    @SuppressWarnings("unchecked")
    private List<Carrito> cargarCarritos() {
        List<Carrito> carritos;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            carritos = (List<Carrito>) ois.readObject();
        } catch (EOFException e) {
            carritos = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            carritos = new ArrayList<>();
        }
        return carritos;
    }
    /**
     * Guarda la lista de carritos en el archivo binario.
     *
     * @param carritos Lista de carritos a guardar
     */
    private void guardarCarritos(List<Carrito> carritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Carga el último código registrado para mantener una numeración incremental.
     */
    private void cargarUltimoCodigo() {
        List<Carrito> carritos = cargarCarritos();
        for (Carrito c : carritos) {
            if (c.getCodigo() > ultimoCodigo) {
                ultimoCodigo = c.getCodigo();
            }
        }
    }
    /**
     * Crea un nuevo carrito, asignándole un código único e incremental.
     *
     * @param carrito Carrito a crear
     */
    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();

        // Obtener el mayor código actual para asignar uno nuevo único
        ultimoCodigo = carritos.stream()
                .mapToInt(Carrito::getCodigo)
                .max()
                .orElse(0);

        carrito.setCodigo(++ultimoCodigo); // Asignar nuevo código

        carritos.add(carrito);
        guardarCarritos(carritos);
    }
    /**
     * Busca un carrito por su código.
     *
     * @param codigo Código del carrito
     * @return Carrito encontrado o null si no existe
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }
    /**
     * Busca todos los carritos asociados a un usuario.
     *
     * @param usuario Usuario propietario
     * @return Lista de carritos del usuario
     */
    @Override
    public List<Carrito> buscarPorUsuario(edu.ec.ups.modelo.Usuario usuario) {
        List<Carrito> carritos = cargarCarritos();
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito c : carritos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(c);
            }
        }
        return resultado;
    }
    /**
     * Actualiza un carrito existente si su código coincide.
     *
     * @param carrito Carrito con los datos actualizados
     */
    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarCarritos(carritos);
    }
    /**
     * Elimina un carrito por su código.
     *
     * @param codigo Código del carrito a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }
    /**
     * Lista todos los carritos almacenados en el archivo binario.
     *
     * @return Lista de carritos
     */
    @Override
    public List<Carrito> listarTodos() {
        return cargarCarritos();
    }
}