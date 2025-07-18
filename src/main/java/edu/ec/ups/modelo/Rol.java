package edu.ec.ups.modelo;

/**
 * Enum que representa los roles posibles que puede tener un usuario en el sistema.
 * Este enum se utiliza para diferenciar los niveles de acceso y permisos de los usuarios.
 */
public enum Rol {

    /**
     * Rol con privilegios administrativos.
     * Puede gestionar usuarios, productos, carritos y realizar configuraciones globales del sistema.
     */
    ADMINISTRADOR,

    /**
     * Rol estándar para los usuarios comunes.
     * Puede navegar por el sistema, agregar productos al carrito y realizar compras.
     */
    USUARIO
}
