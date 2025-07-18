package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un conjunto de preguntas de seguridad asociadas a un usuario.
 * Se utiliza principalmente para funciones de recuperación de cuenta o validación de identidad.
 */
public class PreguntaCuestionario implements Serializable {
    /**
     * Username del usuario al que está asociado el cuestionario.
     */
    private String username;
    /**
     * Lista de preguntas del cuestionario.
     */
    private List<Pregunta> preguntas;
    /**
     * Objeto {@link Usuario} al que pertenece este cuestionario (opcional).
     */
    private Usuario usuario;
    /**
     * Manejador de internacionalización para aplicar los textos en diferentes idiomas.
     */
    private MensajeInternacionalizacionHandler mi;
    private static final long serialVersionUID = 1L;
    /**
     * Constructor que inicializa el cuestionario con un nombre de usuario.
     *
     * @param username Nombre de usuario asociado al cuestionario.
     */
    public PreguntaCuestionario(String username) {
        this.username = username;
        this.preguntas = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Agrega una nueva pregunta al cuestionario.
     *
     * @param pregunta Objeto {@link Pregunta} a agregar.
     */
    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }
    /**
     * Elimina una pregunta del cuestionario según su ID.
     *
     * @param idPregunta ID de la pregunta a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminarPreguntaPorId(int idPregunta) {
        return preguntas.removeIf(p -> p.getId() == idPregunta);
    }
    /**
     * Elimina todas las preguntas del cuestionario.
     */
    public void limpiarPreguntas() {
        preguntas.clear();
    }
    /**
     * Busca una pregunta específica por su ID dentro del cuestionario.
     *
     * @param idPregunta ID de la pregunta a buscar.
     * @return Instancia de {@link Pregunta} o null si no se encuentra.
     */
    public Pregunta buscarPreguntaPorId(int idPregunta) {
        for (Pregunta p : preguntas) {
            if (p.getId() == idPregunta) {
                return p;
            }
        }
        return null;
    }
    /**
     * Genera una lista predefinida de preguntas de seguridad con claves de internacionalización.
     *
     * @param mi Manejador de internacionalización.
     * @return Lista de preguntas por defecto.
     */
    public List<Pregunta> preguntasPorDefecto(MensajeInternacionalizacionHandler mi) {
        List<Pregunta> lista = new ArrayList<>();
        lista.add(new Pregunta(1, "pregunta.ciudad_donde_naciste",mi));
        lista.add(new Pregunta(2, "pregunta.nombre_de_tu_mascota_en_la_infancia",mi));
        lista.add(new Pregunta(3, "pregunta.nombre_de_tu_primer_docente",mi));
        lista.add(new Pregunta(4, "pregunta.mejor_amigo_de_la_infancia",mi));
        lista.add(new Pregunta(5, "pregunta.juego_infantil_favorito",mi));
        lista.add(new Pregunta(6, "pregunta.nombre_de_tu_abuelo_materno",mi));
        lista.add(new Pregunta(7, "pregunta.deportista_favorito",mi));
        lista.add(new Pregunta(8, "pregunta.apodo_que_tenias_en_la_escuela",mi));
        lista.add(new Pregunta(9, "pregunta.nombre_de_tu_primer_amor",mi));
        lista.add(new Pregunta(10, "pregunta.lugar_de_vacaciones_mas_memorable",mi));
        return lista;
    }
    /**
     * Aplica el idioma a todas las preguntas del cuestionario usando el manejador dado.
     *
     * @param mi Instancia de {@link MensajeInternacionalizacionHandler}.
     */
    public void aplicarIdioma(MensajeInternacionalizacionHandler mi) {
        for (Pregunta p : this.preguntas) {
            p.setMensajeIdioma(mi);
        }
    }
}
