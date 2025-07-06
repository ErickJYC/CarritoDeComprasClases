package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;
import edu.ec.ups.modelo.Respuesta;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {

    // Lista que almacena los usuarios en memoria
    private List<Usuario> usuarios;
    private PreguntaDAO cuestionarioDAO;

    // Constructor que inicializa la lista y agrega usuarios por defecto
    public UsuarioDAOMemoria(PreguntaDAO cuestionarioDAO) {
        this.usuarios = new ArrayList<>();
        this.cuestionarioDAO = cuestionarioDAO;

        // Usuarios por defecto
        crear(new Usuario("", "", Rol.ADMINISTRADOR));
        crear(new Usuario("admin", "12345", Rol.ADMINISTRADOR));
        crear(new Usuario("user", "12345", Rol.USUARIO));

        // Preguntas por defecto para el usuario admin
        PreguntaCuestionario cuestionarioAdmin = new PreguntaCuestionario("admin");
        List<Respuesta> preguntas = cuestionarioAdmin.preguntasPorDefecto();

        preguntas.get(0).setRespuesta("Negro");
        preguntas.get(1).setRespuesta("Kobu");
        preguntas.get(2).setRespuesta("Churrasco");

        // Agregar respuestas al cuestionario
        cuestionarioAdmin.agregarRespuesta(preguntas.get(0));
        cuestionarioAdmin.agregarRespuesta(preguntas.get(1));
        cuestionarioAdmin.agregarRespuesta(preguntas.get(2));

        cuestionarioDAO.guardar(cuestionarioAdmin);
    }

    // Autentica usuario verificando username y contraseña
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    // Crea un nuevo usuario y lo agrega a la lista
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Busca un usuario por username exacto
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    // Elimina un usuario por username
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

    // Actualiza los datos de un usuario existente
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

    // Devuelve la lista completa de usuarios
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    // Lista usuarios cuyo username inicia con la cadena dada
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
}
