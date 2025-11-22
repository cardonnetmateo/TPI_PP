package Interfaz;
import javax.swing.*;

import Enum.Mensajes;

public class MenuEstacionamiento extends JFrame{
    private JButton btnIngresarVehiculo;
    private JButton btnRetirarVehiculo;

    public MenuEstacionamiento(){
        setTitle("Menu Estacionamiento");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel(Mensajes.SELECCIONAR_OPCION, SwingConstants.CENTER);
        titulo.setBounds(100, 20, 200, 30);
        add(titulo);

        btnIngresarVehiculo = new JButton(Mensajes.INGRESAR_VEHICULO);
        btnIngresarVehiculo.setBounds(125, 70, 150, 30);
        add(btnIngresarVehiculo);

        btnRetirarVehiculo = new JButton(Mensajes.RETIRAR_VEHICULO);
        btnRetirarVehiculo.setBounds(125, 110, 150, 30);
        add(btnRetirarVehiculo);
    }

    public JButton getBtnIngresarVehiculo() {
        return btnIngresarVehiculo;
    }

    public JButton getBtnRetirarVehiculo() {
        return btnRetirarVehiculo;
    }
}
