# 🛒 Sistema de Gestión de Compras - Ñemu

----
## Nombre: Erick Yunga

Este proyecto es una aplicación de escritorio en Java, desarrollada con arquitectura MVC, que permite la gestión de usuarios, productos, carritos de compras y preguntas de seguridad.

## 🧩 Descripción General del Sistema

El sistema permite:

- Registrar, modificar y eliminar **usuarios**, **productos**, **carritos** y **preguntas**.
- Iniciar sesión y realizar autenticación con preguntas de seguridad.
- Agregar productos al carrito.
- Recuperar contraseña usando preguntas de seguridad.
- Cambiar dinámicamente el **idioma de la interfaz**.
- Visualizar datos con formatos regionales (fecha y moneda).

Se aplican **principios de diseño SOLID**, estructura modular y patrón DAO para garantizar un código **mantenible, escalable y limpio**.

---

## 🎨 Interfaz Visual Personalizada

La aplicación utiliza componentes avanzados de Swing como `JInternalFrame`, `JTable` y `JComboBox`, y un escritorio visual (`MiDesktopPane`) personalizado para una experiencia atractiva:

### `MiDesktopPane` incluye:

- 🎨 **Fondo degradado** suave (de tonos claros a cálidos) con `GradientPaint`.
- 🌀 **Curvas decorativas** elegantes usando `CubicCurve2D`, distribuidas en capas con transparencia.
- 🧾 **Texto central**: Título **“Ñemu”** con sombra, y el eslogan: _"Compra fácil. Vive mejor."_.

Esto se logra sobrescribiendo el método `paintComponent()` para personalizar la apariencia de la interfaz.

---

## 🌍 Internacionalización (i18n)

La clase `MensajeInternacionalizacionHandler` permite cambiar de idioma dinámicamente en toda la aplicación:

- Los textos se obtienen desde archivos `.properties` (por ejemplo, `mensajes_es_EC.properties`, `mensajes_en_US.properties`, `mensajes_fr_FR.properties`).
- Se soportan tres idiomas:
    - 🇪🇸 Español
    - 🇺🇸 Inglés
    - 🇫🇷 Francés
- También se formatean fechas y monedas automáticamente usando `NumberFormat` y `DateFormat`, adaptándose a cada región.

Cada vista implementa un método `cambiarIdioma()` que actualiza los textos según el idioma seleccionado desde el menú principal.

---

## 🧠 Principios SOLID en el diseño

Se aplican los **principios SOLID** para mejorar la estructura del código:

### ✅ SRP – Responsabilidad Única
Cada clase tiene una única función:
- `UsuarioController`: maneja operaciones de usuario.
- `MensajeInternacionalizacionHandler`: gestiona los textos multilenguaje.

### ✅ OCP – Abierto/Cerrado
El sistema está abierto a extensiones sin modificar clases existentes.
- Ejemplo: Para agregar un nuevo idioma, solo se añade un archivo `.properties`.

### ✅ DIP – Inversión de Dependencias
La lógica del sistema depende de **interfaces** (como `UsuarioDAO`, `ProductoDAO`, `CarritoDAO`, etc.) y no de implementaciones directas.
- Esto permite reemplazar fácilmente una DAO en memoria por una conexión real a base de datos.

---

## 👨‍💻 Autor

> Proyecto desarrollado por **Erick Yunga**  
> Estudiante de Programación en Java – Ecuador 🇪🇨  
> Email: erick.yunga@example.com *(opcional)*  
> GitHub: [github.com/erickyunga](https://github.com/erickyunga) *(opcional)*
