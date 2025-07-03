package edu.ec.ups.dao;

import edu.ec.ups.modelo.Preguntas;

public interface CuestionarioDAO {
    void guardar(Preguntas cuestionario);
    Preguntas buscarPorUsername(String username);
}
