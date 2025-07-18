package edu.ec.ups.dao.impl.texto;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementación de la interfaz UsuarioDAO que gestiona usuarios a través de un archivo de texto.
 *
 * Cada usuario se almacena en una línea con el formato:
 * username,password,rol
 */
public class UsuarioDAOArchivoTexto implements UsuarioDAO {
    /** Ruta del archivo donde se almacenan los usuarios */
    private String rutaArchivo;
    /**
     * Constructor que inicializa el archivo de usuarios.
     * Si el archivo no existe, lo crea e inserta un usuario administrador por defecto.
     *
     * @param rutaArchivo Ruta del archivo donde se guardarán los datos de usuario.
     */
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

    /**
     * Convierte un objeto Usuario a una línea de texto para guardar en archivo.
     *
     * @param u Usuario a convertir
     * @return Cadena con formato username,password,rol
     */
    private String usuarioToLinea(Usuario u) {
        return u.getUsername() + "," + u.getContrasenia() + "," + (u.getRol() != null ? u.getRol().name() : "USUARIO");
    }
    /**
     * Convierte una línea de texto del archivo a un objeto Usuario.
     *
     * @param linea Línea leída del archivo
     * @return Objeto Usuario construido o null si la línea es inválida
     */
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
    /**
     * Busca y devuelve un usuario cuyo nombre de usuario y contraseña coincidan.
     *
     * @param username Nombre de usuario
     * @param contrasenia Contraseña
     * @return Usuario autenticado o null si no coincide
     */
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
    /**
     * Crea un nuevo usuario y lo guarda en el archivo.
     *
     * @param usuario Usuario a agregar
     */
    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        usuarios.add(usuario);
        guardarLista(usuarios);
    }
    /**
     * Busca un usuario en el archivo por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
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
    /**
     * Elimina un usuario del archivo según su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios = usuarios.stream()
                .filter(u -> !u.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
        guardarLista(usuarios);
    }
    /**
     * Actualiza un usuario en el archivo. Si el usuario ya existe, lo reemplaza.
     *
     * @param usuario Usuario con datos actualizados
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        usuarios = usuarios.stream()
                .map(u -> u.getUsername().equalsIgnoreCase(usuario.getUsername()) ? usuario : u)
                .collect(Collectors.toList());
        guardarLista(usuarios);
    }
    /**
     * Lista todos los usuarios almacenados en el archivo.
     *
     * @return Lista completa de usuarios
     */
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
    /**
     * Lista todos los usuarios cuyo nombre de usuario comience con el texto dado.
     *
     * @param username Parte inicial del nombre de usuario
     * @return Lista de usuarios que coinciden con el filtro
     */
    @Override
    public List<Usuario> listarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        return usuarios.stream()
                .filter(u -> u.getUsername().toLowerCase().startsWith(username.toLowerCase()))
                .collect(Collectors.toList());
    }
    /**
     * Guarda toda la lista de usuarios en el archivo, sobrescribiendo el contenido anterior.
     *
     * @param usuarios Lista de usuarios a guardar
     */
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
