package edu.ec.ups.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Carrito implements Serializable {

    private int codigo;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;
    private Usuario usuario;
    private static final long serialVersionUID = 1L;

    // Constructor por defecto
    public Carrito() {
        this.items = new ArrayList<>();
        this.fechaCreacion = new GregorianCalendar();
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Agrega un producto al carrito (acumula cantidad si ya existe)
    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigo() == producto.getCodigo()) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    // Elimina un producto del carrito por su código
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    // Vacía el carrito completamente
    public void vaciarCarrito() {
        items.clear();
    }

    // Calcula el total sin impuestos
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

    // Devuelve la lista de items
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    // Verifica si el carrito está vacío
    public boolean estaVacio() {
        return items.isEmpty();
    }

    // Calcula el IVA (12%)
    public double calcularIVA() {
        return calcularTotal() * 0.12;
    }

    // Calcula el total incluyendo el IVA
    public double calcularTotalConIVA() {
        return calcularTotal() + calcularIVA();
    }

    // Devuelve la fecha formateada en "dd/MM/yyyy"
    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaCreacion.getTime());
    }

    // Devuelve una copia del carrito con sus productos y cantidades
    public Carrito copiar() {
        Carrito copia = new Carrito();
        copia.setFechaCreacion(this.fechaCreacion);
        copia.setCodigo(this.codigo);
        copia.setUsuario(this.usuario);
        for (ItemCarrito item : this.items) {
            copia.agregarProducto(item.getProducto(), item.getCantidad());
        }
        return copia;
    }
}