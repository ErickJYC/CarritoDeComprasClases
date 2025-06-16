package edu.ec.ups.vista;

import edu.ec.ups.controlador.ProductoController;
import edu.ec.ups.dao.ProductoDAO;
import edu.ec.ups.dao.impl.ProductoDAOMemoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PrincipalView principalView = new PrincipalView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();

                ProductoController productoController = new ProductoController(productoDAO);


                principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                        productoController.setProductoAnadirView(productoAnadirView);

                        principalView.getjDesktopPane().add(productoAnadirView);
                    }
                });
                principalView.getMenuItemBusacarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });


            }
        });
    }
}
