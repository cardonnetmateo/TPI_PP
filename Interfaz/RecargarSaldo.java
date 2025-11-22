package Interfaz;

import javax.swing.*;
import Modelos.Cuenta;

public class RecargarSaldo extends JFrame {
    private JComboBox<Cuenta> cmbCuentas;
    private JTextField txtCantidad;
    private JButton btnRecargarCuentaCorriente;
    private JButton btnRecargarAbonoMensual;

    public RecargarSaldo() {
        setTitle("Recargar Saldo");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Recargar Saldo de la Cuenta");
        add(titulo);
        titulo.setBounds(100, 20, 220, 30);

        JLabel lblCuenta = new JLabel("Seleccionar Cuenta:");
        add(lblCuenta);
        lblCuenta.setBounds(50, 70, 150, 25);

        cmbCuentas = new JComboBox<>();
        add(cmbCuentas);
        cmbCuentas.setBounds(200, 70, 160, 25);

        JLabel lblCantidad = new JLabel("Cantidad a recargar:");
        add(lblCantidad);
        lblCantidad.setBounds(50, 120, 150, 25);

        txtCantidad = new JTextField();
        add(txtCantidad);
        txtCantidad.setBounds(200, 120, 160, 25);

        btnRecargarCuentaCorriente = new JButton("Recargar Cuenta Corriente");
        add(btnRecargarCuentaCorriente);
        btnRecargarCuentaCorriente.setBounds(60, 170, 160, 30);

        btnRecargarAbonoMensual = new JButton("Recargar Abono Mensual");
        add(btnRecargarAbonoMensual);
        btnRecargarAbonoMensual.setBounds(220, 170, 160, 30);
    }

    public JComboBox<Cuenta> getCmbCuentas() {
        return cmbCuentas;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JButton getBtnRecargarCuentaCorriente() {
        return btnRecargarCuentaCorriente;
    }

    public JButton getBtnRecargarAbonoMensual() {
        return btnRecargarAbonoMensual;
    }

}
