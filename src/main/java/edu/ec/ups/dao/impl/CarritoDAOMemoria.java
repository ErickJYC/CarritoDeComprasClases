package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoDAOMemoria implements CarritoDAO {

    // Lista interna que simula la base de datos en memoria
    private List<Carrito> carritos;

    // Constructor: inicializa la lista vacía
    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();
    }

    // Crear un nuevo carrito: asigna un código y guarda una copia
    @Override
    public void crear(Carrito carrito) {
        carrito.setCodigo(carritos.size() + 1);
        Carrito copia = carrito.copiar();
        carritos.add(copia);
        System.out.println("Carrito creado con código: " + copia.getCodigo());
    }

    // Buscar un carrito por código
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    // Actualizar un carrito existente por código
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    // Eliminar un carrito por código usando iterator para evitar ConcurrentModificationException
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

    // Listar todos los carritos
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    // Buscar carritos asociados a un usuario dado (comparando username)
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