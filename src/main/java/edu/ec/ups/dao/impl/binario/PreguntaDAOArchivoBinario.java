package edu.ec.ups.dao.impl.binario;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de la interfaz PreguntaDAO que utiliza archivos binarios
 * para almacenar y recuperar objetos de tipo PreguntaCuestionario.
 *
 * Esta clase permite guardar cuestionarios de seguridad por usuario y
 * buscarlos posteriormente mediante su nombre de usuario.
 *
 * Utiliza serialización binaria con listas de objetos.
 *
 * @author
 */
public class PreguntaDAOArchivoBinario implements PreguntaDAO {
    /** Ruta del archivo binario donde se almacenan los cuestionarios */
    private String rutaArchivo;
    /**
     * Constructor que inicializa la DAO con la ruta del archivo.
     * Si el archivo no existe, lo crea y guarda una lista vacía.
     *
     * @param rutaArchivo Ruta del archivo binario donde se guardarán los cuestionarios
     */
    public PreguntaDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
                guardarCuestionarios(new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Carga todos los cuestionarios desde el archivo binario.
     *
     * @return Lista de cuestionarios cargados; vacía si no hay datos o hay error
     */
    @SuppressWarnings("unchecked")
    private List<PreguntaCuestionario> cargarCuestionarios() {
        List<PreguntaCuestionario> cuestionarios;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            cuestionarios = (List<PreguntaCuestionario>) ois.readObject();
        } catch (EOFException e) {
            cuestionarios = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            cuestionarios = new ArrayList<>();
        }
        return cuestionarios;
    }
    /**
     * Guarda la lista completa de cuestionarios en el archivo binario.
     *
     * @param cuestionarios Lista de cuestionarios a guardar
     */
    private void guardarCuestionarios(List<PreguntaCuestionario> cuestionarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(cuestionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Guarda un cuestionario en la base de datos.
     * Si ya existe uno con el mismo username, lo actualiza.
     * En caso contrario, lo agrega a la lista.
     *
     * @param cuestionario Objeto PreguntaCuestionario a guardar o actualizar
     */
    @Override
    public void guardar(PreguntaCuestionario cuestionario) {
        List<PreguntaCuestionario> cuestionarios = cargarCuestionarios();
        boolean actualizado = false;
        for (int i = 0; i < cuestionarios.size(); i++) {
            if (cuestionarios.get(i).getUsername().equals(cuestionario.getUsername())) {
                cuestionarios.set(i, cuestionario);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {
            cuestionarios.add(cuestionario);
        }
        guardarCuestionarios(cuestionarios);
    }
    /**
     * Busca un cuestionario de seguridad según el nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return Cuestionario encontrado o null si no existe
     */
    @Override
    public PreguntaCuestionario buscarPorUsername(String username) {
        List<PreguntaCuestionario> cuestionarios = cargarCuestionarios();
        for (PreguntaCuestionario c : cuestionarios) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }
}
