package edu.ec.ups.dao.impl.texto;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.modelo.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Implementación de CarritoDAO que utiliza un archivo de texto plano
 * para almacenar los datos de carritos y sus productos.
 *
 * Cada carrito y sus ítems se guardan como líneas de texto utilizando formatos:
 * - "CAR:" para los datos del carrito (código;fecha;usuario)
 * - "ITEM:" para cada ítem del carrito (códigoProducto;nombre;precio;cantidad)
 *
 * Usa `SimpleDateFormat` para guardar/leer fechas y garantiza unicidad con un código incremental.
 *
 */
public class CarritoDAOArchivoTexto implements CarritoDAO {
    /** Ruta del archivo de texto donde se almacenan los carritos */
    private String rutaArchivo;
    /** Formato para guardar y leer la fecha del carrito */
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    /** Último código de carrito registrado para asignar códigos únicos */
    private int ultimoCodigo = 0;
    /**
     * Constructor que inicializa el DAO con la ruta del archivo.
     * Si el archivo no existe, lo crea vacío y carga el último código disponible.
     *
     * @param rutaArchivo Ruta al archivo de texto donde se guardan los carritos
     */
    public CarritoDAOArchivoTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        File f = new File(rutaArchivo);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarUltimoCodigo();
    }
    /**
     * Carga los carritos almacenados desde el archivo de texto.
     * Cada carrito puede tener múltiples líneas asociadas: una principal y varias de ítems.
     *
     * @return Lista de objetos Carrito cargados del archivo
     */
    public List<Carrito> cargarCarritos() {
        List<Carrito> carritos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            Carrito carrito = null;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("CAR:")) {
                    String[] partes = linea.substring(4).split(";");
                    if (partes.length == 3) {
                        carrito = new Carrito();
                        int codigo = Integer.parseInt(partes[0]);
                        carrito.setCodigo(codigo);
                        if (codigo > ultimoCodigo) ultimoCodigo = codigo;
                        try {
                            carrito.setFechaCreacion(new GregorianCalendar());
                            Date fecha = formatoFecha.parse(partes[1]);
                            carrito.getFechaCreacion().setTime(fecha);
                        } catch (Exception e) {
                            carrito.setFechaCreacion(new GregorianCalendar());
                        }
                        Usuario usuario = new Usuario();
                        usuario.setUsername(partes[2]);
                        carrito.setUsuario(usuario);
                        carritos.add(carrito);
                    }
                } else if (linea.startsWith("ITEM:") && carrito != null) {
                    String[] partes = linea.substring(5).split(";");
                    if (partes.length == 4) {
                        try {
                            int codigoProducto = Integer.parseInt(partes[0]);
                            String nombre = partes[1];
                            double precio = Double.parseDouble(partes[2]);
                            int cantidad = Integer.parseInt(partes[3]);

                            Producto producto = new Producto(codigoProducto, nombre, precio);
                            carrito.agregarProducto(producto, cantidad);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parseando item: " + linea);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carritos;
    }
    /**
     * Guarda una lista de carritos en el archivo de texto, sobrescribiendo el contenido previo.
     * Cada carrito se guarda con su información y sus ítems.
     *
     * @param carritos Lista de carritos a guardar
     */
    public void guardarCarritos(List<Carrito> carritos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            for (Carrito c : carritos) {
                String fechaStr = formatoFecha.format(c.getFechaCreacion().getTime());
                bw.write("CAR:" + c.getCodigo() + ";" + fechaStr + ";" + c.getUsuario().getUsername());
                bw.newLine();
                for (ItemCarrito item : c.obtenerItems()) {
                    Producto p = item.getProducto();
                    bw.write("ITEM:" + p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + item.getCantidad());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Carga el último código utilizado a partir del archivo de texto
     * para garantizar que los nuevos carritos tengan un código único.
     */
    private void cargarUltimoCodigo() {
        List<Carrito> carritos = cargarCarritos();
        for (Carrito c : carritos) {
            if (c.getCodigo() > ultimoCodigo) {
                ultimoCodigo = c.getCodigo();
            }
        }
    }
    /**
     * Crea un nuevo carrito, asignando un código único e incrementando el contador.
     *
     * @param carrito Objeto carrito a registrar
     */
    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();

        // Obtener el mayor código actual para asignar uno nuevo único
        ultimoCodigo = carritos.stream()
                .mapToInt(Carrito::getCodigo)
                .max()
                .orElse(0);

        carrito.setCodigo(++ultimoCodigo); // Asignar nuevo código

        carritos.add(carrito);
        guardarCarritos(carritos);
    }
    /**
     * Busca un carrito por su código.
     *
     * @param codigo Código del carrito
     * @return Carrito encontrado o null si no existe
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }
    /**
     * Devuelve una lista de carritos pertenecientes a un usuario específico.
     *
     * @param usuario Usuario del que se quiere obtener sus carritos
     * @return Lista de carritos encontrados
     */
    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> carritos = cargarCarritos();
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito c : carritos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(c);
            }
        }
        return resultado;
    }
    /**
     * Actualiza los datos de un carrito existente, reemplazándolo por uno nuevo.
     *
     * @param carrito Carrito actualizado
     */
    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarCarritos(carritos);
    }
    /**
     * Elimina un carrito por su código.
     *
     * @param codigo Código del carrito a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }
    /**
     * Lista todos los carritos disponibles en el archivo.
     *
     * @return Lista de todos los carritos
     */
    @Override
    public List<Carrito> listarTodos() {
        return cargarCarritos();
    }
}