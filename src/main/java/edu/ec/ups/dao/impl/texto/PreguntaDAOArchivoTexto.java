package edu.ec.ups.dao.impl.texto;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.modelo.Pregunta;
import edu.ec.ups.modelo.PreguntaCuestionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación del DAO de preguntas utilizando almacenamiento en archivo de texto.
 * Se guarda la información en formato plano, donde cada cuestionario se identifica
 * por un "USER:" seguido por varias líneas "PREG:" que representan preguntas y respuestas.
 *
 * Este DAO permite guardar y recuperar cuestionarios por nombre de usuario.
 *
 * Formato del archivo:
 * USER:username
 * PREG:id;claveInternacionalizacion;respuesta
 *
 */
public class PreguntaDAOArchivoTexto implements PreguntaDAO {
    /** Ruta del archivo de texto que almacena los cuestionarios */
    private String rutaArchivo;
    /**
     * Constructor que inicializa el DAO con la ruta al archivo de texto.
     * Si el archivo no existe, lo crea automáticamente.
     *
     * @param rutaArchivo Ruta al archivo donde se guardan los cuestionarios
     */
    public PreguntaDAOArchivoTexto(String rutaArchivo) {
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
     * Carga todos los cuestionarios desde el archivo de texto.
     * Cada cuestionario se identifica por un "USER:" seguido de líneas "PREG:".
     *
     * @return Lista de objetos PreguntaCuestionario
     */
    private List<PreguntaCuestionario> cargarCuestionarios() {
        List<PreguntaCuestionario> cuestionarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            PreguntaCuestionario cuestionario = null;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("USER:")) {
                    String username = linea.substring(5);
                    cuestionario = new PreguntaCuestionario(username);
                    cuestionarios.add(cuestionario);
                } else if (linea.startsWith("PREG:") && cuestionario != null) {
                    // Formato: id;clave;respuesta
                    String[] partes = linea.substring(5).split(";", 3);
                    if (partes.length == 3) {
                        try {
                            int id = Integer.parseInt(partes[0]);
                            String clave = partes[1];
                            String respuesta = partes[2];
                            Pregunta p = new Pregunta(id, clave, null);
                            p.setRespuesta(respuesta);
                            cuestionario.agregarPregunta(p);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parseando pregunta: " + linea);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cuestionarios;
    }

    /**
     * Guarda todos los cuestionarios recibidos sobrescribiendo el archivo.
     *
     * @param cuestionarios Lista de cuestionarios a guardar
     */
    private void guardarCuestionarios(List<PreguntaCuestionario> cuestionarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            for (PreguntaCuestionario c : cuestionarios) {
                bw.write("USER:" + c.getUsername());
                bw.newLine();
                for (Pregunta p : c.getPreguntas()) {
                    bw.write("PREG:" + p.getId() + ";" + p.getClaveInternacionalizacion() + ";" + (p.getRespuesta() != null ? p.getRespuesta() : ""));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Guarda un cuestionario. Si ya existe uno con el mismo username, lo reemplaza.
     *
     * @param cuestionario Cuestionario que se va a guardar o actualizar
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
     * Busca y devuelve un cuestionario asociado a un nombre de usuario.
     *
     * @param username Nombre del usuario asociado al cuestionario
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
