package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un conjunto de respuestas a preguntas de seguridad asociadas a un usuario.
 * Se utiliza para la recuperación de contraseña y verificación de identidad.
 */
public class PreguntaCuestionario {

//    private String username; // Nombre de usuario asociado
//    private List<Respuesta> respuestas; // Lista de respuestas dadas por el usuario
//    private Usuario usuario; // Referencia al objeto Usuario (opcional)
//
//    // Constructor
//    public PreguntaCuestionario(String username) {
//        this.username = username;
//        this.respuestas = new ArrayList<>();
//    }
//
//    // Getters y setters
//    public String getUsername() {
//        return username;
//    }
//
//    public List<Respuesta> getRespuestas() {
//        return respuestas;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    // Agrega una respuesta al cuestionario
//    public void agregarRespuesta(Respuesta respuesta) {
//        respuestas.add(respuesta);
//    }
//
//    // Elimina una respuesta por el ID de la pregunta asociada
//    public boolean eliminarRespuestaPorId(int idPregunta) {
//        return respuestas.removeIf(r -> r.getId() == idPregunta);
//    }
//
//    // Limpia todas las respuestas del cuestionario
//    public void limpiarRespuestas() {
//        respuestas.clear();
//    }
//
//    // Busca una respuesta por ID de la pregunta
//    public Respuesta buscarRespuestaPorId(int idPregunta) {
//        for (Respuesta r : respuestas) {
//            if (r.getId() == idPregunta) {
//                return r;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Genera una lista con las preguntas por defecto sin respuestas definidas.
//     * Esto se utiliza normalmente cuando un usuario aún no ha completado su cuestionario.
//     */
//    public List<Respuesta> preguntasPorDefecto() {
//        List<Respuesta> lista = new ArrayList<>();
//        PreguntaEnum[] preguntas = PreguntaEnum.values();
//        for (int i = 0; i < preguntas.length; i++) {
//            lista.add(new Respuesta(i + 1, preguntas[i].getEnunciado()));
//        }
//        return lista;
//    }
//
//    /**
//     * Aplica la internacionalización a todas las preguntas del sistema,
//     * actualizando su enunciado con el idioma seleccionado.
//     */
//    public void aplicarIdioma(MensajeInternacionalizacionHandler mi) {
//        for (PreguntaEnum p : PreguntaEnum.values()) {
//            p.setMensajeIdioma(mi);
//        }
//    }
private String username;
    private List<Pregunta> preguntas;
    private Usuario usuario;
    private MensajeInternacionalizacionHandler mi;

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

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    public boolean eliminarPreguntaPorId(int idPregunta) {
        return preguntas.removeIf(p -> p.getId() == idPregunta);
    }

    public void limpiarPreguntas() {
        preguntas.clear();
    }

    public Pregunta buscarPreguntaPorId(int idPregunta) {
        for (Pregunta p : preguntas) {
            if (p.getId() == idPregunta) {
                return p;
            }
        }
        return null;
    }

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

    public void aplicarIdioma(MensajeInternacionalizacionHandler mi) {
        for (Pregunta p : this.preguntas) {
            p.setMensajeIdioma(mi);
        }
    }
}
