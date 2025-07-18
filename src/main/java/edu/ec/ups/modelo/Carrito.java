package edu.ec.ups.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un carrito de compras.
 * Contiene productos agregados por un usuario, con fecha de creación y cálculo de totales.
 */
public class Carrito implements Serializable {

    private int codigo;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;
    private Usuario usuario;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto que inicializa el carrito con una lista vacía de ítems
     * y asigna la fecha de creación al momento actual.
     */
    public Carrito() {
        this.items = new ArrayList<>();
        this.fechaCreacion = new GregorianCalendar();
    }

    /**
     * Obtiene el código único del carrito.
     * @return Código del carrito
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * Establece el código del carrito.
     * @param codigo Código único a asignar
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    /**
     * Retorna la fecha de creación del carrito.
     * @return Fecha como objeto {@link GregorianCalendar}
     */
    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }
    /**
     * Retorna la fecha de creación del carrito.
     * @return Fecha como objeto {@link GregorianCalendar}
     */
    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * Retorna el usuario asociado a este carrito.
     * @return Objeto {@link Usuario}
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Establece el usuario asociado al carrito.
     * @param usuario Objeto {@link Usuario}
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Agrega un producto al carrito. Si ya existe, incrementa su cantidad.
     * @param producto Producto a agregar
     * @param cantidad Cantidad del producto
     */
    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigo() == producto.getCodigo()) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito según su código.
     * @param codigoProducto Código del producto a eliminar
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Elimina todos los productos del carrito.
     */
    public void vaciarCarrito() {
        items.clear();
    }

    /**
     * Calcula el total de la compra sin incluir IVA.
     * @return Total sin IVA
     */
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

    /**
     * Devuelve la lista de ítems del carrito.
     * @return Lista de {@link ItemCarrito}
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Verifica si el carrito está vacío.
     * @return {@code true} si no tiene productos, {@code false} en caso contrario
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Calcula el valor del IVA sobre el total (12%).
     * @return Valor del IVA
     */
    public double calcularIVA() {
        return calcularTotal() * 0.12;
    }

    /**
     * Calcula el total de la compra incluyendo el IVA.
     * @return Total con IVA
     */
    public double calcularTotalConIVA() {
        return calcularTotal() + calcularIVA();
    }

    /**
     * Devuelve la fecha de creación formateada en formato "dd/MM/yyyy".
     * @return Fecha como cadena formateada
     */
    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaCreacion.getTime());
    }

    /**
     * Crea una copia del carrito actual, incluyendo los productos y cantidades.
     * @return Objeto {@link Carrito} duplicado
     */
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