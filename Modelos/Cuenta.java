package Modelos;
import java.time.LocalDate;
import Enum.TipoCuenta;
import Modelos.Facturas.AbonoMensual;
import Modelos.Facturas.RecargaSaldo;
import Enum.Mensajes;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class Cuenta {
    private Persona usuario;
    private Integer saldo;
    private TipoCuenta tipo;
    private List<RecargaSaldo> recargos = new ArrayList<>();
    private List<AbonoMensual> abonos = new ArrayList<>();
    

    public Cuenta(Persona usuario) {
        this.usuario = usuario;
        this.saldo = 0;
        this.tipo = TipoCuenta.CUENTA_CORRIENTE;
    }
    
    @Override
    public String toString() {
        return usuario.getNombre();
    }

    //ABONO MENSUAL
    public void registrarCobroAbono(TarifaAbonoMensual tarifa) {
        if (esAbonoMensual()){
            AbonoMensual nuevoAbono = new AbonoMensual(tarifa, LocalDate.now(), LocalDate.now().plusDays(30));
            this.abonos.add(nuevoAbono);
        }
    }

    public boolean esAbonoMensual() {
        return this.tipo == TipoCuenta.ABONO_MENSUAL;
    }

    public AbonoMensual obtenerUltimoAbono() {
        if (this.abonos.isEmpty()) {
            System.err.println(Mensajes.NO_EXISTEN_ABONOS);
            return null;
        }
        return this.abonos.get(this.abonos.size() - 1);
    }

    public boolean tieneAbonoVigente() {
        if (this.abonos == null) {
            return false;
        }
        AbonoMensual ultimo = obtenerUltimoAbono();
        LocalDate hoy = LocalDate.now();
        return (hoy.isEqual(ultimo.getFechaDesde()) || hoy.isAfter(ultimo.getFechaDesde())) &&
               (hoy.isEqual(ultimo.getFechaHasta()) || hoy.isBefore(ultimo.getFechaHasta()));
    }

    //CUENTA CORRIENTE

    public void recargarSaldoCuentaCorriente(Integer cantidad) {
        if (esCuentaCorriente()) {
            RecargaSaldo nuevaRecarga = new RecargaSaldo(cantidad, LocalDate.now());
            this.recargos.add(nuevaRecarga);
            this.saldo += cantidad;
        }
    }

    public boolean esCuentaCorriente() {
        return this.tipo == TipoCuenta.CUENTA_CORRIENTE;
    }
    
    //Settear Tipo Cuenta

    public void setAbonoMensual() {
        this.tipo = TipoCuenta.ABONO_MENSUAL;
        this.saldo = 0;
    }

    public void setCuentaCorriente() {
        this.tipo = TipoCuenta.CUENTA_CORRIENTE;
        this.saldo = 0;
    }
}