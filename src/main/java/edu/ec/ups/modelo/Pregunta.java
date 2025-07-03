package edu.ec.ups.modelo;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

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
    LUGAR_VACACIONES_MEMORABLES("pregunta.lugar_de_vacaciones_mas_memorable"),;

    private String enunciado;
    private MensajeInternacionalizacionHandler mi;

    Pregunta(String enunciado) {
        this.enunciado = enunciado;
    }
    Pregunta() {}

    public void setMensajeIdioma(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }

    public String getEnunciado() {
        if (mi != null) {
            return mi.get(enunciado);
        } else {
            return enunciado;
        }
    }
}
