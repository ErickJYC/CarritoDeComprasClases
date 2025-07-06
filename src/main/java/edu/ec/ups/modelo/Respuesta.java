package edu.ec.ups.modelo;

/**
 * Representa una pregunta con su respectiva respuesta dentro del cuestionario de seguridad.
 */
public class Respuesta {

    private int id;              // Identificador único de la pregunta
    private String enunciado;    // Texto de la pregunta (enunciado)
    private String respuesta;    // Respuesta proporcionada por el usuario

    /**
     * Constructor que inicializa una pregunta con su ID y enunciado.
     * La respuesta se inicializa como null.
     *
     * @param id        Identificador de la pregunta
     * @param enunciado Texto de la pregunta
     */
    public Respuesta(int id, String enunciado) {
        this.id = id;
        this.enunciado = enunciado;
        this.respuesta = null;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
