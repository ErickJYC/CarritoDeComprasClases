package edu.ec.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Clase encargada de manejar la internacionalización de mensajes en la aplicación.
 * Utiliza archivos ResourceBundle (por ejemplo, mensajes.properties) para obtener los textos
 * traducidos en distintos idiomas y regiones.
 */
public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;
    /**
     * Constructor que inicializa el idioma y país definidos por el usuario.
     *
     * @param lenguaje Código del lenguaje (por ejemplo, "es" para español).
     * @param pais     Código del país (por ejemplo, "EC" para Ecuador).
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }
    /**
     * Obtiene el mensaje traducido correspondiente a la clave especificada.
     *
     * @param key Clave del mensaje que se desea obtener.
     * @return Texto traducido según el idioma configurado.
     */
    public String get(String key) {
        return bundle.getString(key);
    }
    /**
     * Establece un nuevo lenguaje y país para la configuración regional.
     * Actualiza automáticamente el ResourceBundle correspondiente.
     *
     * @param lenguaje Nuevo código de lenguaje (por ejemplo, "en").
     * @param pais     Nuevo código de país (por ejemplo, "US").
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }
    /**
     * Devuelve el Locale actual (idioma y país) usado para la internacionalización.
     *
     * @return Objeto Locale actual.
     */
    public Locale getLocale() {
        return locale;
    }
    /**
     * Cambia el idioma según una cadena corta de idioma.
     * Usa valores fijos para español (es), inglés (en) y francés (fr).
     *
     * @param idioma Código del idioma: "es", "en", o "fr".
     */
    public void cambiarIdioma(String idioma) {
        Locale nueva = switch (idioma) {
            case "en" -> new Locale("en", "US");
            case "fr" -> new Locale("fr", "FR");
            default -> new Locale("es", "EC");
        };

        this.bundle = ResourceBundle.getBundle("messages", nueva);
    }
}
