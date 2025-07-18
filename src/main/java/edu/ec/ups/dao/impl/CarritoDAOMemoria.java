package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de la interfaz CarritoDAO que almacena los carritos en memoria.
 * Esta clase simula una base de datos usando una lista en tiempo de ejecución.
 */
public class CarritoDAOMemoria implements CarritoDAO {

    /** Lista que actúa como base de datos en memoria para almacenar carritos */
    private List<Carrito> carritos;

    /**
     * Constructor que inicializa la lista de carritos vacía.
     */
    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();
    }

    /**
     * Crea un nuevo carrito, le asigna un código incremental y lo almacena en memoria.
     *
     * @param carrito Carrito a ser creado
     */
    @Override
    public void crear(Carrito carrito) {
        carrito.setCodigo(carritos.size() + 1);
        Carrito copia = carrito.copiar();
        carritos.add(copia);
        System.out.println("Carrito creado con código: " + copia.getCodigo());
    }

    /**
     * Busca un carrito por su código.
     *
     * @param codigo Código único del carrito
     * @return Carrito encontrado o null si no existe
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente si el código coincide.
     *
     * @param carrito Carrito con información actualizada
     */
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    /**
     * Elimina un carrito del sistema según su código.
     * Se utiliza un iterator para evitar ConcurrentModificationException.
     *
     * @param codigo Código del carrito a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Carrito> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Carrito carrito = iterator.next();
            if (carrito.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Lista todos los carritos almacenados en memoria.
     *
     * @return Lista de todos los carritos
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    /**
     * Busca todos los carritos asociados a un usuario específico.
     * Se compara por el nombre de usuario.
     *
     * @param usuario Usuario del cual se quieren obtener los carritos
     * @return Lista de carritos pertenecientes al usuario
     */
    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> carritosUsuario = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                carritosUsuario.add(carrito);
            }
        }
        return carritosUsuario;
    }
}