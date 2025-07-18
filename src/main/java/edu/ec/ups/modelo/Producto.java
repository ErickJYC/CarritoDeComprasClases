package edu.ec.ups.modelo;

import javax.swing.*;
import java.io.Serializable;

/**
 * Representa un producto disponible para agregar al carrito de compras.
 * Cada producto tiene un código único, un nombre y un precio.
 */
public class Producto implements Serializable {
    /**
     * Código único del producto.
     */
    private int codigo;
    /**
     * Nombre del producto.
     */
    private String nombre;
    /**
     * Precio unitario del producto.
     */
    private double precio;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío requerido por algunos frameworks o procesos de serialización.
     */
    public Producto() {
    }


    /**
     * Constructor que inicializa el producto con sus atributos.
     *
     * @param codigo Código único del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio unitario del producto.
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto validando que sea positivo.
     *
     * @param codigo Código a establecer.
     */
    public void setCodigo(int codigo) {
        try {
            if (codigo <= 0) {
                throw new IllegalArgumentException("El código debe ser un número positivo.");
            }
            this.codigo = codigo;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.codigo = -1; // señal de error
        }
    }
    /**
     * Establece el nombre del producto, validando que no sea nulo ni vacío.
     *
     * @param nombre Nombre del producto.
     */
    public void setNombre(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            this.nombre = nombre;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.nombre = null;
        }
    }
    /**
     * Establece el precio del producto, validando que sea mayor a cero.
     *
     * @param precio Precio del producto.
     */
    public void setPrecio(double precio) {
        try {
            if (precio <= 0) {
                throw new IllegalArgumentException("El precio debe ser mayor que cero.");
            }
            this.precio = precio;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.precio = -1;
        }
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación en texto del producto.
     * Ejemplo: "Laptop - $999.99"
     */
    @Override
    public String toString() {
        return nombre + " - $" + String.format("%.2f", precio);
    }
}
