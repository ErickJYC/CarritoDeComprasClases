package edu.ec.ups;

import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.servicio.CarritoService;
import edu.ec.ups.servicio.CarritoServiceImpl;

public class CarritoTest {
    public static void main(String[] args) {

        // Crear servicio de carrito
        CarritoService carrito = new CarritoServiceImpl();

        // Crear productos
        Producto p1 = new Producto(1, "Mouse", 15.0);
        Producto p2 = new Producto(2, "Teclado", 25.0);

        // Agregar productos al carrito
        carrito.agregarProducto(p1, 2);  // 2 x $15 = $30
        carrito.agregarProducto(p2, 1);  // 1 x $25 = $25

        // Mostrar los ítems
        System.out.println("Contenido del carrito:");
        for (ItemCarrito item : carrito.obtenerItems()) {
            System.out.println("- " + item);
        }

        // Calcular total
        double total = carrito.calcularTotal();
        System.out.println("Total: $" + total);

        // Verificar si está vacío
        System.out.println("¿Carrito vacío? " + carrito.estaVacio());

        // Eliminar producto y vaciar carrito
        carrito.eliminarProducto(1);
        carrito.vaciarCarrito();

        System.out.println("Carrito vaciado. ¿Vacío ahora? " + carrito.estaVacio());
    }
}
