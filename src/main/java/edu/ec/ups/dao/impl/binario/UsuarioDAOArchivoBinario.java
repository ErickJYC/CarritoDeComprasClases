package edu.ec.ups.dao.impl.binario;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDAOArchivoBinario implements UsuarioDAO {
    private String rutaArchivo;

    public UsuarioDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        if (!f.exists()) {
            try {
                f.createNewFile();
                // Inicializar archivo con lista vacía para evitar EOFException
                guardarLista(new ArrayList<>());
            } catch (IOException e) {
                e.printStackTrace();
            }
            crear(new Usuario("0102896362", "Admin@1", Rol.ADMINISTRADOR));
        }
    }

    @SuppressWarnings("unchecked")
    private List<Usuario> leerLista() {
        List<Usuario> lista = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            lista = (List<Usuario>) ois.readObject();
        } catch (EOFException eof) {
            // Archivo vacío, retornamos lista vacía
            lista = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            lista = new ArrayList<>();
        }
        return lista;
    }

    private void guardarLista(List<Usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        List<Usuario> usuarios = listarTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        usuarios.add(usuario);
        guardarLista(usuarios);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios = usuarios.stream()
                .filter(u -> !u.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
        guardarLista(usuarios);
    }

    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        usuarios = usuarios.stream()
                .map(u -> u.getUsername().equalsIgnoreCase(usuario.getUsername()) ? usuario : u)
                .collect(Collectors.toList());
        guardarLista(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        return leerLista();
    }

    @Override
    public List<Usuario> listarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        return usuarios.stream()
                .filter(u -> u.getUsername().toLowerCase().startsWith(username.toLowerCase()))
                .collect(Collectors.toList());
    }

}
