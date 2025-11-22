package Modelos;

import lombok.Data;

@Data
public class Vehiculo {
    private String patente;
    private String modelo;
    private Cuenta cuenta;
    private Plaza plaza;

    public Vehiculo(String patente, String modelo) {
        this.patente = patente;
        this.modelo = modelo;
    }

    public Vehiculo(String patente, String modelo, Cuenta cuenta) {
        this.patente = patente;
        this.modelo = modelo;
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return patente + " - " + modelo + (plaza != null ? " (Plaza " + plaza.getNumeroPlaza() + ")" : "");
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }
}
