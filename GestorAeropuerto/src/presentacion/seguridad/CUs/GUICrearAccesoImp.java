
package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import negocio.seguridad.TransferAcceso;
import negocio.seguridad.Zonas;
import negocio.seguridad.SAAcceso;
import negocio.personal.TransferEmpleado;
import negocio.seguridad.FactoriaSA;
import integracion.personal.DAOEmpleado;
import integracion.FactoriaDAO;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;


public class GUICrearAccesoImp extends GUICrearAcceso {
    private JFrame marco;
    private JTextField txtDni;
    private JComboBox<String> comboZonas;
    private JTextField txtDuracion;
    private JButton btnCrear;
    private JButton btnVolver;

    public GUICrearAccesoImp() {
        marco = new JFrame("Crear Acceso a Zona Restringida");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // DNI Empleado
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("DNI Empleado:"));
        txtDni = new JTextField(10);
        p1.add(txtDni);
        panel.add(p1);

        // Zona
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Zona:"));
        comboZonas = new JComboBox<>(Zonas.TODAS);
        p2.add(comboZonas);
        panel.add(p2);

        // Duración
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Duración (min):"));
        txtDuracion = new JTextField(5);
        p3.add(txtDuracion);
        panel.add(p3);

        // Botones
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCrear  = new JButton("Crear Acceso");
        btnVolver = new JButton("Volver");
        p4.add(btnCrear);
        p4.add(btnVolver);
        panel.add(p4);

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        // Acciones
        btnCrear.addActionListener(e -> crearAccesoAction());
        btnVolver.addActionListener(e -> {
            marco.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        marco.setVisible(true);
    }

    private void crearAccesoAction() {
        try {
            // 1) Recoger y validar formato
            String dni = txtDni.getText().trim();
            if (dni.isBlank()) {
                JOptionPane.showMessageDialog(marco,
                    "DNI es obligatorio",
                    "Error de formato",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            String zona = (String) comboZonas.getSelectedItem();
            int duracion = Integer.parseInt(txtDuracion.getText().trim());

            // 2) Preparar DTO con DNI
            TransferAcceso datos = new TransferAcceso(dni, zona, duracion);

            // 3) Obtener rol
            DAOEmpleado daoEmp = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
            TransferEmpleado transferEmpleado = daoEmp.listarPorDni(String.valueOf(datos.getEmpleadoDni())); // por que hacemos String.valueOf sobre algo que ya es String???
            String rol = transferEmpleado.getTurno();
            if (rol == null) {
                JOptionPane.showMessageDialog(marco,
                    "Empleado no encontrado: " + dni,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4) Invocar servicio de negocio
            SAAcceso sa = FactoriaSA.getInstancia().nuevoSAAccesoImp();
            sa.crearAcceso(datos, rol);

            // 5) Mensaje éxito
            JOptionPane.showMessageDialog(marco,
                "Acceso creado para zona " + zona,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

            // 6) Volver al menú
            marco.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(marco,
                "Duración no válida",
                "Error de formato",
                JOptionPane.WARNING_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(marco,
                "Error de validación: " + ex.getMessage(),
                "Atención",
                JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(marco,
                "Error en el servicio: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // No usado en esta GUI
    }

    @Override
    public JFrame getFrame() {
        return marco;
    }
}
