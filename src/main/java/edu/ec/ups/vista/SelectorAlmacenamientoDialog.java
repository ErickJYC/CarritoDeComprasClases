package edu.ec.ups.vista;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SelectorAlmacenamientoDialog extends JDialog {

    private int opcionSeleccionada = 1; // 1=Memoria, 2=Texto, 3=Binario
    private String carpetaSeleccionada = "";

    private JComboBox<String> cbxIdiomas;
    private JComboBox<String> cbxAlmacenamiento;
    private JButton btnSeleccionarRuta;
    private JButton btnAceptar;
    private JButton btnCancelar;

    private JLabel lblRuta;
    private MensajeInternacionalizacionHandler mi;

    public SelectorAlmacenamientoDialog(Frame parent, MensajeInternacionalizacionHandler mi) {
        super(parent, true);
        this.mi = mi;

        setTitle(mi.get("storage.titulo"));

        cbxIdiomas = new JComboBox<>(new String[]{"Español", "English", "Français"});
        cbxIdiomas.setSelectedIndex(idiomaIndex(mi));
        cbxIdiomas.addActionListener(e -> cambiarIdioma());

        cbxAlmacenamiento = new JComboBox<>(new String[]{
                mi.get("storage.memoria"),
                mi.get("storage.archivoTexto"),
                mi.get("storage.archivoBinario")
        });

        btnSeleccionarRuta = new JButton(mi.get("storage.seleccionarRuta"));
        btnSeleccionarRuta.addActionListener(e -> abrirSelectorCarpeta());

        lblRuta = new JLabel("...");

        btnAceptar = new JButton(mi.get("storage.aceptar"));
        btnAceptar.addActionListener(e -> onAceptar());

        btnCancelar = new JButton(mi.get("storage.cancelar"));
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior.add(new JLabel(mi.get("storage.idioma")));
        panelSuperior.add(cbxIdiomas);

        JPanel panelCentro = new JPanel(new GridLayout(3, 1, 5, 5));
        panelCentro.add(new JLabel(mi.get("storage.seleccione")));
        panelCentro.add(cbxAlmacenamiento);
        panelCentro.add(btnSeleccionarRuta);

        JPanel panelRuta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRuta.add(new JLabel(mi.get("storage.ruta")));
        panelRuta.add(lblRuta);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        setLayout(new BorderLayout(10, 10));
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelRuta, BorderLayout.WEST);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(500, 220);
        setLocationRelativeTo(parent);
    }

    private void cambiarIdioma() {
        switch (cbxIdiomas.getSelectedIndex()) {
            case 0 -> mi.setLenguaje("es", "EC");
            case 1 -> mi.setLenguaje("en", "US");
            case 2 -> mi.setLenguaje("fr", "FR");
        }
        actualizarTextos();
    }

    private void actualizarTextos() {
        setTitle(mi.get("storage.titulo"));
        cbxAlmacenamiento.removeAllItems();
        cbxAlmacenamiento.addItem(mi.get("storage.memoria"));
        cbxAlmacenamiento.addItem(mi.get("storage.archivoTexto"));
        cbxAlmacenamiento.addItem(mi.get("storage.archivoBinario"));
        btnSeleccionarRuta.setText(mi.get("storage.seleccionarRuta"));
        btnAceptar.setText(mi.get("storage.aceptar"));
        btnCancelar.setText(mi.get("storage.cancelar"));
    }

    private void abrirSelectorCarpeta() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            carpetaSeleccionada = fileChooser.getSelectedFile().getAbsolutePath();
            lblRuta.setText(carpetaSeleccionada);
        }
    }

    private void onAceptar() {
        opcionSeleccionada = cbxAlmacenamiento.getSelectedIndex() + 1;
        if (opcionSeleccionada != 1 && (carpetaSeleccionada == null || carpetaSeleccionada.isBlank())) {
            JOptionPane.showMessageDialog(this, mi.get("storage.errorSeleccionRuta"), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (opcionSeleccionada == 2) {
            crearArchivoPorDefecto("usuarios.txt");
        } else if (opcionSeleccionada == 3) {
            crearArchivoPorDefecto("usuarios.dat");
        }
        dispose();
    }

    private void crearArchivoPorDefecto(String nombreArchivo) {
        File archivo = new File(carpetaSeleccionada, nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al crear archivo: " + nombreArchivo, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int idiomaIndex(MensajeInternacionalizacionHandler mi) {
        return switch (mi.getLocale().getLanguage()) {
            case "es" -> 0;
            case "en" -> 1;
            case "fr" -> 2;
            default -> 0;
        };
    }

    public int getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public String getCarpetaSeleccionada() {
        return carpetaSeleccionada;
    }
}