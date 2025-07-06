package edu.ec.ups.dao;

import edu.ec.ups.modelo.PreguntaCuestionario;

public interface PreguntaDAO {
    // Guarda un cuestionario de preguntas para un usuario
    void guardar(PreguntaCuestionario cuestionario);

    // Busca un cuestionario por el username del usuario
    PreguntaCuestionario buscarPorUsername(String username);
}
