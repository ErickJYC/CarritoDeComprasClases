package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.CuestionarioDAO;
import edu.ec.ups.modelo.Cuestionario;

import java.util.ArrayList;
import java.util.List;

public class CuestionarioDAOMemoria implements CuestionarioDAO {

    private List<Cuestionario> cuestionarios;
    public CuestionarioDAOMemoria() {
        this.cuestionarios = new ArrayList<>();
    }

    @Override
    public void guardar(Cuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    @Override
    public Cuestionario buscarPorUsername(String username) {
        for (Cuestionario cuestionario : cuestionarios) {
            if (cuestionario.getUsername().equalsIgnoreCase(username)) {
                return cuestionario;
            }
        }
        return null;
    }
}
