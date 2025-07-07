package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un conjunto de respuestas a preguntas de seguridad asociadas a un usuario.
 * Se utiliza para la recuperación de contraseña y verificación de identidad.
 */
public class PreguntaCuestionario {


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
