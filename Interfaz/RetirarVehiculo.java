package Interfaz;
import javax.swing.*;

import Enum.Mensajes;
public class RetirarVehiculo extends JFrame {
    private JButton btnRetirar;
    private JTextField txtPatente;

    public RetirarVehiculo() {
        setTitle(Mensajes.RETIRAR_VEHICULO);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel titulo = new JLabel("Formulario de Retiro de Vehículo");
        add(titulo);
        titulo.setBounds(80, 20, 250, 30);

        JLabel lblPatente = new JLabel(Mensajes.PATENTE_VEHICULO);
        add(lblPatente);
        lblPatente.setBounds(50, 70, 150, 25);

        txtPatente = new JTextField();
        add(txtPatente);
        txtPatente.setBounds(200, 70, 150, 25);

        btnRetirar = new JButton(Mensajes.RETIRAR_VEHICULO);
        add(btnRetirar);
        btnRetirar.setBounds(130, 120, 150, 30);
    }
    public JButton getBtnRetirar() {
        return btnRetirar;
    }

    public JTextField getTxtPatente() {
        return txtPatente;
    }
}
