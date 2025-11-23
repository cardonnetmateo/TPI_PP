package Modelos;
import Enum.Rol;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import Enum.TipoDocumento;
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class UsuarioInterno extends Persona {
    private Integer legajo;
    private Rol rol;

    public UsuarioInterno(String nombre, int edad, TipoDocumento tipo, int nroDocumento, String mail, Integer telefono, Integer legajo, Rol rol) {
        super(nombre, edad, tipo, nroDocumento, mail, telefono);
        this.legajo = legajo;
        this.rol = rol;
    }
}
