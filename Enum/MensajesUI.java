package Enum;
import javax.swing.JOptionPane;
import java.awt.Component;

public class MensajesUI {

    public static void error(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, Mensajes.ERROR, JOptionPane.ERROR_MESSAGE);
    }
}
