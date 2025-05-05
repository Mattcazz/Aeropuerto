package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;
import negocio.seguridad.TransferAcceso;
import negocio.seguridad.Zonas;
import negocio.seguridad.SAAcceso;
import negocio.seguridad.FactoriaSA;

/**
 * GUI concreta para el caso de uso "Modificar Acceso".
 */
public class GUIModificarAccesoImp extends GUIModificarAcceso {
    private JFrame marco;
    private JTextField txtAccesoId;
    private JComboBox<String> comboZonas;
    private JTextField txtDuracion;
    private JButton btnModificar;
    private JButton btnVolver;

    public GUIModificarAccesoImp() {
        marco = new JFrame("Modificar Acceso");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // ID del acceso
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("ID Acceso:"));
        txtAccesoId = new JTextField(10);
        p1.add(txtAccesoId);
        panel.add(p1);

        // Nueva zona
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Nueva Zona:"));
        comboZonas = new JComboBox<>(Zonas.TODAS);
        p2.add(comboZonas);
        panel.add(p2);

        // Nueva duración
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Nueva Duración (min):"));
        txtDuracion = new JTextField(5);
        p3.add(txtDuracion);
        panel.add(p3);

        // Botones
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnModificar = new JButton("Modificar Acceso");
        btnVolver    = new JButton("Volver");
        p4.add(btnModificar);
        p4.add(btnVolver);
        panel.add(p4);

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        btnModificar.addActionListener(e -> modificarAccesoAction());
        btnVolver  .addActionListener(e -> volverMenu());

        marco.setVisible(true);
    }

    private void modificarAccesoAction() {
        try {
            int accesoId = Integer.parseInt(txtAccesoId.getText().trim());
            String zona = (String)comboZonas.getSelectedItem();
            int duracion = Integer.parseInt(txtDuracion.getText().trim());

            // Preparar DTO
            TransferAcceso datos = new TransferAcceso();
            datos.setAccesoId(accesoId);
            datos.setZona(zona);
            datos.setDuracion(duracion);

            // Invocar servicio
            SAAcceso sa = FactoriaSA.getInstancia().nuevoSAAccesoImp();
            sa.modificarAcceso(datos);

            JOptionPane.showMessageDialog(
                marco,
                "Acceso " + accesoId + " modificado correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            volverMenu();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                marco,
                "ID o duración no válidos",
                "Error de formato",
                JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                marco,
                "Error al modificar acceso: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void volverMenu() {
        marco.setVisible(false);
        Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // No utilizado en esta GUI
    }

    @Override
    public JFrame getFrame() {
        return marco;
    }
}
