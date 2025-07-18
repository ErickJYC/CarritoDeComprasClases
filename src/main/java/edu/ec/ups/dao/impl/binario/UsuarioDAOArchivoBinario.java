package edu.ec.ups.dao.impl.binario;

import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementación de la interfaz UsuarioDAO que utiliza archivos binarios
 * para almacenar, leer y actualizar objetos de tipo Usuario.
 *
 * Permite realizar operaciones CRUD, autenticación y búsqueda sobre usuarios.
 * La información se guarda en una lista serializada dentro de un archivo binario.
 *
 * Al inicializarse, crea automáticamente un usuario administrador por defecto si no existe el archivo.
 *
 */
public class UsuarioDAOArchivoBinario implements UsuarioDAO {
    /** Ruta del archivo binario donde se almacenan los usuarios */
    private String rutaArchivo;
    /**
     * Constructor que inicializa el DAO con la ruta del archivo binario.
     * Si el archivo no existe, lo crea con una lista vacía y registra un usuario administrador por defecto.
     *
     * @param rutaArchivo Ruta del archivo binario
     */
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
    /**
     * Lee la lista de usuarios desde el archivo binario.
     *
     * @return Lista de usuarios cargada del archivo, o vacía si no hay datos
     */
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
    /**
     * Guarda la lista de usuarios en el archivo binario.
     *
     * @param lista Lista de usuarios a guardar
     */
    private void guardarLista(List<Usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Autentica un usuario verificando el nombre de usuario y la contraseña.
     *
     * @param username Nombre de usuario
     * @param contrasenia Contraseña
     * @return Usuario autenticado o null si las credenciales no coinciden
     */
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
    /**
     * Crea un nuevo usuario y lo agrega a la lista en el archivo.
     *
     * @param usuario Usuario a crear
     */
    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        usuarios.add(usuario);
        guardarLista(usuarios);
    }
    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario
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
     * Elimina un usuario de la lista según su nombre de usuario.
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
     * Actualiza un usuario existente en el archivo si coincide su nombre de usuario.
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
     * @return Lista de todos los usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        return leerLista();
    }
    /**
     * Lista todos los usuarios cuyo nombre de usuario empieza con el valor especificado.
     *
     * @param username Prefijo del nombre de usuario
     * @return Lista de usuarios que coinciden
     */
    @Override
    public List<Usuario> listarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        return usuarios.stream()
                .filter(u -> u.getUsername().toLowerCase().startsWith(username.toLowerCase()))
                .collect(Collectors.toList());
    }

}
