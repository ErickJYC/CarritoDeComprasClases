package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.CuestionarioDAO;
import edu.ec.ups.modelo.Preguntas;

import java.util.ArrayList;
import java.util.List;

public class CuestionarioDAOMemoria implements CuestionarioDAO {

    private List<Preguntas> cuestionarios;
    public CuestionarioDAOMemoria() {
        this.cuestionarios = new ArrayList<>();
    }

    @Override
    public void guardar(Preguntas cuestionario) {
        cuestionarios.add(cuestionario);
    }

    @Override
    public Preguntas buscarPorUsername(String username) {
        for (Preguntas cuestionario : cuestionarios) {
            if (cuestionario.getUsername().equalsIgnoreCase(username)) {
                return cuestionario;
            }
        }
        return null;
    }
}
