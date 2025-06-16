package edu.ec.ups.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBusacarProducto;
    private JDesktopPane jDesktopPane;

    public PrincipalView() {
        jDesktopPane = new JDesktopPane();
        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");
        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemBusacarProducto = new JMenuItem("Buscar Producto");

        menuBar.add(menuProducto);
        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBusacarProducto);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Carrito De Compras En Linea");
        setLocationRelativeTo(null);
        setSize(700,500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        menuItemCrearProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame jInternalFrame = new JInternalFrame("Crear Producto");
                jInternalFrame.setSize(500,500);

                jInternalFrame.setClosable(true);
                jInternalFrame.setMaximizable(true);
                jInternalFrame.setResizable(true);
                jInternalFrame.setIconifiable(true);
                jInternalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);


                jInternalFrame.setVisible(true);
                jDesktopPane.add(jInternalFrame);
            }
        });
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public void setMenuItemActualizarProducto(JMenuItem menuItemActualizarProducto) {
        this.menuItemActualizarProducto = menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBusacarProducto() {
        return menuItemBusacarProducto;
    }

    public void setMenuItemBusacarProducto(JMenuItem menuItemBusacarProducto) {
        this.menuItemBusacarProducto = menuItemBusacarProducto;
    }
}
