package Modelos;
import java.util.ArrayList;
import java.util.List;

import Enum.Mensajes;
import lombok.Data;
import java.time.LocalDate;


@Data
public class Estacionamiento {
    private String nombre;
    private List<Plaza> plazas;


    public Estacionamiento(String nombre, int cantidadPlazas) {
        this.nombre = nombre;
        this.plazas = new ArrayList<>();
        for (int i = 1; i <= cantidadPlazas; i++) {
            plazas.add(new Plaza(i, LocalDate.now()));
        }
    }    

    public void agregarPlaza(Plaza nuevaPlaza) {
        this.plazas.add(nuevaPlaza);
    }

    public int getCapacidadTotal() {
        return plazas.size();
    }

    public int cantidadPlazasDisponibles() {
        return (int) plazas.stream()
                        .filter(p -> !p.isOcupada())
                        .count();
    }

    public List<Plaza> plazasDisponibles() {
        return plazas.stream()
                    .filter(p -> !p.isOcupada())
                    .toList();
    }

    public void mostrarPlazasDisponibles() {
    System.out.println("Cantidad de plazas disponibles: " + cantidadPlazasDisponibles());
    for (Plaza p : plazasDisponibles()) {
        System.out.println(Mensajes.NUMERO_PLAZA + p.getNumeroPlaza());
    }
}
}
