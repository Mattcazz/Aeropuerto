package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import negocio.seguridad.TransferDispositivo;
import negocio.seguridad.SADispositivo;
import negocio.seguridad.FactoriaSA;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;

public class GUIActivarDispositivoImp extends GUIActivarDispositivo {
    private JFrame marco;
    private JTextField txtId;
    private JComboBox<String> comboTipo;
    private JButton btnActivar;
    private JButton btnVolver;

    public GUIActivarDispositivoImp() {
        marco = new JFrame("Activar Dispositivo");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ID Dispositivo
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("ID Dispositivo:"));
        txtId = new JTextField(8);
        row1.add(txtId);
        panel.add(row1);

        // Tipo
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Alarma", "Camara"});
        row2.add(comboTipo);
        panel.add(row2);

        // Botones
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnActivar = new JButton("Activar Dispositivo");
        btnVolver  = new JButton("Volver");
        row3.add(btnActivar);
        row3.add(btnVolver);
        panel.add(row3);

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        btnActivar.addActionListener(e -> activarAction());
        btnVolver .addActionListener(e -> volverMenu());

        marco.setVisible(true);
    }

    private void activarAction() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String tipo = (String) comboTipo.getSelectedItem();

            TransferDispositivo td = new TransferDispositivo(
                id, tipo, false, 0);

            SADispositivo sa = FactoriaSA.getInstancia()
                .nuevoSADispositivoImp();
            sa.activarDispositivo(td);

            JOptionPane.showMessageDialog(
                marco,
                "Dispositivo " + id + " activado",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            volverMenu();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                marco,
                "ID no válido",
                "Error formato",
                JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                marco,
                "Error activar: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void volverMenu() {
        marco.setVisible(false);
        Controlador.getInstancia()
            .accion(Eventos.VOLVER_MENU, null);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // No usado
    }

    @Override
    public JFrame getFrame() {
        return marco;
    }
}
