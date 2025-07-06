package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

public class Pregunta {
    private int id;
    private String claveInternacionalizacion;
    private String respuesta;
    private transient MensajeInternacionalizacionHandler mi;

    public Pregunta(int id, String claveInternacionalizacion, MensajeInternacionalizacionHandler mi) {
        this.id = id;
        this.claveInternacionalizacion = claveInternacionalizacion;
        this.respuesta = null;
        this.mi = mi;
    }

    public int getId() {
        return id;
    }

    public String getClaveInternacionalizacion() {
        return claveInternacionalizacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setMensajeIdioma(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }

    public String getEnunciado() {
        if (mi != null) {
            return mi.get(claveInternacionalizacion);
        } else {
            return claveInternacionalizacion;
        }
    }
}
