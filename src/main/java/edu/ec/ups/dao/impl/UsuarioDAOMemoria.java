package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.*;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de UsuarioDAO que gestiona usuarios completamente en memoria.
 * Incluye autenticación, creación, eliminación, búsqueda y actualización de usuarios,
 * así como asociación con preguntas de seguridad.
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    /** Lista que almacena los usuarios en memoria */
    private List<Usuario> usuarios;
    /** DAO para manejar preguntas de seguridad asociadas al usuario */
    private PreguntaDAO cuestionarioDAO;
    /** Manejador de internacionalización (no inicializado en este constructor) */
    private MensajeInternacionalizacionHandler  mi;
    /**
     * Constructor que inicializa la lista y agrega usuarios y preguntas por defecto.
     *
     * @param cuestionarioDAO DAO para guardar y acceder a los cuestionarios de preguntas de seguridad
     */
    public UsuarioDAOMemoria(PreguntaDAO cuestionarioDAO) {
        this.usuarios = new ArrayList<>();
        this.cuestionarioDAO = cuestionarioDAO;

        // Usuarios por defecto
        crear(new Usuario("0102896362", "Admin@1", Rol.ADMINISTRADOR));
        crear(new Usuario("0102896364", "User@1", Rol.USUARIO));

        // Preguntas por defecto para el usuario 0102896362
        PreguntaCuestionario cuestionarioAdmin = new PreguntaCuestionario("0102896362");
        List<Pregunta> preguntas = cuestionarioAdmin.preguntasPorDefecto(mi);

        preguntas.get(0).setRespuesta("Negro");
        preguntas.get(1).setRespuesta("Kobu");
        preguntas.get(2).setRespuesta("Churrasco");

        // Agregar respuestas al cuestionario
        cuestionarioAdmin.agregarPregunta(preguntas.get(0));
        cuestionarioAdmin.agregarPregunta(preguntas.get(1));
        cuestionarioAdmin.agregarPregunta(preguntas.get(2));

        cuestionarioDAO.guardar(cuestionarioAdmin);
    }

    /**
     * Autentica un usuario comparando su nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario
     * @param contrasenia Contraseña
     * @return Usuario autenticado o null si no coincide
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Agrega un nuevo usuario a la lista en memoria.
     *
     * @param usuario Usuario a crear
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario por su nombre exacto.
     *
     * @param username Nombre de usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario de la lista, buscando por su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario Usuario con datos actualizados
     */
    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAux = usuarios.get(i);
            if (usuarioAux.getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    /**
     * Devuelve la lista completa de usuarios en memoria.
     *
     * @return Lista de usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Permite establecer el DAO de preguntas de seguridad si no fue proporcionado en el constructor.
     *
     * @param username para gestionar los cuestionarios
     */
    @Override
    public List<Usuario> listarPorUsername(String username) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().startsWith(username)) {
                usuariosEncontrados.add(usuario);
            }
        }
        return usuariosEncontrados;
    }
    public void setPreguntaDAO(PreguntaDAO cuestionarioDAO) {
        this.cuestionarioDAO = cuestionarioDAO;
    }
}
