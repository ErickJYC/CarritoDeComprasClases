package edu.ec.ups.modelo;

import javax.swing.*;
import java.io.Serializable;

/**
 * Clase que representa un usuario dentro del sistema.
 * Contiene información personal, de autenticación y su rol asignado.
 */
public class Usuario implements Serializable {

    // Atributos principales del usuario
    private String username;
    private String contrasenia;
    private Rol rol;
    private String nombreCompleto;
    private String fechaNacimiento; // Formato recomendado: dd/mm/yyyy
    private String celular;
    private String correo;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío necesario para frameworks o instancias manuales.
     */
    public Usuario() {
    }

    /**
     * Constructor básico para autenticación y asignación de rol.
     */
    public Usuario(String username, String contrasenia, Rol rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    /**
     * Constructor completo con toda la información personal.
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

    public void setContrasenia(String contrasenia) {
        try {
            if (contrasenia.contains(" ")) {
                throw new IllegalArgumentException("La contraseña no debe contener espacios.");
            }
            if (contrasenia.length() < 6) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
            }
            if (!contrasenia.matches(".*[A-Z].*")) {
                throw new IllegalArgumentException("la contraseña debe contener una letra mayúscula.");
            }
            if (!contrasenia.matches(".*[a-z].*")) {
                throw new IllegalArgumentException("la contraseña debe contener una letra minúscula.");
            }
            if (!contrasenia.matches(".*[@_-].*")) {
                throw new IllegalArgumentException("La contraseña debe incluir uno de estos caracteres especiales: @, _ o -");
            }

            this.contrasenia = contrasenia;
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error de seguridad", JOptionPane.ERROR_MESSAGE);
            this.contrasenia = null; // IMPORTANTE: si es inválida, queda null
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
