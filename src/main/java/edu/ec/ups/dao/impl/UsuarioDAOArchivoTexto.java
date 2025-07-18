package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDAOArchivoTexto implements UsuarioDAO {

    private String rutaArchivo;

    public UsuarioDAOArchivoTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            crear(new Usuario("0102896362", "Admin@1", Rol.ADMINISTRADOR));
        }
    }

    // Formato en archivo por línea: username,password,rol
    private String usuarioToLinea(Usuario u) {
        return u.getUsername() + "," + u.getContrasenia() + "," + (u.getRol() != null ? u.getRol().name() : "USUARIO");
    }

    private Usuario lineaToUsuario(String linea) {
        String[] parts = linea.split(",");
        if (parts.length >= 3) {
            Usuario u = new Usuario();
            u.setUsername(parts[0]);
            u.setContrasenia(parts[1]);
            try {
                u.setRol(Rol.valueOf(parts[2]));
            } catch (IllegalArgumentException e) {
                u.setRol(Rol.USUARIO);
            }
            return u;
        }
        return null;
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        List<Usuario> usuarios = listarTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) &&
                    u.getContrasenia() != null &&
                    u.getContrasenia().equals(contrasenia)) {
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
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario u = lineaToUsuario(linea);
                if (u != null) usuarios.add(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public List<Usuario> listarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        return usuarios.stream()
                .filter(u -> u.getUsername().toLowerCase().startsWith(username.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void guardarLista(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            for (Usuario u : usuarios) {
                bw.write(usuarioToLinea(u));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
