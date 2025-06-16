package edu.ec.ups.vista;

import edu.ec.ups.controlador.ProductoController;
import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.dao.impl.ProductoDAOMemoria;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                ProductoAnadirView productoView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                ProductoModificarView productoModificarView = new ProductoModificarView();

                new ProductoController(productoView,productoListaView,productoDAO,productoModificarView);
            }
        });
    }
}
