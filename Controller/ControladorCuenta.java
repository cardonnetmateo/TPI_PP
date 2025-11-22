package Controller;

import Interfaz.CrearCuenta;
import Interfaz.IngresarVehiculo;
import Interfaz.MenuEstacionamiento;
import Interfaz.RetirarVehiculo;
import Interfaz.MenuPrincipal;
import Interfaz.RecargarSaldo;
import Modelos.UsuarioInterno;
import Modelos.Cuenta;
import Modelos.Vehiculo;
import Modelos.Plaza;
import Enum.TipoDocumento;
import Enum.Mensajes;
import Enum.MensajesUI;
import Enum.Rol;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class ControladorCuenta {
    //CREAR CUENTA
    private MenuPrincipal vistaMenuPrincipal;
    private CrearCuenta vistaCrearCuenta;
    private RecargarSaldo vistaRecargarSaldo;
    //RECARGAR SALDO

    private final List<UsuarioInterno> usuarios = new ArrayList<>();
    private final List<Cuenta> cuentas = new ArrayList<>();
    private final List<Vehiculo> vehiculos = new ArrayList<>();
    private final List<Plaza> plazas = new ArrayList<>();

    //MENU PRINCIPAL
    public void mostrarMenuPrincipal(){
        vistaMenuPrincipal = new MenuPrincipal();
        vistaMenuPrincipal.setLocationRelativeTo(null);
        vistaMenuPrincipal.setVisible(true);
        configurarEventosMenuPrincipal();
    }

    private Vehiculo findVehiculoByPatente(String patente) {
        for (Vehiculo v : vehiculos) {
            if (v.getPatente() != null && v.getPatente().equalsIgnoreCase(patente)) {
                return v;
            }
        }
        return null;
    }

    private void cargarVehiculosEjemplo() {
        if (!vehiculos.isEmpty()) return; // ya cargados
        vehiculos.add(new Vehiculo("ABC123", "Model A"));
        vehiculos.add(new Vehiculo("DEF456", "Model B"));
        vehiculos.add(new Vehiculo("GHI789", "Model C"));
    }

    private void cargarPlazasEjemplo() {
        if (!plazas.isEmpty()) return;
        java.time.LocalDate hoy = java.time.LocalDate.now();
        plazas.add(new Plaza(1, hoy));
        plazas.add(new Plaza(2, hoy));
        plazas.add(new Plaza(3, hoy));
        plazas.add(new Plaza(4, hoy));
    }

    private void configurarEventosMenuPrincipal(){
        vistaMenuPrincipal.getBtnCrearCuenta().addActionListener(e -> {
            vistaMenuPrincipal.dispose();
            mostrarCrearCuenta();
        });

        vistaMenuPrincipal.getBtnRecargarSaldo().addActionListener(e -> {
            vistaMenuPrincipal.dispose();
            mostrarRecargarSaldo();
        });
    }

    public void mostrarAdministrarEstacionamiento() {
        MenuEstacionamiento vista = new MenuEstacionamiento();
        vista.setLocationRelativeTo(null);

        // Cargar vehículos de prueba y poblar combo
        cargarVehiculosEjemplo();
        vista.getCmbVehiculos().removeAllItems();
        for (Vehiculo v : vehiculos) {
            vista.getCmbVehiculos().addItem(v);
        }

        // Abrir formulario de Ingresar Vehículo
        vista.getBtnIngresarVehiculo().addActionListener(e -> {
            IngresarVehiculo ingresoView = new IngresarVehiculo();
            ingresoView.setLocationRelativeTo(null);

            // poblar combo de plazas disponibles
            cargarPlazasEjemplo();
            ingresoView.getCmbPlazas().removeAllItems();
            for (Plaza p : plazas) ingresoView.getCmbPlazas().addItem(p);

            ingresoView.getBtnIngresar().addActionListener(ev -> {
                String patente = ingresoView.getTxtPatente().getText().trim();
                if (patente.isEmpty()) {
                    JOptionPane.showMessageDialog(ingresoView, "Ingrese patente.");
                    return;
                }

                Plaza plazaSeleccionada = (Plaza) ingresoView.getCmbPlazas().getSelectedItem();
                if (plazaSeleccionada == null) {
                    JOptionPane.showMessageDialog(ingresoView, "Seleccione una plaza.");
                    return;
                }

                if (plazaSeleccionada.isOcupada()) {
                    JOptionPane.showMessageDialog(ingresoView, "La plaza seleccionada está ocupada.");
                    return;
                }

                // Crear vehículo, asignar plaza y marcarla ocupada
                Vehiculo nuevo = new Vehiculo(patente, "N/A");
                nuevo.setPlaza(plazaSeleccionada);
                plazaSeleccionada.setOcupada();

                vehiculos.add(nuevo);
                // refrescar combo en la vista principal de estacionamiento
                vista.getCmbVehiculos().removeAllItems();
                for (Vehiculo v : vehiculos) vista.getCmbVehiculos().addItem(v);

                JOptionPane.showMessageDialog(ingresoView, "Vehículo agregado en plaza " + plazaSeleccionada.getNumeroPlaza());
                ingresoView.dispose();
            });

            ingresoView.setVisible(true);
        });

        // Abrir formulario de Retirar Vehículo
        vista.getBtnRetirarVehiculo().addActionListener(e -> {
            RetirarVehiculo retirarView = new RetirarVehiculo();
            retirarView.setLocationRelativeTo(null);

            retirarView.getBtnRetirar().addActionListener(ev -> {
                String patente = retirarView.getTxtPatente().getText().trim();
                if (patente.isEmpty()) {
                    JOptionPane.showMessageDialog(retirarView, "Ingrese patente.");
                    return;
                }

                Vehiculo encontrada = findVehiculoByPatente(patente);
                if (encontrada == null) {
                    JOptionPane.showMessageDialog(retirarView, "Vehículo no encontrado: " + patente);
                    return;
                }
                // Acción de retiro: liberar plaza si la tenía y remover vehículo
                Plaza plaza = encontrada.getPlaza();
                if (plaza != null) {
                    plaza.setApta();
                    encontrada.setPlaza(null);
                }
                vehiculos.remove(encontrada);
                // refrescar combo en vista estacionamiento
                vista.getCmbVehiculos().removeAllItems();
                for (Vehiculo v : vehiculos) vista.getCmbVehiculos().addItem(v);
                JOptionPane.showMessageDialog(retirarView, "Vehículo " + patente + " retirado.");
                retirarView.dispose();
            });

            retirarView.setVisible(true);
        });

        vista.setVisible(true);
    }

    //CREAR CUENTA
    public void mostrarCrearCuenta(){
        vistaCrearCuenta = new CrearCuenta();
        vistaCrearCuenta.setLocationRelativeTo(null);
        cargarUsuariosEjemplo();
        poblarComboUsuarios();
        configurarEventosCrearCuenta();
        vistaCrearCuenta.setVisible(true);
    }

    private void configurarEventosCrearCuenta(){
        vistaCrearCuenta.getBtnCrear().addActionListener(e -> {
            String patente = vistaCrearCuenta.getTxtPatente().getText().trim();
            String modelo = vistaCrearCuenta.getTxtModelo().getText().trim();
            UsuarioInterno seleccionado = (UsuarioInterno) vistaCrearCuenta.getCmbUsuarios().getSelectedItem();

            if (seleccionado == null) {
                MensajesUI.error(vistaCrearCuenta, Mensajes.SELECCIONAR_USUARIO);
                return;
            }

            if (patente.isEmpty() || modelo.isEmpty()) {
                MensajesUI.error(vistaCrearCuenta, Mensajes.PATENTE_MODELO);
                return;
            }

            // Crear cuenta y vehículo y relacionarlos
            Cuenta cuenta = new Cuenta(seleccionado);
            Vehiculo vehiculo = new Vehiculo(patente, modelo, cuenta);

            // Registrar la cuenta y el vehículo creados para que estén disponibles
            cuentas.add(cuenta);
            vehiculos.add(vehiculo);

            // Aquí podrías persistir las instancias en un repositorio; por ahora solo mostramos confirmación
            JOptionPane.showMessageDialog(vistaCrearCuenta,
                    "Cuenta creada para " + seleccionado.getNombre() + "\nVehículo: " + vehiculo.getPatente(),
                    Mensajes.EXITO,
                    JOptionPane.INFORMATION_MESSAGE);

            vistaCrearCuenta.dispose();
            mostrarMenuPrincipal();
        });
    }

    private void cargarUsuariosEjemplo() {
        if (!usuarios.isEmpty()) return; 
        usuarios.add(new UsuarioInterno("Juan Pérez", 30, TipoDocumento.DNI, 12345678, "juan@ejemplo.com", 11223344, 1001, Rol.ESTUDIANTE));
        usuarios.add(new UsuarioInterno("María Gómez", 40, TipoDocumento.DNI, 87654321, "maria@ejemplo.com", 44332211, 1002, Rol.PROFESOR));
    }

    private void poblarComboUsuarios() {
        vistaCrearCuenta.getCmbUsuarios().removeAllItems();
        for (UsuarioInterno u : usuarios) {
            vistaCrearCuenta.getCmbUsuarios().addItem(u);
        }
    }

    // RECARGAR SALDO: poblar combo de cuentas y configurar listeners
    public void mostrarRecargarSaldo(){
        vistaRecargarSaldo = new RecargarSaldo();
        vistaRecargarSaldo.setLocationRelativeTo(null);
        poblarComboCuentas();
        configurarEventosRecargarSaldo();
        vistaRecargarSaldo.setVisible(true);
    }

    private void poblarComboCuentas() {
        vistaRecargarSaldo.getCmbCuentas().removeAllItems();
        for (Cuenta c : cuentas) {
            vistaRecargarSaldo.getCmbCuentas().addItem(c);
        }
    }

    //RECARGAR SALDO

    private void configurarEventosRecargarSaldo(){
        // Cuenta corriente
        vistaRecargarSaldo.getBtnRecargarCuentaCorriente().addActionListener(e -> {
            Cuenta seleccion = (Cuenta) vistaRecargarSaldo.getCmbCuentas().getSelectedItem();
            if (seleccion == null) {
                MensajesUI.error(vistaRecargarSaldo, Mensajes.SELECCIONE_CUENTA);
                return;
            }

            String txt = vistaRecargarSaldo.getTxtCantidad().getText().trim();
            Integer cantidad;
            try {
                cantidad = Integer.parseInt(txt);
            } catch (NumberFormatException ex) {
                MensajesUI.error(vistaRecargarSaldo, Mensajes.INGRESAR_CANTIDAD_VALIDA);
                return;
            }

            // Asegurar que la cuenta sea de tipo cuenta corriente
            seleccion.setCuentaCorriente();
            seleccion.recargarSaldoCuentaCorriente(cantidad);

            JOptionPane.showMessageDialog(vistaRecargarSaldo, "Se recargaron $" + cantidad + " a la cuenta de " + seleccion.getUsuario().getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vistaRecargarSaldo.dispose();
            mostrarMenuPrincipal();
        });

        // Abono mensual
        vistaRecargarSaldo.getBtnRecargarAbonoMensual().addActionListener(e -> {
            Cuenta seleccion = (Cuenta) vistaRecargarSaldo.getCmbCuentas().getSelectedItem();
            if (seleccion == null) {
                MensajesUI.error(vistaRecargarSaldo, Mensajes.SELECCIONE_CUENTA);
                return;
            }

            String txt = vistaRecargarSaldo.getTxtCantidad().getText().trim();
            Integer monto = null;
            try {
                monto = Integer.parseInt(txt);
            } catch (NumberFormatException ex) {
                MensajesUI.error(vistaRecargarSaldo, Mensajes.INGRESAR_CANTIDAD_VALIDA);
            }

            // Crear tarifa de abono mensual y mostrarla
            java.time.LocalDate hoy = java.time.LocalDate.now();
            java.time.LocalDate fin = hoy.plusDays(30);
            Modelos.TarifaAbonoMensual tarifa = new Modelos.TarifaAbonoMensual(monto, hoy.toString(), fin.toString());

            // Mostrar la tarifa al usuario y confirmar
            int resp = JOptionPane.showConfirmDialog(vistaRecargarSaldo,
                    "Se va a generar un abono mensual con la siguiente tarifa:\nMonto: $" + monto + "\nDesde: " + hoy + "\nHasta: " + fin + "\n¿Confirmar?",
                    "Confirmar Abono Mensual",
                    JOptionPane.YES_NO_OPTION);

            if (resp != JOptionPane.YES_OPTION) return;

            // Setear tipo y registrar cobro
            seleccion.setAbonoMensual();
            seleccion.registrarCobroAbono(tarifa);

            JOptionPane.showMessageDialog(vistaRecargarSaldo, "Abono mensual generado para " + seleccion.getUsuario().getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vistaRecargarSaldo.dispose();
            mostrarMenuPrincipal();
        });
    }

}
