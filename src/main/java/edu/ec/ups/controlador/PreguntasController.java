package edu.ec.ups.controlador;

import edu.ec.ups.dao.CuestionarioDAO;
import edu.ec.ups.modelo.Preguntas;
import edu.ec.ups.modelo.Respuesta;
import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.PreguntasRecuperarContrasenaView;
import edu.ec.ups.vista.PreguntaView;

import java.util.ArrayList;
import java.util.List;

    public class PreguntasController {
        private final PreguntaView cuestionarioView;
        private final PreguntasRecuperarContrasenaView recuperarView;
        private final CuestionarioDAO cuestionarioDAO;
        private final Preguntas cuestionario;
        private List<Respuesta> preguntasAleatorias;
        private final MensajeInternacionalizacionHandler mi;
        private String contraseniaUsuario;


        public PreguntasController(PreguntaView vista, CuestionarioDAO dao, String username,
                                   MensajeInternacionalizacionHandler mi) {
            this.mi = mi;
            this.cuestionarioView = vista;
            this.cuestionarioDAO = dao;
            this.cuestionario = new Preguntas(username);
            this.recuperarView = null;
            this.contraseniaUsuario = null;

            this.cuestionario.aplicarIdioma(mi);

            List<Respuesta> todasLasPreguntas = cuestionario.preguntasPorDefecto();
            preguntasAleatorias = new ArrayList<>();

            for (int i = 0; i < 3 && i < todasLasPreguntas.size(); i++) {
                preguntasAleatorias.add(todasLasPreguntas.get(i));
            }


            cargarComboPreguntas();
            configurarEventosCuestionario();
        }


        public PreguntasController(PreguntasRecuperarContrasenaView recuperarView, CuestionarioDAO dao,
                                   String username, String contrasenia, MensajeInternacionalizacionHandler mi) {
            this.mi = mi;
            this.cuestionarioDAO = dao;
            this.cuestionarioView = null;
            this.recuperarView = recuperarView;
            this.contraseniaUsuario = contrasenia;

            this.cuestionario = cuestionarioDAO.buscarPorUsername(username);
            if (cuestionario == null) {
                recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.noPreguntas"));
                recuperarView.dispose();
                return;
            }

            preguntasAleatorias = cuestionario.getRespuestas();

            if (preguntasAleatorias.size() < 3) {
                recuperarView.mostrarMensaje("No hay suficientes preguntas para recuperación.");
                recuperarView.dispose();
                return;
            }


            recuperarView.getLblPregunta1().setText(preguntasAleatorias.get(0).getEnunciado());
            recuperarView.getLblPregunta2().setText(preguntasAleatorias.get(1).getEnunciado());
            recuperarView.getLblPregunta3().setText(preguntasAleatorias.get(2).getEnunciado());

            configurarEventosRecuperar();
        }

        private void configurarEventosCuestionario() {
            cuestionarioView.getCbxPreguntas().addActionListener(e -> preguntasCuestionario());
            cuestionarioView.getBtnGuardar().addActionListener(e -> guardar());
            cuestionarioView.getBtnTerminar().addActionListener(e -> finalizar());
        }

        private void configurarEventosRecuperar() {
            recuperarView.getBtnEnviar().addActionListener(e -> comprobarTodasRespuestas());
            recuperarView.getBtnTerminar().addActionListener(e -> finalizarRecuperar());
        }

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
                recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.recuperada") + contraseniaUsuario);
            } else {
                recuperarView.mostrarMensaje(mi.get("cuestionario.recuperar.incorrecta"));
            }
        }

        private void finalizarRecuperar() {
            recuperarView.dispose();
        }

        private void preguntasCuestionario() {
            int index = cuestionarioView.getCbxPreguntas().getSelectedIndex();
            if (index >= 0) {
                Respuesta r = preguntasAleatorias.get(index);
                cuestionarioView.getLblPregunta().setText(r.getEnunciado());

                Respuesta respondido = cuestionario.buscarRespuestaPorId(r.getId());
                if (respondido != null) {
                    cuestionarioView.getTxtRespuesta().setText(respondido.getRespuesta());
                } else {
                    cuestionarioView.getTxtRespuesta().setText("");
                }
            }
        }

        private void guardar() {
            int index = cuestionarioView.getCbxPreguntas().getSelectedIndex();
            if (index < 0) return;

            String texto = cuestionarioView.getTxtRespuesta().getText().trim();
            if (texto.isEmpty()) {
                cuestionarioView.mostrarMensaje(mi.get("cuestionario.guardar.vacia"));
                return;
            }

            Respuesta seleccionada = preguntasAleatorias.get(index);

            Respuesta yaRespondida = cuestionario.buscarRespuestaPorId(seleccionada.getId());
            if (yaRespondida != null) {
                cuestionarioView.mostrarMensaje(mi.get("cuestionario.guardar.yaRespondida")); // Debes tener esta clave en tu archivo de mensajes
                return;
            }

            seleccionada.setRespuesta(texto);
            cuestionario.agregarRespuesta(seleccionada);
            cuestionarioView.mostrarMensaje(mi.get("cuestionario.guardar.ok"));
        }

        private void finalizar() {
            if (cuestionario.getRespuestas().size() < 3) {
                cuestionarioView.mostrarMensaje(mi.get("cuestionario.finalizar.minimo"));
                return;
            }

            cuestionarioDAO.guardar(cuestionario);
            cuestionarioView.mostrarMensaje(mi.get("cuestionario.finalizar.ok"));
            cuestionarioView.dispose();
        }

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
    }
