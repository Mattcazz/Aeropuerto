package presentacion.seguridad.CUs;

import javax.swing.*;
import java.awt.*;
import integracion.personal.DAOEmpleado;
import integracion.FactoriaDAO;
import negocio.personal.TransferEmpleado;
import negocio.seguridad.SelectorEstrategiaPorRol;
import negocio.seguridad.TransferAcceso;
import negocio.seguridad.Zonas;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;

/**
 * GUI concreta para el caso de uso "Verificar Acceso".
 * Ahora comprueba permisos según estrategia (DNI + Zona).
 */
public class GUIVerificarAccesoImp extends GUIVerificarAcceso {
    private final JFrame marco;
    private final JTextField txtDni;
    private final JComboBox<String> comboZonas;
    private final JButton btnVerificar;
    private final JButton btnVolver;

    public GUIVerificarAccesoImp() {
        marco = new JFrame("Verificar Acceso a Zona Restringida");
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

        // Botones
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVerificar = new JButton("Verificar Acceso");
        btnVolver     = new JButton("Volver");
        p3.add(btnVerificar);
        p3.add(btnVolver);
        panel.add(p3);

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        btnVerificar.addActionListener(e -> verificarAccesoAction());
        btnVolver    .addActionListener(e -> volverAlMenu());

        marco.setVisible(true);
    }

    private void verificarAccesoAction() {
        try {
            // Validar formato
            String dni = txtDni.getText().trim();
            if (dni.isBlank()) throw new RuntimeException("DNI es obligatorio");
            String zona = (String) comboZonas.getSelectedItem();

            // Obtener rol
            DAOEmpleado daoEmp = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
            TransferEmpleado transferEmpleado = daoEmp.listarPorDni(dni);
            String rol = transferEmpleado.getTurno();
            if (rol == null) {
                JOptionPane.showMessageDialog(marco,
                    "Empleado no encontrado: " + dni,
                    "Error", JOptionPane.ERROR_MESSAGE);
                volverAlMenu();
                return;
            }

            // Aplicar estrategia
            TransferAcceso datos = new TransferAcceso(dni, zona, 0);
            var estrategia = new SelectorEstrategiaPorRol().getEstrategiaParaRol(rol);
            estrategia.gestionarAcceso(datos);

            // Éxito
            JOptionPane.showMessageDialog(marco,
                "Permiso concedido para zona " + zona,
                "Acceso Permitido", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(marco,
                "Acceso denegado: " + ex.getMessage(),
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
        } finally {
            // Siempre volver al menú
            volverAlMenu();
        }
    }

    private void volverAlMenu() {
        marco.setVisible(false);
        Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
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

