package Controller;

import Modelos.Vehiculo;
import Modelos.Cuenta;
import javax.swing.JOptionPane;

public class ControladorEstacionamiento {

    // Hechos como métodos que solo describen condiciones verdaderas
    private boolean tieneAbonoVigente(Cuenta c) {
        return c.esAbonoMensual() && c.tieneAbonoVigente();
    }

    private boolean tieneSaldo(Cuenta c) {
        return c.esCuentaCorriente() && c.getSaldo() > 0;
    }

    // Regla lógica pura: puede ingresar si cumple alguna de las condiciones
    public boolean puedePasar(Vehiculo v) {
        Cuenta c = v.getCuenta();

        // "Consulta" lógica: no hacemos pasos, solo verificamos hechos
        return tieneAbonoVigente(c) || tieneSaldo(c);
    }

    public void intentarIngreso(Vehiculo v) {
        String mensaje = puedePasar(v) ? 
            "Ingreso permitido para el vehículo " + v.getPatente() :
            "Ingreso denegado: saldo insuficiente o abono no vigente.";

        JOptionPane.showMessageDialog(null, mensaje);
    }
}