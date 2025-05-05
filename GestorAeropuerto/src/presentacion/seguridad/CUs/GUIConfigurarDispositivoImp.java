package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import negocio.seguridad.TransferDispositivo;
import negocio.seguridad.SADispositivo;
import negocio.seguridad.FactoriaSA;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;


public class GUIConfigurarDispositivoImp extends GUIConfigurarDispositivo {
    private JFrame marco;
    private JTextField txtId;
    private JTextField txtNivel;
    private JComboBox<String> comboTipo;
    private JButton btnConfigurar;
    private JButton btnVolver;

    public GUIConfigurarDispositivoImp() {
        marco = new JFrame("Configurar Dispositivo");
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

        // Nivel sensibilidad
        JPanel r3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r3.add(new JLabel("Nivel Sensibilidad (0-10):"));
        txtNivel = new JTextField(3);
        r3.add(txtNivel);
        panel.add(r3);

        // Botones
        JPanel r4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnConfigurar = new JButton("Configurar Dispositivo");
        btnVolver      = new JButton("Volver");
        r4.add(btnConfigurar);
        r4.add(btnVolver);
        panel.add(r4);

        marco.add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        btnConfigurar.addActionListener(e -> configurarAction());
        btnVolver     .addActionListener(e -> volverMenu());

        marco.setVisible(true);
    }

    private void configurarAction() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String tipo = (String) comboTipo.getSelectedItem();
            int nivel = Integer.parseInt(txtNivel.getText().trim());

            TransferDispositivo td = new TransferDispositivo(
                id, tipo, false, nivel);

            SADispositivo sa = FactoriaSA.getInstancia()
                .nuevoSADispositivoImp();
            sa.configurarDispositivo(td);

            JOptionPane.showMessageDialog(
                marco,
                "Dispositivo " + id + " configurado",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            volverMenu();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                marco,
                "Formato no válido",
                "Error formato",
                JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                marco,
                "Error configurar: " + ex.getMessage(),
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

    @Override public void actualizar(int evento, Object datos) {}
    @Override public JFrame getFrame() { return marco; }
}
