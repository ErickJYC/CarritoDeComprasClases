package edu.ec.ups.modelo;

import java.io.Serializable;

/**
 * Clase que representa un ítem dentro del carrito de compras.
 * Cada ítem contiene un producto y su cantidad correspondiente,
 * y permite calcular su subtotal, IVA y total.
 */
public class ItemCarrito implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Producto asociado a este ítem del carrito.
     */
    private Producto producto;
    /**
     * Cantidad de unidades del producto.
     */
    private int cantidad;
    /**
     * Constructor por defecto.
     * Crea un ítem vacío sin producto ni cantidad definida.
     */
    public ItemCarrito() {
    }
    /**
     * Constructor que recibe el producto y la cantidad.
     *
     * @param producto Producto que se agregará al ítem
     * @param cantidad Cantidad del producto
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Devuelve el producto del ítem.
     *
     * @return Objeto {@link Producto}
     */
    public Producto getProducto() {
        return producto;
    }
    /**
     * Establece el producto del ítem.
     *
     * @param producto Objeto {@link Producto}
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    /**
     * Devuelve la cantidad de productos.
     *
     * @return Número de unidades
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Establece la cantidad de productos.
     *
     * @param cantidad Número de unidades
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * Calcula el subtotal del ítem (precio unitario * cantidad).
     *
     * @return Subtotal en dólares
     */
    // Calcula el subtotal (precio * cantidad)
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
    /**
     * Calcula el valor del IVA (12% del subtotal).
     *
     * @return Valor del IVA
     */
    public double getIVA() {
        return getSubtotal() * 0.12;
    }
    /**
     * Calcula el total (subtotal + IVA).
     *
     * @return Total en dólares
     */
    public double getTotal() {
        return getSubtotal() + getIVA();
    }
    /**
     * Devuelve una representación en cadena del ítem,
     * incluyendo nombre, cantidad y subtotal.
     *
     * @return Cadena de texto descriptiva del ítem
     */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}
