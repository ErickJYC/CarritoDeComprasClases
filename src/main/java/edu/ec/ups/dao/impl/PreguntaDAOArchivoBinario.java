package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOArchivoBinario implements PreguntaDAO {

    private String rutaArchivo;

    public PreguntaDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
                guardarCuestionarios(new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<PreguntaCuestionario> cargarCuestionarios() {
        List<PreguntaCuestionario> cuestionarios;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            cuestionarios = (List<PreguntaCuestionario>) ois.readObject();
        } catch (EOFException e) {
            cuestionarios = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            cuestionarios = new ArrayList<>();
        }
        return cuestionarios;
    }

    private void guardarCuestionarios(List<PreguntaCuestionario> cuestionarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(cuestionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardar(PreguntaCuestionario cuestionario) {
        List<PreguntaCuestionario> cuestionarios = cargarCuestionarios();
        boolean actualizado = false;
        for (int i = 0; i < cuestionarios.size(); i++) {
            if (cuestionarios.get(i).getUsername().equals(cuestionario.getUsername())) {
                cuestionarios.set(i, cuestionario);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {
            cuestionarios.add(cuestionario);
        }
        guardarCuestionarios(cuestionarios);
    }

    @Override
    public PreguntaCuestionario buscarPorUsername(String username) {
        List<PreguntaCuestionario> cuestionarios = cargarCuestionarios();
        for (PreguntaCuestionario c : cuestionarios) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }
}
