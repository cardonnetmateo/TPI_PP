package Interfaz;
import javax.swing.*;

import Enum.Mensajes;
import Modelos.Plaza;
public class IngresarVehiculo extends JFrame{
    private JButton btnIngresar;

    public IngresarVehiculo(){
        setTitle(Mensajes.INGRESAR_VEHICULO);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel(Mensajes.INGRESAR_VEHICULO);
        add(titulo);
        titulo.setBounds(80, 20, 250, 30);

        JLabel lblPatente = new JLabel(Mensajes.PATENTE_VEHICULO);
        add(lblPatente);
        lblPatente.setBounds(50, 70, 150, 25);

        JTextField txtPatente = new JTextField();
        add(txtPatente);
        txtPatente.setBounds(200, 70, 100, 25);

        JLabel lblPlaza = new JLabel(Mensajes.NUMERO_PLAZA);
        add(lblPlaza);
        lblPlaza.setBounds(50, 110, 150, 25);

        JComboBox<Plaza> cmbPlazas = new JComboBox<>();
        add(cmbPlazas);
        cmbPlazas.setBounds(200, 110, 100, 25);

        btnIngresar = new JButton(Mensajes.INGRESAR_VEHICULO);
        add(btnIngresar);
        btnIngresar.setBounds(130, 160, 150, 30);
    }
    
    public JButton getBtnIngresar() {
        return btnIngresar;
    }
}
