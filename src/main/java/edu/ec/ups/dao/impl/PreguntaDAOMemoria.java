package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementación en memoria del DAO para gestionar cuestionarios de preguntas.
 * Utiliza una lista para almacenar objetos PreguntaCuestionario durante la ejecución.
 */
public class PreguntaDAOMemoria implements PreguntaDAO {

    /** Lista que almacena los cuestionarios en memoria */
    private List<PreguntaCuestionario> cuestionarios;

    /**
     * Constructor por defecto que inicializa la lista vacía de cuestionarios.
     */
    public PreguntaDAOMemoria() {
        this.cuestionarios = new ArrayList<>();
    }

    /**
     * Guarda un nuevo cuestionario en la lista.
     *
     * @param cuestionario Objeto PreguntaCuestionario que se desea guardar
     */
    @Override
    public void guardar(PreguntaCuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    /**
     * Busca un cuestionario según el nombre de usuario asociado.
     *
     * @param username Nombre de usuario asociado al cuestionario
     * @return El cuestionario correspondiente o null si no se encuentra
     */
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
