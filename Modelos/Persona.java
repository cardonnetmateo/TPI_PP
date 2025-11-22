package Modelos;
import Enum.TipoDocumento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Persona {
    private String nombre;
    private int edad;
    private TipoDocumento tipo;
    private int nroDocumento;
    private String mail;
    private Integer telefono;

    public Persona(String nombre, int edad, TipoDocumento tipo, int nroDocumento, String mail, Integer telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.nroDocumento = nroDocumento;
        this.mail = mail;
        this.telefono = telefono;
    }
}
