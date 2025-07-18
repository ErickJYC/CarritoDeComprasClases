package edu.ec.ups.modelo;

import javax.swing.*;
import java.io.Serializable;

/**
 * Clase que representa un usuario dentro del sistema.
 * Contiene información personal, credenciales de acceso y su rol asignado.
 */
public class Usuario implements Serializable {

    /**
     * Nombre de usuario, generalmente cédula o identificador único (10 dígitos).
     */
    private String username;
    /**
     * Contraseña del usuario con validación de seguridad.
     */
    private String contrasenia;
    /**
     * Rol del usuario (ADMINISTRADOR o USUARIO).
     */
    private Rol rol;
    /**
     * Nombre completo del usuario.
     */
    private String nombreCompleto;
    /**
     * Fecha de nacimiento del usuario (formato recomendado: dd/mm/yyyy).
     */
    private String fechaNacimiento;
    /**
     * Número de celular del usuario (10 dígitos).
     */
    private String celular;
    /**
     * Dirección de correo electrónico del usuario.
     */
    private String correo;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío necesario para frameworks o instancias manuales.
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros básicos para autenticación y asignación de rol.
     *
     * @param username    Identificador único del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param rol         Rol asignado (ADMINISTRADOR o USUARIO).
     */
    public Usuario(String username, String contrasenia, Rol rol) {
        setUsername(username);
        setContrasenia(contrasenia);
        this.rol = rol;
    }

    /**
     * Constructor completo con todos los atributos personales.
     *
     * @param username        Identificador único del usuario.
     * @param contrasenia     Contraseña con validaciones.
     * @param rol             Rol asignado.
     * @param nombreCompleto  Nombre completo.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param celular         Número celular (10 dígitos).
     * @param correo          Correo electrónico.
     */
    public Usuario(String username, String contrasenia, Rol rol, String nombreCompleto,
                   String fechaNacimiento, String celular, String correo) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
        this.correo = correo;
    }

    // ===================== Getters y Setters =====================

    public String getUsername() {
        return username;
    }
    /**
     * Asigna el nombre de usuario con validación de 10 dígitos numéricos.
     *
     * @param username Username a asignar.
     */
    public void setUsername(String username) {
        try {
            if (!username.matches("\\d{10}")) {
                throw new IllegalArgumentException("El usuario debe contener exactamente 10 números.");
            }
            this.username = username;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de cédula", JOptionPane.ERROR_MESSAGE);
            this.username = null; // ⚠️ Esto es clave para evitar que se cree el usuario
        }
    }


    public String getContrasenia() {
        return contrasenia;
    }
    /**
     * Asigna una contraseña al usuario con validaciones de seguridad.
     * Debe tener al menos 6 caracteres, una mayúscula, una minúscula y un carácter especial (@, _ o -).
     *
     * @param contrasenia Contraseña a asignar.
     */
    public void setContrasenia(String contrasenia) {
        try {
            if (contrasenia == null || contrasenia.trim().isEmpty()) {
                throw new IllegalArgumentException("La contraseña no puede estar vacía.");
            }

            contrasenia = contrasenia.trim(); // elimina espacios invisibles

            if (contrasenia.contains(" ")) {
                throw new IllegalArgumentException("La contraseña no debe contener espacios.");
            }
            if (contrasenia.length() < 6) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
            }
            if (!contrasenia.matches(".*[A-Z].*")) {
                throw new IllegalArgumentException("La contraseña debe contener una letra mayúscula.");
            }
            if (!contrasenia.matches(".*[a-z].*")) {
                throw new IllegalArgumentException("La contraseña debe contener una letra minúscula.");
            }
            if (!contrasenia.matches(".*[@_-].*")) {
                throw new IllegalArgumentException("La contraseña debe incluir uno de estos caracteres especiales: @, _ o -");
            }

            this.contrasenia = contrasenia;
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error de seguridad", JOptionPane.ERROR_MESSAGE);
            this.contrasenia = null;
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error general al validar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            this.contrasenia = null;
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCelular() {
        return celular;
    }
    /**
     * Asigna el número de celular del usuario con validación de 10 dígitos.
     *
     * @param celular Número de celular.
     */
    public void setCelular(String celular) {
        try {
            if (!celular.matches("\\d{10}")) {
                throw new IllegalArgumentException("El número de celular debe tener exactamente 10 dígitos.");
            }
            this.celular = celular;
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error de celular", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error general al validar el celular.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getCorreo() {
        return correo;
    }
    /**
     * Asigna el correo electrónico del usuario validando que tenga '@' y '.'.
     *
     * @param correo Dirección de correo.
     */
    public void setCorreo(String correo) {
        try {
            if (!correo.contains("@") || !correo.contains(".")) {
                throw new IllegalArgumentException("Correo inválido. Debe contener '@' y '.'");
            }
            this.correo = correo;
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error de correo", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error general al asignar el correo electrónico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Representación en texto del objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", celular='" + celular + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
