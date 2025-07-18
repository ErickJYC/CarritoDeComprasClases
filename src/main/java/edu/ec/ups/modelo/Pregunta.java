package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.io.Serializable;
/**
 * Clase que representa una pregunta de seguridad o autenticación.
 * Incluye un identificador, una clave de internacionalización, una posible respuesta
 * y un manejador para obtener el enunciado traducido.
 */
public class Pregunta implements Serializable {
    /**
     * Identificador único de la pregunta.
     */
    private int id;
    /**
     * Clave que permite obtener el enunciado desde los archivos de internacionalización.
     */
    private String claveInternacionalizacion;
    /**
     * Respuesta proporcionada por el usuario.
     */
    private String respuesta;
    /**
     * Manejador de mensajes para internacionalización (transient porque no se serializa).
     */
    private transient MensajeInternacionalizacionHandler mi;
    private static final long serialVersionUID = 1L;
    /**
     * Constructor para crear una pregunta con su ID, clave de idioma y manejador de mensajes.
     *
     * @param id                      Identificador de la pregunta
     * @param claveInternacionalizacion Clave usada para buscar el texto internacionalizado
     * @param mi                      Instancia de {@link MensajeInternacionalizacionHandler}
     */
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
    /**
     * Devuelve el enunciado de la pregunta en el idioma correspondiente.
     * Si el manejador no está disponible, devuelve la clave directamente.
     *
     * @return Texto del enunciado de la pregunta
     */
    public String getEnunciado() {
        if (mi != null) {
            return mi.get(claveInternacionalizacion);
        } else {
            return claveInternacionalizacion;
        }
    }
}
