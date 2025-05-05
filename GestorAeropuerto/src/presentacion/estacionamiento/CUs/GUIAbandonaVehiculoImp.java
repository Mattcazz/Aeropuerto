package presentacion.estacionamiento.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;

public class GUIAbandonaVehiculoImp extends GUIAbandonaVehiculo {
    private JFrame frame;
    private JTextField txtNumero;

    public GUIAbandonaVehiculoImp() {
        frame = new JFrame("Abandona Vehículo");
        frame.setLayout(new GridLayout(2, 2, 5, 5));
        frame.add(new JLabel("Número plaza:"));
        txtNumero = new JTextField(); frame.add(txtNumero);
        JButton btnOk = new JButton("Aceptar");
        JButton btnCancel = new JButton("Cancelar");
        btnOk.addActionListener(e -> {
            int num = Integer.parseInt(txtNumero.getText());
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.INICIAR_SALIDA, num);
        });
        btnCancel.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frame.add(btnOk); frame.add(btnCancel);
        frame.pack(); frame.setLocationRelativeTo(null);
    }

    @Override
    public void actualizar(int evento, Object datos) {}

    public void mostrar() {
        frame.setVisible(true);
    }
}