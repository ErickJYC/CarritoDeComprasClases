package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.List;

public class PreguntaCuestionario {
    private String username;
    private List<Respuesta> respuestas;
    private Usuario usuario;

    public PreguntaCuestionario(String username) {
        this.username = username;
        this.respuestas = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    public boolean eliminarRespuestaPorId(int idPregunta) {
        return respuestas.removeIf(r -> r.getId() == idPregunta);
    }

    public void limpiarRespuestas() {
        respuestas.clear();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Respuesta buscarRespuestaPorId(int idPregunta) {
        for (Respuesta r : respuestas) {
            if (r.getId() == idPregunta) {
                return r;
            }
        }
        return null;
    }

    public List<Respuesta> preguntasPorDefecto() {
        List<Respuesta> lista = new ArrayList<>();
        Pregunta[] preguntas = Pregunta.values();
        for (int i = 0; i < preguntas.length; i++) {
            lista.add(new Respuesta(i + 1, preguntas[i].getEnunciado()));
        }
        return lista;
    }

    public void aplicarIdioma(MensajeInternacionalizacionHandler mi) {
        for (Pregunta p : Pregunta.values()) {
            p.setMensajeIdioma(mi);
        }
    }
}
