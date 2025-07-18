package edu.ec.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Clase utilitaria que proporciona métodos estáticos para formatear valores
 * como moneda y fechas, de acuerdo a una configuración regional (Locale).
 */
public class FormateadorUtils {
    /**
     * Formatea una cantidad numérica como moneda, utilizando el formato del país/lenguaje indicado.
     *
     * @param cantidad Valor numérico que se desea formatear.
     * @param locale   Configuración regional (país/idioma) que define el estilo del formato.
     * @return Una cadena de texto que representa la cantidad en formato de moneda según el locale.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }
    /**
     * Formatea una fecha según el estilo y la región indicada.
     *
     * @param fecha   Objeto Date que se desea formatear.
     * @param locale  Configuración regional (país/idioma) que define el estilo de la fecha.
     * @return Una cadena de texto que representa la fecha formateada según el locale.
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }
}
