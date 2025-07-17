package edu.ec.ups.vista;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;

public class StorageSelectorDialog extends JDialog {

    private int opcionSeleccionada = 1; // 1=Memoria, 2=Texto, 3=Binario
    private String rutaArchivo = "";

    private JComboBox<String> cbxIdiomas;
    private JComboBox<String> cbxAlmacenamiento;
    private JButton btnAceptar;
    private JButton btnCancelar;

    private MensajeInternacionalizacionHandler mi;

    public StorageSelectorDialog(Frame parent, MensajeInternacionalizacionHandler mi) {
        super(parent, true);
        this.mi = mi;

        setTitle(mi.get("storage.titulo"));

        // Combo de idiomas
        cbxIdiomas = new JComboBox<>();
        cbxIdiomas.addItem("Español");
        cbxIdiomas.addItem("English");
        cbxIdiomas.addItem("Français");
        cbxIdiomas.setSelectedIndex(idiomaIndex(mi)); // Para seleccionar según idioma actual

        cbxIdiomas.addActionListener(e -> {
            int index = cbxIdiomas.getSelectedIndex();
            switch (index) {
                case 0 -> mi.setLenguaje("es", "EC");
                case 1 -> mi.setLenguaje("en", "US");
                case 2 -> mi.setLenguaje("fr", "FR");
            }
            actualizarTextos();
        });

        // Combo almacenamiento
        cbxAlmacenamiento = new JComboBox<>();
        cbxAlmacenamiento.addItem(mi.get("storage.memoria"));
        cbxAlmacenamiento.addItem(mi.get("storage.archivoTexto"));
        cbxAlmacenamiento.addItem(mi.get("storage.archivoBinario"));

        btnAceptar = new JButton(mi.get("storage.aceptar"));
        btnCancelar = new JButton(mi.get("storage.cancelar"));

        btnAceptar.addActionListener(e -> {
            opcionSeleccionada = cbxAlmacenamiento.getSelectedIndex() + 1; // 1=Memoria, 2=Texto, 3=Binario

            if (opcionSeleccionada == 2) { // Archivo texto (.txt)
                rutaArchivo = JOptionPane.showInputDialog(this, mi.get("storage.ingreseRutaTexto"));
                if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
                    opcionSeleccionada = 1; // vuelve a memoria si no ingresa ruta
                    rutaArchivo = "";
                } else if (!rutaArchivo.toLowerCase().endsWith(".txt")) {
                    JOptionPane.showMessageDialog(this,
                            mi.get("storage.errorRutaTxt"),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    rutaArchivo = "";
                    opcionSeleccionada = 1;
                } else {
                    dispose();
                }
            } else if (opcionSeleccionada == 3) { // Archivo binario (.dat)
                boolean valido = false;
                while (!valido) {
                    rutaArchivo = JOptionPane.showInputDialog(this, mi.get("storage.ingreseRutaBinario"));
                    if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
                        opcionSeleccionada = 1;
                        rutaArchivo = "";
                        valido = true;
                    } else if (!rutaArchivo.toLowerCase().endsWith(".dat")) {
                        JOptionPane.showMessageDialog(this,
                                mi.get("storage.errorRutaDat"),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        valido = true;
                    }
                }
                if (opcionSeleccionada != 1) {
                    dispose();
                }
            } else { // Memoria
                rutaArchivo = "";
                dispose();
            }
        });

        btnCancelar.addActionListener(e -> {
            opcionSeleccionada = 1;
            rutaArchivo = "";
            dispose();
        });

        // Panel para idioma arriba a la derecha
        JPanel panelIdioma = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelIdioma.add(new JLabel(mi.get("storage.idioma")));
        panelIdioma.add(cbxIdiomas);

        // Panel central para combo almacenamiento
        JPanel panelCentro = new JPanel();
        panelCentro.add(new JLabel(mi.get("storage.seleccione")));
        panelCentro.add(cbxAlmacenamiento);

        // Panel botones abajo
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        setLayout(new BorderLayout(10, 10));
        add(panelIdioma, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(450, 180);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private int idiomaIndex(MensajeInternacionalizacionHandler mi) {
        String lang = mi.getLocale().getLanguage();
        return switch (lang) {
            case "es" -> 0;
            case "en" -> 1;
            case "fr" -> 2;
            default -> 0;
        };
    }

    private void actualizarTextos() {
        setTitle(mi.get("storage.titulo"));
        ((JLabel)((JPanel)getContentPane().getComponent(0)).getComponent(0)).setText(mi.get("storage.idioma"));
        ((JLabel)((JPanel)getContentPane().getComponent(1)).getComponent(0)).setText(mi.get("storage.seleccione"));

        cbxAlmacenamiento.removeAllItems();
        cbxAlmacenamiento.addItem(mi.get("storage.memoria"));
        cbxAlmacenamiento.addItem(mi.get("storage.archivoTexto"));
        cbxAlmacenamiento.addItem(mi.get("storage.archivoBinario"));

        btnAceptar.setText(mi.get("storage.aceptar"));
        btnCancelar.setText(mi.get("storage.cancelar"));
    }

    public int getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }
}
