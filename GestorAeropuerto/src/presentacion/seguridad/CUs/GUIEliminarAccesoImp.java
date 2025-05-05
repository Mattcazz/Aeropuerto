package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;
import negocio.seguridad.SAAcceso;
import negocio.seguridad.FactoriaSA;

/**
 * GUI concreta para el caso de uso "Eliminar Acceso".
 */
public class GUIEliminarAccesoImp extends GUIEliminarAcceso {
    private JFrame marco;
    private JTextField txtId;
    private JButton btnEliminar;
    private JButton btnVolver;

    public GUIEliminarAccesoImp() {
        marco = new JFrame("Eliminar Acceso");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("ID Acceso:"));
        txtId = new JTextField(10);
        p1.add(txtId);
        panel.add(p1);

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEliminar = new JButton("Eliminar Acceso");
        btnVolver   = new JButton("Volver");
        p2.add(btnEliminar);
        p2.add(btnVolver);
        panel.add(p2);

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                SAAcceso sa = FactoriaSA.getInstancia().nuevoSAAccesoImp();
                sa.eliminarAcceso(id);
                JOptionPane.showMessageDialog(marco,
                    "Acceso " + id + " eliminado correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                marco.setVisible(false);
                Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(marco,
                    "ID no válido",
                    "Error de formato", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(marco,
                    "Error al eliminar acceso: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            marco.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        marco.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // No necesario
    }

    @Override
    public JFrame getFrame() {
        return marco;
    }
}