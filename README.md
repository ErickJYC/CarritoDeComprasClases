# 🛒 Ñemu - Carrito de Compras en Línea

Aplicación de escritorio desarrollada en Java con Swing, que permite gestionar usuarios, productos y carritos de compras. Diseñada bajo los principios SOLID y los patrones de diseño MVC y DAO, con soporte multilenguaje y una interfaz gráfica moderna tipo MDI.

---

👤 Autor- Erick Yunga
- Carrera de Computación - Universidad Politécnica Salesiana
- GitHub: https://github.com/ErickJYC
- Repositorio: https://github.com/ErickJYC/CarritoDeComprasClases

📽️ Video de Demostración
- Video: https://youtu.be/WPIz5ml5iUc

---

##  Características Principales

- ✅ Autenticación de usuarios con recuperación de contraseña mediante preguntas de seguridad
- 🛍️ CRUD completo de productos y usuarios (rol administrador)
- 🛒 Gestión de carritos de compras (crear, modificar, eliminar, listar)
- 🌐 Soporte multilenguaje: Español 🇪🇸, Inglés 🇺🇸, Francés 🇫🇷
- 🎨 Interfaz gráfica avanzada con Swing (JDesktopPane, JInternalFrame, JTable, JComboBox)
- 🌍 Formateo regional de fechas y monedas con `DateFormat` y `NumberFormat`
- 🧩 Arquitectura modular con MVC + DAO + principios SOLID

---

##  Arquitectura

- **Modelo-Vista-Controlador (MVC)**: Separación clara entre lógica de negocio, presentación y datos.
- **DAO Pattern**: Acceso a datos desacoplado mediante interfaces (`UsuarioDAO`, `ProductoDAO`, etc.).
- **Principios SOLID**:
    - SRP: Cada clase tiene una única responsabilidad.
    - OCP: Fácil de extender sin modificar código existente.
    - DIP: Controladores dependen de interfaces, no de implementaciones concretas.

---

## 🌍 Internacionalización

La clase `MensajeInternacionalizacionHandler` gestiona los textos multilenguaje usando `ResourceBundle`. Los archivos `.properties` disponibles son:

- `mensajes_es_EC.properties`
- `mensajes_en_US.properties`
- `mensajes_fr_FR.properties`

Desde el menú principal, el usuario puede cambiar el idioma dinámicamente. Todas las vistas actualizan sus textos automáticamente.

---

## 🎨 Interfaz Gráfica

La clase `MiDesktopPane` redefine `paintComponent` para ofrecer:

- Fondo degradado con `GradientPaint`
- Curvas decorativas con `CubicCurve2D`
- Título central “Ñemu” con sombra
- Slogan: “Compra fácil. Vive mejor.”

---

## ✅ Resultados Obtenidos- Aplicación funcional con autenticación y recuperación de contraseña
- CRUD completo de usuarios y productos
- Gestión de carritos por usuario
- Interfaz MDI moderna y multilenguaje
- Aplicación de principios SOLID y patrones de diseño

---

## 💡 Recomendaciones Futuras- Implementar persistencia con archivos o base de datos
- Agregar roles con permisos más granulares
- Mejorar validaciones de formularios





