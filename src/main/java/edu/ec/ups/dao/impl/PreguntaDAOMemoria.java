package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {

    private List<PreguntaCuestionario> cuestionarios;
    public PreguntaDAOMemoria() {
        this.cuestionarios = new ArrayList<>();
    }

    @Override
    public void guardar(PreguntaCuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

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
