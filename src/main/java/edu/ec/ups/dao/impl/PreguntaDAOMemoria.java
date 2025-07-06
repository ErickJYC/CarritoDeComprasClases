package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {

    // Lista que almacena los cuestionarios en memoria
    private List<PreguntaCuestionario> cuestionarios;

    // Constructor: inicializa la lista vacía
    public PreguntaDAOMemoria() {
        this.cuestionarios = new ArrayList<>();
    }

    // Guarda un cuestionario en la lista
    @Override
    public void guardar(PreguntaCuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    // Busca un cuestionario por username ignorando mayúsculas/minúsculas
    @Override
    public PreguntaCuestionario buscarPorUsername(String username) {
        for (PreguntaCuestionario cuestionario : cuestionarios) {
            if (cuestionario.getUsername().equalsIgnoreCase(username)) {
                return cuestionario;
            }
        }
        return null;
    }
}
