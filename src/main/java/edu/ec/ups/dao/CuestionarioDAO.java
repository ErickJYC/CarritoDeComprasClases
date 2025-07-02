package edu.ec.ups.dao;

import edu.ec.ups.modelo.Cuestionario;

public interface CuestionarioDAO {
    void guardar(Cuestionario cuestionario);
    Cuestionario buscarPorUsername(String username);
}
