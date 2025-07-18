package edu.ec.ups.controlador;

import edu.ec.ups.dao.PreguntaDAO;
import edu.ec.ups.dao.UsuarioDAO;
import edu.ec.ups.modelo.Pregunta;
import edu.ec.ups.modelo.PreguntaCuestionario;
import edu.ec.ups.modelo.Usuario;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.PreguntaView;
import edu.ec.ups.vista.loginView.PreguntasRecuperarContView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador que maneja la lógica relacionada con el cuestionario de preguntas de seguridad,
 * utilizado tanto en el registro de nuevos usuarios como en la recuperación de contraseñas.
 * Permite mostrar preguntas, guardar respuestas, validar respuestas, actualizar la contraseña,
 * y aplicar internacionalización.
 *
 * Se conecta con las vistas `PreguntaView` y `PreguntasRecuperarContView`,
 * y utiliza DAOs para manejar preguntas y usuarios.
 */
public class PreguntaController {

    /** Lista de preguntas aleatorias mostradas al usuario */
    private List<Pregunta> preguntasAleatorias;

    /** DAO encargado de guardar y recuperar cuestionarios */
    private final PreguntaDAO cuestionarioDAO;

    /** Cuestionario actual que se está llenando o validando */
    private final PreguntaCuestionario cuestionario;

    /** Vista de recuperación de contraseña */
    private final PreguntasRecuperarContView recuperarView;

    /** Vista para responder el cuestionario de seguridad */
    private final PreguntaView cuestionarioView;

    /** Contraseña temporal del usuario (usada solo en recuperación) */
    private String contraseniaU;

    /** Usuario autenticado o en proceso de autenticación */
    private Usuario usuario;

    /** DAO que gestiona operaciones de usuario (como actualizar contraseña) */
    private final UsuarioDAO usuarioDAO;

    /** Manejador de mensajes multilenguaje (internacionalización) */
    private final MensajeInternacionalizacionHandler mi;

    /**
     * Constructor para uso durante el registro de un usuario.
     * Carga una lista de preguntas y permite guardar respuestas.
     *
     * @param vista Vista del cuestionario de seguridad.
     * @param dao DAO para persistencia de preguntas.
     * @param username Nombre del usuario.
     * @param mi Manejador de internacionalización.
     * @param usuarioDAO DAO de usuario para actualizar datos.
     */
    public PreguntaController(PreguntaView vista, PreguntaDAO dao, String username,
                              MensajeInternacionalizacionHandler mi, UsuarioDAO usuarioDAO) {
        this.mi = mi;
        this.cuestionarioView = vista;
        this.cuestionarioDAO = dao;
        this.usuarioDAO = usuarioDAO;
        this.recuperarView = null;
        this.contraseniaU = null;

        this.cuestionario = new PreguntaCuestionario(username);
        this.cuestionario.aplicarIdioma(mi);
        this.preguntasAleatorias = cuestionario.preguntasPorDefecto(mi);

        // Obtener lista de preguntas y seleccionar las primeras 10
        List<Pregunta> todasLasPreguntas = cuestionario.preguntasPorDefecto(mi);
        preguntasAleatorias = new ArrayList<>();
        for (int i = 0; i < 10 && i < todasLasPreguntas.size(); i++) {
            preguntasAleatorias.add(todasLasPreguntas.get(i));
        }

        cargarComboPreguntas();
        configurarEventosCuestionario();
    }

    /**
     * Constructor para uso en recuperación de contraseña.
     * Carga las preguntas previamente respondidas y permite validar respuestas.
     *
     * @param recuperarView Vista para recuperación.
     * @param dao DAO para preguntas.
     * @param usuarioDAO DAO de usuario.
     * @param usuario Usuario que solicita la recuperación.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña temporal.
     * @param mi Manejador de internacionalización.
     */
    public PreguntaController(PreguntasRecuperarContView recuperarView, PreguntaDAO dao,
                              UsuarioDAO usuarioDAO, Usuario usuario,
                              String username, String contrasenia,
                              MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        this.cuestionarioDAO = dao;
        this.usuarioDAO = usuarioDAO;
        this.cuestionarioView = null;
        this.recuperarView = recuperarView;
        this.contraseniaU = contrasenia;
        this.usuario = usuarioDAO.buscarPorUsername(username);

        this.cuestionario = cuestionarioDAO.buscarPorUsername(username);
        if (cuestionario == null) {
            recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.noPreguntas"));
            recuperarView.dispose();
            return;
        }

        preguntasAleatorias = cuestionario.getPreguntas();

        if (preguntasAleatorias.size() < 3) {
            recuperarView.mostrarMensaje("No hay suficientes preguntas para recuperación.");
            recuperarView.dispose();
            return;
        }

        // Mostrar las 3 preguntas en la vista
        recuperarView.getLblPregunta1().setText(preguntasAleatorias.get(0).getEnunciado());
        recuperarView.getLblPregunta2().setText(preguntasAleatorias.get(1).getEnunciado());
        recuperarView.getLblPregunta3().setText(preguntasAleatorias.get(2).getEnunciado());

        configurarEventosRecuperar();
    }

    /**
     * Asocia los botones de la vista de registro con sus eventos:
     * guardar, terminar, y seleccionar pregunta.
     */
    private void configurarEventosCuestionario() {
        cuestionarioView.getCbxPreguntas().addActionListener(e -> preguntasCuestionario());
        cuestionarioView.getBtnGuardar().addActionListener(e -> guardar());
        cuestionarioView.getBtnTerminar().addActionListener(e -> finalizar());
    }

    /**
     * Asocia los botones de la vista de recuperación con sus eventos:
     * enviar respuestas y terminar proceso.
     */
    private void configurarEventosRecuperar() {
        recuperarView.getBtnEnviar().addActionListener(e -> comprobarTodasRespuestas());
        recuperarView.getBtnTerminar().addActionListener(e -> finalizarRecuperar());
    }

    /**
     * Verifica que las respuestas a las tres preguntas de seguridad sean correctas.
     * Si son válidas, permite que el usuario cambie su contraseña.
     */
    private void comprobarTodasRespuestas() {
        String respuesta1 = recuperarView.getTxtRespuesta1().getText().trim();
        String respuesta2 = recuperarView.getTxtRespuesta2().getText().trim();
        String respuesta3 = recuperarView.getTxtRespuesta3().getText().trim();

        if (respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.respuestaVacia"));
            return;
        }

        boolean r1Correcta = respuesta1.equalsIgnoreCase(preguntasAleatorias.get(0).getRespuesta());
        boolean r2Correcta = respuesta2.equalsIgnoreCase(preguntasAleatorias.get(1).getRespuesta());
        boolean r3Correcta = respuesta3.equalsIgnoreCase(preguntasAleatorias.get(2).getRespuesta());

        if (r1Correcta && r2Correcta && r3Correcta) {
            // Si las respuestas son correctas, permitir cambio de contraseña
            int opcion = JOptionPane.showConfirmDialog(
                    recuperarView,
                    mi.get("cuestionario.recuperar.confirmarCambio"),
                    mi.get("cuestionario.recuperar.tituloCambio"),
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                JPasswordField nuevaContrasena = new JPasswordField();
                int resultado = JOptionPane.showConfirmDialog(
                        recuperarView,
                        nuevaContrasena,
                        mi.get("cuestionario.recuperar.ingreseNueva"),
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (resultado == JOptionPane.OK_OPTION) {
                    String nueva = new String(nuevaContrasena.getPassword());

                    if (nueva.trim().isEmpty()) {
                        recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.campoVacio"));
                        return;
                    }

                    if (nueva.matches(".*\\s+.*")) {
                        recuperarView.mostrarMensaje(mi.get("mensaje.contrasena.espacios"));
                        return;
                    }

                    // NUEVAS VALIDACIONES AÑADIDAS:
                    if (nueva.length() < 6) {
                        recuperarView.mostrarMensaje(mi.get("mensaje.contrasena.minima"));
                        return;
                    }

                    if (!nueva.matches(".*[A-Z].*")) {
                        recuperarView.mostrarMensaje(mi.get("mensaje.contrasena.mayuscula"));
                        return;
                    }

                    if (!nueva.matches(".*[a-z].*")) {
                        recuperarView.mostrarMensaje(mi.get("mensaje.contrasena.minuscula"));
                        return;
                    }

                    if (!nueva.matches(".*[@_-].*")) {
                        recuperarView.mostrarMensaje(mi.get("mensaje.contrasena.caracterEspecial"));
                        return;
                    }

                    // Si pasa todas las validaciones:
                    usuario.setContrasenia(nueva);
                    usuarioDAO.actualizar(usuario);
                    recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.exito"));
                }
            }
        } else {
            recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.incorrecta"));
        }
    }

    /**
     * Cierra la ventana de recuperación de contraseña.
     */
    private void finalizarRecuperar() {
        recuperarView.dispose();
    }

    /**
     * Muestra el enunciado de la pregunta seleccionada en el combo box,
     * y su respuesta previa si ya fue respondida.
     */
    private void preguntasCuestionario() {
        int index = cuestionarioView.getCbxPreguntas().getSelectedIndex();
        if (index >= 0) {
            Pregunta r = preguntasAleatorias.get(index);
            cuestionarioView.getLblPregunta().setText(r.getEnunciado());

            Pregunta respondido = cuestionario.buscarPreguntaPorId(r.getId());
            if (respondido != null) {
                cuestionarioView.getTxtRespuesta().setText(respondido.getRespuesta());
            } else {
                cuestionarioView.getTxtRespuesta().setText("");
            }
        }
    }

    /**
     * Guarda la respuesta ingresada por el usuario para la pregunta seleccionada.
     * Solo permite guardar una vez por cada pregunta.
     */
    private void guardar() {
        int index = cuestionarioView.getCbxPreguntas().getSelectedIndex();
        if (index < 0) return;

        String texto = cuestionarioView.getTxtRespuesta().getText().trim();
        if (texto.isEmpty()) {
            cuestionarioView.mostrarMensaje(mi.get("cuestionario.guardar.vacia"));
            return;
        }

        Pregunta seleccionada = preguntasAleatorias.get(index);

        if (cuestionario.buscarPreguntaPorId(seleccionada.getId()) != null) {
            cuestionarioView.mostrarMensaje(mi.get("cuestionario.guardar.yaRespondida"));
            return;
        }

        seleccionada.setRespuesta(texto);
        cuestionario.agregarPregunta(seleccionada);
        cuestionarioView.mostrarMensaje(mi.get("cuestionario.guardar.ok"));
    }

    /**
     * Finaliza el cuestionario de seguridad si el usuario ha respondido al menos 3 preguntas.
     * Guarda el cuestionario en la base de datos.
     */
    private void finalizar() {
        if (cuestionario.getPreguntas().size() < 3) {
            cuestionarioView.mostrarMensaje(mi.get("cuestionario.finalizar.minimo"));
            return;
        }

        cuestionarioDAO.guardar(cuestionario);
        cuestionarioView.mostrarMensaje(mi.get("cuestionario.finalizar.ok"));
        cuestionarioView.dispose();
    }

    /**
     * Carga los enunciados de las preguntas aleatorias en el combo box de selección.
     */
    private void cargarComboPreguntas() {
        int cantidadPreguntas = preguntasAleatorias.size();

        for (int i = 0; i < cantidadPreguntas; i++) {
            String enunciado = preguntasAleatorias.get(i).getEnunciado();
            cuestionarioView.getCbxPreguntas().addItem(enunciado);
        }

        if (cantidadPreguntas > 0) {
            cuestionarioView.getLblPregunta().setText(preguntasAleatorias.get(0).getEnunciado());
        }
    }
    /**
     * Actualiza los enunciados del combo box aplicando el nuevo idioma seleccionado.
     */
    private void actualizarComboPreguntas() {
        // Aplica el nuevo idioma a las preguntas
        for (Pregunta p : preguntasAleatorias) {
            p.setMensajeIdioma(mi);
        }

        // Limpia el combo actual
        cuestionarioView.getCbxPreguntas().removeAllItems();

        // Agrega los nuevos enunciados traducidos
        for (Pregunta p : preguntasAleatorias) {
            cuestionarioView.getCbxPreguntas().addItem(p.getEnunciado());
        }

        // Opcional: mostrar la primera pregunta
        if (!preguntasAleatorias.isEmpty()) {
            cuestionarioView.getLblPregunta().setText(preguntasAleatorias.get(0).getEnunciado());
        }
    }
}
