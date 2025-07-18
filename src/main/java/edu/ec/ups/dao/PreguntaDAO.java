package edu.ec.ups.dao;

import edu.ec.ups.modelo.PreguntaCuestionario;
/**
 * Interfaz DAO (Data Access Object) que define las operaciones de persistencia
 * para los cuestionarios de preguntas asociadas a usuarios.
 */
public interface PreguntaDAO {
    /**
     * Guarda un objeto {@link PreguntaCuestionario} en el sistema de almacenamiento.
     * Si ya existe un cuestionario con el mismo username, se actualiza.
     *
     * @param cuestionario El cuestionario que se desea guardar o actualizar
     */
    void guardar(PreguntaCuestionario cuestionario);

    /**
     * Busca y recupera un cuestionario de preguntas basado en el nombre de usuario.
     *
     * @param username Nombre de usuario asociado al cuestionario
     * @return El objeto {@link PreguntaCuestionario} encontrado, o {@code null} si no existe
     */
    PreguntaCuestionario buscarPorUsername(String username);
}
