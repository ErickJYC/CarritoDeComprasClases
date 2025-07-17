package edu.ec.ups.modelo;

import javax.swing.*;

/**
 * Representa un producto disponible para agregar al carrito de compras.
 */
public class Producto {

    private int codigo;       // Código único del producto
    private String nombre;    // Nombre del producto
    private double precio;    // Precio unitario del producto

    // Constructor vacío (requerido para frameworks o uso genérico)
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Setters
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
