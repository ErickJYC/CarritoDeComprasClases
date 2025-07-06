package edu.ec.ups.modelo;

/**
 * Enum que representa los roles posibles que puede tener un usuario en el sistema.
 */
public enum Rol {

    /**
     * Rol con privilegios administrativos.
     * Puede gestionar usuarios, productos y otras funciones avanzadas.
     */
    ADMINISTRADOR,

    /**
     * Rol estándar para los usuarios comunes.
     * Tiene acceso limitado a funcionalidades básicas del sistema.
     */
    USUARIO
}
