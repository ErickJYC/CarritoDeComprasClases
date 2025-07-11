package edu.ec.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;

    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public String get(String key) {
        return bundle.getString(key);
    }

    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public Locale getLocale() {
        return locale;
    }
    public void cambiarIdioma(String idioma) {
        Locale nueva = switch (idioma) {
            case "en" -> new Locale("en", "US");
            case "fr" -> new Locale("fr", "FR");
            default -> new Locale("es", "EC");
        };

        this.bundle = ResourceBundle.getBundle("messages", nueva);
    }
}
