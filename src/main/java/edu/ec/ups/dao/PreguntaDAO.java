package edu.ec.ups.dao;

import edu.ec.ups.modelo.PreguntaCuestionario;

public interface PreguntaDAO {
    void guardar(PreguntaCuestionario cuestionario);
    PreguntaCuestionario buscarPorUsername(String username);
}
