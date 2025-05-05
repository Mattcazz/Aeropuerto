package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import negocio.seguridad.TransferDispositivo;
import negocio.seguridad.SADispositivo;
import negocio.seguridad.FactoriaSA;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;

public class GUIDesactivarDispositivoImp extends GUIDesactivarDispositivo {
    private JFrame marco;
    private JTextField txtId;
    private JComboBox<String> comboTipo;
    private JButton btnDesactivar;
    private JButton btnVolver;

    public GUIDesactivarDispositivoImp() {
        marco = new JFrame("Desactivar Dispositivo");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ID
        JPanel r1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r1.add(new JLabel("ID Dispositivo:"));
        txtId = new JTextField(8);
        r1.add(txtId);
        panel.add(r1);

        // Tipo
        JPanel r2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r2.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Alarma", "Camara"});
        r2.add(comboTipo);
        panel.add(r2);

        // Botones
        JPanel r3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDesactivar = new JButton("Desactivar Dispositivo");
        btnVolver     = new JButton("Volver");
        r3.add(btnDesactivar);
        r3.add(btnVolver);
        panel.add(r3);

        marco.add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        btnDesactivar.addActionListener(e -> desactivarAction());
        btnVolver    .addActionListener(e -> volverMenu());

        marco.setVisible(true);
    }

    private void desactivarAction() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String tipo = (String) comboTipo.getSelectedItem();

            TransferDispositivo td = new TransferDispositivo(
                id, tipo, true, 0);

            SADispositivo sa = FactoriaSA.getInstancia()
                .nuevoSADispositivoImp();
            sa.desactivarDispositivo(td);

            JOptionPane.showMessageDialog(
                marco,
                "Dispositivo " + id + " desactivado",
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
                "Error desactivar: " + ex.getMessage(),
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
    public void actualizar(int evento, Object datos) {}

    @Override
    public JFrame getFrame() {
        return marco;
    }
}

