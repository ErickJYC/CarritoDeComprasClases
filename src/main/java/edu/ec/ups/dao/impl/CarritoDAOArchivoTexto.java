package edu.ec.ups.dao.impl;

import edu.ec.ups.dao.CarritoDAO;
import edu.ec.ups.modelo.Carrito;
import edu.ec.ups.modelo.ItemCarrito;
import edu.ec.ups.modelo.Producto;
import edu.ec.ups.modelo.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarritoDAOArchivoTexto implements CarritoDAO {

    private String rutaArchivo;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

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
    }

    // Carga todos los carritos desde el archivo texto
    public List<Carrito> cargarCarritos() {
        List<Carrito> carritos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            Carrito carrito = null;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("CAR:")) {
                    // Nueva línea de carrito: CAR:codigo;fecha;username
                    String[] partes = linea.substring(4).split(";");
                    if (partes.length == 3) {
                        carrito = new Carrito();
                        carrito.setCodigo(Integer.parseInt(partes[0]));
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
                    // Línea item: ITEM:codigoProducto;nombre;precio;cantidad
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

    // Guarda todos los carritos en archivo texto
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

    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = cargarCarritos();
        carritos.add(carrito);
        guardarCarritos(carritos);
    }

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

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = cargarCarritos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }

    @Override
    public List<Carrito> listarTodos() {
        return cargarCarritos();
    }
}
