package edu.ec.ups.modelo;

/**
 * Representa una pregunta con su respectiva respuesta dentro del cuestionario de seguridad.
 */
public class Respuesta {
    private int id;
    private String enunciado;
    private String respuesta;

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
