package edu.ec.ups.modelo;

/**
 * Representa una respuesta a una pregunta de seguridad dentro de un cuestionario.
 * Contiene un identificador único, el enunciado de la pregunta y la respuesta dada.
 */
public class Respuesta {
    /**
     * Identificador único de la pregunta.
     */
    private int id;
    /**
     * Enunciado o texto de la pregunta.
     */
    private String enunciado;
    /**
     * Respuesta proporcionada por el usuario.
     */
    private String respuesta;
    /**
     * Constructor que inicializa el enunciado y el identificador de la pregunta.
     *
     * @param id        Identificador único de la pregunta.
     * @param enunciado Texto o enunciado de la pregunta.
     */
    public Respuesta(int id, String enunciado) {
        this.id = id;
        this.enunciado = enunciado;
        this.respuesta = null;
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
