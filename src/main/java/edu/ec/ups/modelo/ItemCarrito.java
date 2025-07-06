package edu.ec.ups.modelo;

/**
 * Clase que representa un ítem dentro del carrito de compras.
 * Cada ítem contiene un producto y su cantidad correspondiente.
 */
public class ItemCarrito {

    private Producto producto;
    private int cantidad;

    // Constructor vacío
    public ItemCarrito() {
    }

    // Constructor con parámetros
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Calcula el subtotal (precio * cantidad)
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    // Calcula el IVA (12% del subtotal)
    public double getIVA() {
        return getSubtotal() * 0.12;
    }

    // Calcula el total (subtotal + IVA)
    public double getTotal() {
        return getSubtotal() + getIVA();
    }

    // Representación en cadena del ítem
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}
