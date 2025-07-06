package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un conjunto de respuestas a preguntas de seguridad asociadas a un usuario.
 * Se utiliza para la recuperación de contraseña y verificación de identidad.
 */
public class PreguntaCuestionario {

    private String username; // Nombre de usuario asociado
    private List<Respuesta> respuestas; // Lista de respuestas dadas por el usuario
    private Usuario usuario; // Referencia al objeto Usuario (opcional)

    // Constructor
    public PreguntaCuestionario(String username) {
        this.username = username;
        this.respuestas = new ArrayList<>();
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Agrega una respuesta al cuestionario
    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    // Elimina una respuesta por el ID de la pregunta asociada
    public boolean eliminarRespuestaPorId(int idPregunta) {
        return respuestas.removeIf(r -> r.getId() == idPregunta);
    }

    // Limpia todas las respuestas del cuestionario
    public void limpiarRespuestas() {
        respuestas.clear();
    }

    // Busca una respuesta por ID de la pregunta
    public Respuesta buscarRespuestaPorId(int idPregunta) {
        for (Respuesta r : respuestas) {
            if (r.getId() == idPregunta) {
                return r;
            }
        }
        return null;
    }

    /**
     * Genera una lista con las preguntas por defecto sin respuestas definidas.
     * Esto se utiliza normalmente cuando un usuario aún no ha completado su cuestionario.
     */
    public List<Respuesta> preguntasPorDefecto() {
        List<Respuesta> lista = new ArrayList<>();
        Pregunta[] preguntas = Pregunta.values();
        for (int i = 0; i < preguntas.length; i++) {
            lista.add(new Respuesta(i + 1, preguntas[i].getEnunciado()));
        }
        return lista;
    }

    /**
     * Aplica la internacionalización a todas las preguntas del sistema,
     * actualizando su enunciado con el idioma seleccionado.
     */
    public void aplicarIdioma(MensajeInternacionalizacionHandler mi) {
        for (Pregunta p : Pregunta.values()) {
            p.setMensajeIdioma(mi);
        }
    }
}
