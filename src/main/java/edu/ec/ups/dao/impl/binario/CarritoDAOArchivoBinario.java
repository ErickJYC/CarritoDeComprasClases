package edu.ec.ups.dao.impl.binario;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOArchivoBinario implements CarritoDAO {

    private String rutaArchivo;
    private int ultimoCodigo = 0;

    public CarritoDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
                guardarCarritos(new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarUltimoCodigo();
    }

    @SuppressWarnings("unchecked")
    private List<Carrito> cargarCarritos() {
        List<Carrito> carritos;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            carritos = (List<Carrito>) ois.readObject();
        } catch (EOFException e) {
            carritos = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            carritos = new ArrayList<>();
        }
        return carritos;
    }

    private void guardarCarritos(List<Carrito> carritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarUltimoCodigo() {
        List<Carrito> carritos = cargarCarritos();
        for (Carrito c : carritos) {
            if (c.getCodigo() > ultimoCodigo) {
                ultimoCodigo = c.getCodigo();
            }
        }
    }

    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();

        // Obtener el mayor código actual para asignar uno nuevo único
        ultimoCodigo = carritos.stream()
                .mapToInt(Carrito::getCodigo)
                .max()
                .orElse(0);

        carrito.setCodigo(++ultimoCodigo); // Asignar nuevo código

        carritos.add(carrito);
        guardarCarritos(carritos);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Carrito> buscarPorUsuario(edu.ec.ups.modelo.Usuario usuario) {
        List<Carrito> carritos = cargarCarritos();
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito c : carritos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarCarritos(carritos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }

    @Override
    public List<Carrito> listarTodos() {
        return cargarCarritos();
    }
}