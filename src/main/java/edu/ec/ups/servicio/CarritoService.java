package edu.ec.ups.servicio;

import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;

import java.util.List;

public interface CarritoService {
    void agregarProducto(Producto producto, int cantidad);

    void eliminarProducto(int codigoProducto);

    void vaciarCarrito();

    double calcularTotal();

    List<ItemCarrito> obtenerItems();

    boolean estaVacio();
}
