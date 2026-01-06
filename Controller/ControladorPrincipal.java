package Controller;

import Interfaz.*;
import javax.swing.SwingUtilities;

public class ControladorPrincipal {

    private Inicio vista;

    public void mostrar() {
        SwingUtilities.invokeLater(() -> {
            vista = new Inicio();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);

            configurarEventos();
        });
    }

    private void configurarEventos() {

        vista.getBtnAdministrarCuenta().addActionListener(e -> {
            vista.dispose();
            new ControladorCuenta().mostrarMenuPrincipal();
        });

        vista.getBtnAdministrarEstacionamiento().addActionListener(e -> {
            vista.dispose();
            new ControladorCuenta().mostrarAdministrarEstacionamiento();
        });
    }

    public static void main(String[] args) {
        new ControladorPrincipal().mostrar();
    }
}
