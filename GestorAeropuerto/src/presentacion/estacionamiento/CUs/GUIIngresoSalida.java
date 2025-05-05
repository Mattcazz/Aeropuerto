package presentacion.estacionamiento.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;

public class GUIIngresoSalida {
    private static GUIIngresoSalida instancia = new GUIIngresoSalida();
    public static GUIIngresoSalida getInstancia() { return instancia; }

    private JFrame frame;
    private JTextField txtNumero;

    private GUIIngresoSalida() {
        frame = new JFrame("Registrar salida de vehículo");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(new JLabel("Número de plaza"));
        txtNumero = new JTextField(); frame.add(txtNumero);
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        btnAceptar.addActionListener(e -> {
            frame.setVisible(false);
            int numero = Integer.parseInt(txtNumero.getText());
            Controlador.getInstancia().accion(Eventos.INICIAR_SALIDA, numero);
        });
        btnCancelar.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frame.add(btnAceptar);
        frame.add(btnCancelar);
        frame.pack(); frame.setLocationRelativeTo(null);
    }

    public void mostrar() { frame.setVisible(true); }
}