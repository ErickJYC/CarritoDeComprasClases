package edu.ec.ups.modelo;

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
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
