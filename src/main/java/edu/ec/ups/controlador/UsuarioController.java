package edu.ec.ups.controlador;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.PreguntaCuestionario;
import edu.ec.ups.modelo.Rol;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;
import edu.ec.ups.vista.loginView.PreguntaView;
import edu.ec.ups.vista.loginView.PreguntasRecuperarContView;
import edu.ec.ups.vista.loginView.RegistrarView;
import edu.ec.ups.vista.usuarioView.UsuarioCrearView;
import edu.ec.ups.vista.usuarioView.UsuarioEliminarView;
import edu.ec.ups.vista.usuarioView.UsuarioListarView;
import edu.ec.ups.vista.usuarioView.UsuarioModificarView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private UsuarioCrearView usuarioCrearView;
    private UsuarioListarView usuarioListarView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioModificarView usuarioModificarView;
    private PreguntaDAO cuestionarioDAO;
    private RegistrarView registrarView;
    private PreguntasRecuperarContView recuperarView;
    private final MensajeInternacionalizacionHandler mi;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, PreguntaDAO cuestionarioDAO, MensajeInternacionalizacionHandler mi) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.cuestionarioDAO = cuestionarioDAO;
        this.mi = mi;
        this.usuario = null;
        this.registrarView = new RegistrarView(mi); // Inicializar registrarView aquí
        configurarEventosEnVistas();
        recuperarView = new PreguntasRecuperarContView(mi);
    }

    public UsuarioController(UsuarioDAO usuarioDAO, UsuarioCrearView usuarioCrearView,
                             UsuarioListarView usuarioListarView, UsuarioEliminarView usuarioEliminarView,
                             UsuarioModificarView usuarioModificarView, MensajeInternacionalizacionHandler mi, RegistrarView registrarView) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioModificarView = usuarioModificarView;
        this.mi = mi;
        this.registrarView = registrarView;
        configurarEventosUsuarios();
    }

    private void configurarEventosEnVistas() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());

        loginView.getBtnRegistrarse().addActionListener(e -> crearUsuario());

        loginView.getBtnOlvidar().addActionListener(e -> recuperar());

        loginView.getBtnSalir().addActionListener(e -> salir());

        loginView.getCbxIdiomas().addActionListener(e -> cambiarIdioma());

    }

    private void configurarEventosUsuarios(){
        usuarioCrearView.getBtnRegistrar().addActionListener(e -> registrarUsuario());

        usuarioListarView.getBtnBuscar().addActionListener(e -> buscarUsuario());

        usuarioListarView.getBtnListar().addActionListener(e -> listarUsuarios());

        usuarioEliminarView.getBtnBuscar().addActionListener(e -> buscarUsuarioParaEliminar());

        usuarioEliminarView.getBtnEliminar().addActionListener(e -> eliminarUsuario());

        usuarioModificarView.getBtnBuscar().addActionListener(e -> buscarUsuarioParaModificar());

        usuarioModificarView.getBtnEditar().addActionListener(e -> modificarUsuario());
    }

    private void cambiarIdioma() {
        String seleccion = (String) loginView.getCbxIdiomas().getSelectedItem();

        if (seleccion != null) {
            switch (seleccion) {
                case "Español":
                    mi.setLenguaje("es", "EC");
                    break;
                case "English":
                    mi.setLenguaje("en", "US");
                    break;
                case "Français":
                    mi.setLenguaje("fr", "FR");
                    break;
            }
            loginView.actualizarTextos(mi);
            if (registrarView != null) registrarView.cambiarIdioma();
            if (recuperarView != null) recuperarView.cambiarIdioma();

        }
    }

    private void salir() {
        loginView.dispose();
        System.exit(0);
    }
    private void registrarUsuario() {
        String nombreCompleto = usuarioCrearView.getTxtNombreCompleto().getText().trim();
        String username = usuarioCrearView.getTxtUsername().getText().trim();
        String contrasenia = usuarioCrearView.getTxtPassword().getText().trim();
        String celular = usuarioCrearView.getTxtCelular().getText().trim();
        String correo = usuarioCrearView.getTxtCorreo().getText().trim();

        Object diaObj = usuarioCrearView.getCbxDia().getSelectedItem();
        Object mesObj = usuarioCrearView.getCbxMes().getSelectedItem();
        Object anioObj = usuarioCrearView.getCbxAño().getSelectedItem();

        if (nombreCompleto.isEmpty() || username.isEmpty() || contrasenia.isEmpty()
                || celular.isEmpty() || correo.isEmpty() || diaObj == null || mesObj == null || anioObj == null) {
            usuarioCrearView.mostrarMensaje(mi.get("mensaje.campos.obligatorios"));
            return;
        }

        // 🚫 Validar que el username sea solo números
        if (!username.matches("\\d+")) {
            usuarioCrearView.mostrarMensaje(mi.get("mensaje.usuario.solo_numeros")); // <-- agregar a properties
            return;
        }

        // 🚫 Validar celular
        if (!celular.matches("^\\d{10}$")) {
            usuarioCrearView.mostrarMensaje(mi.get("usuario.celular.invalido")); // ya lo tienes
            return;
        }

        // 🚫 Validar correo electrónico
        if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            usuarioCrearView.mostrarMensaje(mi.get("mensaje.correo.invalido")); // ya lo tienes
            return;
        }

        // 🚫 Validar que la contraseña no tenga espacios
        if (contrasenia.contains(" ")) {
            usuarioCrearView.mostrarMensaje(mi.get("mensaje.contrasena.espacios")); // ya lo tienes
            return;
        }

        // ✅ Verificar si ya existe el usuario
        if (usuarioDAO.buscarPorUsername(username) != null) {
            usuarioCrearView.mostrarMensaje(mi.get("usuario.nombre.en.uso"));
            return;
        }

        String fechaNacimiento = diaObj + "/" + mesObj + "/" + anioObj;
        Rol rol = usuarioCrearView.getRolSeleccionado();

        Usuario nuevoUsuario = new Usuario(username, contrasenia, rol);
        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setCelular(celular);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setFechaNacimiento(fechaNacimiento);

        usuarioDAO.crear(nuevoUsuario);

        usuarioCrearView.mostrarMensaje(mi.get("usuario.creado") + ": " + username);
        usuarioCrearView.limpiarCampos();
    }



    private void recuperar() {
        boolean confirmado = loginView.mostrarMensajePregunta(mi.get("login.mensaje.pregunta_recuperar"));
        if (confirmado) {
            String username = loginView.getTxtUsername().getText().trim();

            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            if (usuario == null) {
                loginView.mostrarMensaje(mi.get("login.mensaje.usuario_no_encontrado"));
                return;
            }

            if (usuario.getRol() == Rol.ADMINISTRADOR) {
                loginView.mostrarMensaje(mi.get("login.mensaje.recuperacion_no_disponible_admin"));
                return;
            }

            PreguntaCuestionario cuestionario = cuestionarioDAO.buscarPorUsername(username);
            if (cuestionario == null || cuestionario.getPreguntas().isEmpty()) {
                loginView.mostrarMensaje(mi.get("login.mensaje.sin_preguntas"));
                return;
            }

            PreguntasRecuperarContView recuperarView = new PreguntasRecuperarContView(mi);
            PreguntaController controller = new PreguntaController(
                    recuperarView, cuestionarioDAO, usuarioDAO,usuario,  username, usuario.getContrasenia(), mi
            );

            recuperarView.setVisible(true);
            loginView.setVisible(false);

            recuperarView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            recuperarView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loginView.setVisible(true);
                }
            });

        } else {
            loginView.mostrarMensaje(mi.get("login.mensaje.recuperacion_cancelada"));
        }
    }

    private void modificarUsuario() {
        String nombreBusqueda = usuarioModificarView.getTxtName().getText().trim();

        Usuario usuario1 = usuarioDAO.buscarPorUsername(nombreBusqueda);
        if (usuario1 == null) {
            usuarioModificarView.mostrarMensaje(mi.get("usuario.no.encontrado"));
            return;
        }

        String username = usuarioModificarView.getTxtUsername().getText().trim();
        String contrasenia = usuarioModificarView.getTxtContrasenia().getText().trim();
        String nombreCompleto = usuarioModificarView.getTxtNombreCompleto().getText().trim();
        String correo = usuarioModificarView.getTxtCorreo().getText().trim();
        String celular = usuarioModificarView.getTxtCelular().getText().trim();

        Object diaObj = usuarioModificarView.getCbxDia().getSelectedItem();
        Object mesObj = usuarioModificarView.getCbxMes().getSelectedItem();
        Object anioObj = usuarioModificarView.getCbxAño().getSelectedItem();

        if (username.isEmpty() || contrasenia.isEmpty() || nombreCompleto.isEmpty()
                || correo.isEmpty() || celular.isEmpty() || diaObj == null || mesObj == null || anioObj == null) {
            usuarioModificarView.mostrarMensaje(mi.get("mensaje.campos.obligatorios"));
            return;
        }
        if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            registrarView.mostrarMensaje(mi.get("mensaje.correo.invalido"));
            return;
        }

        if (!celular.matches("^\\d{10}$")) {
            registrarView.mostrarMensaje(mi.get("usuario.celular.invalido"));
            return;
        }


        String fechaNacimiento = diaObj + "/" + mesObj + "/" + anioObj;

        usuario1.setUsername(username);
        usuario1.setContrasenia(contrasenia);
        usuario1.setNombreCompleto(nombreCompleto);
        usuario1.setCorreo(correo);
        usuario1.setCelular(celular);
        usuario1.setFechaNacimiento(fechaNacimiento);

        usuarioDAO.actualizar(usuario1);

        usuarioModificarView.mostrarMensaje(mi.get("usuario.modificado") + ": " + username);
        usuarioModificarView.limpiarCampos();
    }


    private void buscarUsuarioParaModificar() {
        String usernameBusqueda = usuarioModificarView.getTxtName().getText().trim();

        Usuario usuario = usuarioDAO.buscarPorUsername(usernameBusqueda);
        if (usuario == null) {
            usuarioModificarView.mostrarMensaje(mi.get("usuario.no.encontrado"));
            usuarioModificarView.getTxtName().setText("");
            return;
        }

        usuarioModificarView.getTxtUsername().setText(usuario.getUsername());
        usuarioModificarView.getTxtContrasenia().setText(usuario.getContrasenia());
        usuarioModificarView.getTxtNombreCompleto().setText(usuario.getNombreCompleto());
        usuarioModificarView.getTxtCorreo().setText(usuario.getCorreo());
        usuarioModificarView.getTxtCelular().setText(usuario.getCelular());

        String[] fecha = usuario.getFechaNacimiento().split("/");
        if (fecha.length == 3) {
            usuarioModificarView.getCbxDia().setSelectedItem(Integer.parseInt(fecha[0]));
            usuarioModificarView.getCbxMes().setSelectedItem(fecha[1]);
            usuarioModificarView.getCbxAño().setSelectedItem(Integer.parseInt(fecha[2]));
        }
    }


    private void buscarUsuarioParaEliminar() {
        usuarioEliminarView.getModelo().setRowCount(0);
        String usernameBuscado = usuarioEliminarView.getTxtUsuario().getText().trim();
        boolean encontrado = false;

        for (Usuario usuario : usuarioDAO.listarTodos()) {
            if (usuario.getUsername().equals(usernameBuscado)) {
                Object[] fila = {
                        usuario.getNombreCompleto(),
                        usuario.getUsername(),
                        usuario.getContrasenia(),
                        usuario.getCorreo(),
                        usuario.getCelular(),
                        usuario.getFechaNacimiento(),
                        usuario.getRol().toString()
                };
                usuarioEliminarView.getModelo().addRow(fila);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            usuarioEliminarView.mostrarMensaje(mi.get("usuario.no.encontrado"));
            usuarioEliminarView.getTxtUsuario().setText("");
        }

    }

    private void eliminarUsuario() {
        String username = usuarioEliminarView.getTxtUsuario().getText();
        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario == null) {
            usuarioEliminarView.mostrarMensaje(mi.get("usuario.no.encontrado"));
            return;
        }
        usuarioDAO.eliminar(username);
        usuarioEliminarView.mostrarMensaje(mi.get("usuario.eliminado") + ": " + username);
        usuarioEliminarView.getTxtUsuario().setText("");
    }

    private void listarUsuarios() {
        usuarioListarView.getModelo().setRowCount(0);
        for (Usuario usuario : usuarioDAO.listarTodos()) {
            Object[] fila = {
                    usuario.getNombreCompleto(),
                    usuario.getUsername(),
                    usuario.getContrasenia(),
                    usuario.getCorreo(),
                    usuario.getCelular(),
                    usuario.getFechaNacimiento(),
                    usuario.getRol().toString()
            };
            usuarioListarView.getModelo().addRow(fila);
        }
        usuarioListarView.mostrarMensaje(mi.get("usuario.listado.exito"));
    }

    private void buscarUsuario() {
        usuarioListarView.getModelo().setRowCount(0);
        String nombreUsuario = usuarioListarView.getTxtUsuario().getText().trim();

        for (Usuario usuario : usuarioDAO.listarTodos()) {
            if (usuario.getUsername().equalsIgnoreCase(nombreUsuario)) {
                Object[] fila = {
                        usuario.getNombreCompleto(),
                        usuario.getUsername(),
                        usuario.getContrasenia(),
                        usuario.getCorreo(),
                        usuario.getCelular(),
                        usuario.getFechaNacimiento(),
                        usuario.getRol().toString()
                };
                usuarioListarView.getModelo().addRow(fila);
                break;
            }
        }
    }

    private void registrarNuevoUsuario() {
        String nombreCompleto = registrarView.getTxtNombreCompleto().getText().trim();
        String username = registrarView.getTxtUsuario().getText().trim();
        String contrasenia = registrarView.getTxtContrasena().getText().trim();
        String celular = registrarView.getTxtCelular().getText().trim();
        String correo = registrarView.getTxtCorreo().getText().trim();

        Object dia = registrarView.getCbxDia().getSelectedItem();
        Object mes = registrarView.getCbxMes().getSelectedItem();
        Object año = registrarView.getCbxAño().getSelectedItem();

        if (nombreCompleto.isEmpty() || username.isEmpty() || contrasenia.isEmpty()
                || celular.isEmpty() || correo.isEmpty() || dia == null || mes == null || año == null) {
            registrarView.mostrarMensaje(mi.get("mensaje.campos.obligatorios"));
            return;
        }

        // Creamos el usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);       // aquí puede fallar por letras o longitud
        nuevoUsuario.setContrasenia(contrasenia); // aquí puede fallar por seguridad
        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setFechaNacimiento(dia + "/" + mes + "/" + año);
        nuevoUsuario.setCelular(celular);         // aquí puede fallar si no son 10 dígitos
        nuevoUsuario.setCorreo(correo);           // aquí puede fallar si no tiene @ y .

        // ✅ Validación antes de guardar
        if (nuevoUsuario.getUsername() == null || nuevoUsuario.getContrasenia() == null
                || nuevoUsuario.getCelular() == null || nuevoUsuario.getCorreo() == null) {
            registrarView.mostrarMensaje("No se puede registrar. Corrige los campos inválidos.");
            return;
        }

        // Verificamos si ya existe
        if (usuarioDAO.buscarPorUsername(username) != null) {
            registrarView.mostrarMensaje(mi.get("usuario.nombre.en.uso"));
            return;
        }

        // Guardamos
        usuarioDAO.crear(nuevoUsuario);
        registrarView.mostrarMensaje(mi.get("usuario.creado"));
        registrarView.limpiarCampos();
        registrarView.dispose();
        loginView.setVisible(true);
    }


    private void autenticar() {
        String username = loginView.getTxtUsername().getText().trim();
        String contrasenia = loginView.getTxtContrasenia().getText();

        // Verifica si hay campos vacíos
        if (username.isEmpty() || contrasenia.isEmpty()) {
            loginView.mostrarMensaje(mi.get("mensaje.campos.obligatorios"));
            return;
        }

        // ✅ Validación: contraseña no debe tener espacios
        if (contrasenia.contains(" ")) {
            loginView.mostrarMensaje(mi.get("mensaje.contrasena.espacios"));
            return;
        }

        // Continuar con la autenticación
        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            loginView.mostrarMensaje(mi.get("login.mensaje.usuario_o_contrasena_incorrectos"));
        } else {
            PreguntaCuestionario cuestionario = cuestionarioDAO.buscarPorUsername(username);
            if (cuestionario == null || cuestionario.getPreguntas().size() < 3) {
                loginView.mostrarMensaje(mi.get("login.mensaje.completar_cuestionario"));

                PreguntaView cuestionarioView = new PreguntaView(mi);
                PreguntaController controller = new PreguntaController(
                        cuestionarioView, cuestionarioDAO, username, mi, usuarioDAO
                );
                cuestionarioView.setVisible(true);
                loginView.setVisible(false);

                cuestionarioView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cuestionarioView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loginView.setVisible(true);
                    }
                });

            } else {
                loginView.dispose();
            }
        }
    }

    private void crearUsuario() {
        boolean confirmado = loginView.mostrarMensajePregunta(mi.get("usuario.crear.confirmacion"));
        if (confirmado) {
            this.registrarView = new RegistrarView(mi);

            registrarView.getBtnRegistrar().addActionListener(e -> registrarNuevoUsuario());
            registrarView.getBtnLimpiar().addActionListener(e -> registrarView.limpiarCampos());

            registrarView.setVisible(true);
            loginView.setVisible(false);

            registrarView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            registrarView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loginView.setVisible(true);
                }
            });
        } else {
            loginView.mostrarMensaje(mi.get("usuario.crear.cancelado"));
        }
    }
    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}
