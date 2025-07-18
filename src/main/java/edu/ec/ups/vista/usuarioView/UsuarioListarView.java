package edu.ec.ups.vista.usuarioView;

import edu.ec.ups.util.MensajeInternacionalizacionHandler;
import edu.ec.ups.vista.loginView.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class UsuarioListarView  extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField TxtUsuario;
    private JButton BtnBuscar;
    private JButton BtnListar;
    private JTable tblUsuario;
    private JLabel lblUser;
    private JLabel lblListar;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mi;

    public UsuarioListarView( MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
        setContentPane(panelPrincipal);
        setTitle("Listar Usuarios");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        URL buscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if(buscar != null){
            ImageIcon icono = new ImageIcon(buscar);
            BtnBuscar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL listar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if(listar != null){
            ImageIcon icono = new ImageIcon(listar);
            BtnListar.setIcon(icono);
        }else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

        modelo = new DefaultTableModel(new Object[]{"Nombre", "Usuario", "Contraseña", "Correo", "Celular", "Fcha de Nacimiento", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblUsuario.setModel(modelo);
        cambiarIdioma();

    }
    public void cambiarIdioma() {
        setTitle(mi.get("usuario.listar.titulo"));
        lblListar.setText(mi.get("usuario.listar.tituloTabla"));
        lblUser.setText(mi.get("usuario.listar.usuario"));
        BtnBuscar.setText(mi.get("usuario.listar.boton.buscar"));
        BtnListar.setText(mi.get("usuario.listar.boton.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mi.get("usuario.listar.columna.nombre"),
                mi.get("usuario.listar.columna.usuario"),
                mi.get("usuario.listar.columna.contrasena"),
                mi.get("usuario.listar.columna.correo"),
                mi.get("usuario.listar.columna.celular"),
                mi.get("usuario.listar.columna.fechaNacimiento"),
                mi.get("usuario.listar.columna.rol")
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtUsuario() {
        return TxtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        TxtUsuario = txtUsuario;
    }

    public JButton getBtnBuscar() {
        return BtnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        BtnBuscar = btnBuscar;
    }

    public JButton getBtnListar() {
        return BtnListar;
    }

    public void setBtnListar(JButton btnListar) {
        BtnListar = btnListar;
    }

    public JTable getTblUsuario() {
        return tblUsuario;
    }

    public void setTblUsuario(JTable tblUsuario) {
        this.tblUsuario = tblUsuario;
    }

    public JLabel getLblUser() {
        return lblUser;
    }

    public void setLblUser(JLabel lblUser) {
        this.lblUser = lblUser;
    }

    public JLabel getLblListar() {
        return lblListar;
    }

    public void setLblListar(JLabel lblListar) {
        this.lblListar = lblListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public MensajeInternacionalizacionHandler getMi() {
        return mi;
    }

    public void setMi(MensajeInternacionalizacionHandler mi) {
        this.mi = mi;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
