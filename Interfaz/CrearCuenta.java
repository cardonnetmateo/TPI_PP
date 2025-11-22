package Interfaz;
import javax.swing.*;

import Enum.Mensajes;
import Modelos.UsuarioInterno;

public class CrearCuenta extends JFrame {
    private JButton btnCrear;
    private JComboBox<Modelos.UsuarioInterno> cmbUsuarios;
    private JTextField txtPatente;
    private JTextField txtModelo;

    public CrearCuenta() {
        setTitle(Mensajes.CREAR_CUENTA);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel titulo = new JLabel("Formulario de Creación de Cuenta");
        add(titulo);
        titulo.setBounds(100, 20, 250, 30);

        JLabel lblUsuario = new JLabel(Mensajes.USUARIO);
        add(lblUsuario);
        lblUsuario.setBounds(50, 70, 100, 25);

        cmbUsuarios = new JComboBox<>();
        add(cmbUsuarios);
        cmbUsuarios.setBounds(150, 70, 200, 25);

        JLabel lblPatente = new JLabel(Mensajes.PATENTE_VEHICULO);
        add(lblPatente);
        lblPatente.setBounds(50, 110, 100, 25);

        txtPatente = new JTextField();
        add(txtPatente);
        txtPatente.setBounds(150, 110, 200, 25);

        JLabel lblModelo = new JLabel(Mensajes.MODELO_VEHICULO);
        add(lblModelo);
        lblModelo.setBounds(50, 150, 100, 25);

        txtModelo = new JTextField();
        add(txtModelo);
        txtModelo.setBounds(150, 150, 200, 25);

        btnCrear = new JButton(Mensajes.CREAR_CUENTA);
        add(btnCrear);
        btnCrear.setBounds(150, 200, 120, 30);
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public JComboBox<Modelos.UsuarioInterno> getCmbUsuarios() {
        return cmbUsuarios;
    }

    public JTextField getTxtPatente() {
        return txtPatente;
    }

    public JTextField getTxtModelo() {
        return txtModelo;
    }
}
