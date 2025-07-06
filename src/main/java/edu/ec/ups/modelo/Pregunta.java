package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

/**
 * Enum que representa un conjunto de preguntas de seguridad
 * para recuperación de contraseña u otros propósitos.
 * Cada pregunta tiene una clave de internacionalización asociada.
 */
public enum Pregunta {

    CIUDAD_NACIMIENTO("pregunta.ciudad_donde_naciste"),
    NOMBRE_MASCOTA_INFANCIA("pregunta.nombre_de_tu_mascota_en_la_infancia"),
    PRIMER_DOCENTE("pregunta.nombre_de_tu_primer_docente"),
    NOMBRE_MEJOR_AMIGO_INFANCIA("pregunta.mejor_amigo_de_la_infancia"),
    JUEGO_INFANTIL_FAVORITO("pregunta.juego_infantil_favorito"),
    NOMBRE_ABUELO_MATERNO("pregunta.nombre_de_tu_abuelo_materno"),
    DEPORTISTA_FAVORITO("pregunta.deportista_favorito"),
    APODO_EN_LA_ESCUELA("pregunta.apodo_que_tenias_en_la_escuela"),
    NOMBRE_PRIMER_AMOR("pregunta.nombre_de_tu_primer_amor"),
    LUGAR_VACACIONES_MEMORABLES("pregunta.lugar_de_vacaciones_mas_memorable");

    // Clave de internacionalización
    private final String enunciado;

    // Manejador de mensajes internacionalizados
    private MensajeInternacionalizacionHandler mi;

    // Constructor con clave
    Pregunta(String enunciado) {
        this.enunciado = enunciado;
    }

    // Establece el manejador para obtener textos traducidos
    public void setMensajeIdioma(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }

    /**
     * Retorna el enunciado de la pregunta según el idioma.
     * Si el manejador no está definido, retorna la clave literal.
     */
    public String getEnunciado() {
        if (mi != null) {
            return mi.get(enunciado);
        } else {
            return enunciado;
        }
    }
}
