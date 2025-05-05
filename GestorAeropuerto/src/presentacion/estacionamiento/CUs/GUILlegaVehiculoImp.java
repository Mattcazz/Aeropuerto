package presentacion.estacionamiento.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;
import negocio.estacionamiento.TransferPlaza;

public class GUILlegaVehiculoImp extends GUILlegaVehiculo {
    private JFrame frame;
    private JTextField txtNumero, txtMatricula;

    public GUILlegaVehiculoImp() {
        frame = new JFrame("Llega Vehículo");
        frame.setLayout(new GridLayout(3, 2, 5, 5));
        frame.add(new JLabel("Número plaza:"));
        txtNumero = new JTextField();
        frame.add(txtNumero);
        frame.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        frame.add(txtMatricula);

        JButton btnOk = new JButton("Aceptar");
        JButton btnCancel = new JButton("Cancelar");

        btnOk.addActionListener(e -> {
            int num;
            try {
                num = Integer.parseInt(txtNumero.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                    "Número de plaza inválido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String mat = txtMatricula.getText().trim();
            // Validación estricta: 4 dígitos, guion, 3 mayúsculas
            if (!mat.matches("\\d{4}-[A-Z]{3}")) {
                JOptionPane.showMessageDialog(frame,
                    "La matrícula debe tener el formato 1234-ABC",
                    "Formato inválido",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            frame.setVisible(false);
            Controlador.getInstancia().accion(
                Eventos.LLEGA_VEHICULO,
                new TransferPlaza(num, null, mat)
            );
        });

        btnCancel.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        frame.add(btnOk);
        frame.add(btnCancel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actualizar(int evento, Object datos) {
    }

    @Override
    public void mostrar() {
        frame.setVisible(true);
    }
}