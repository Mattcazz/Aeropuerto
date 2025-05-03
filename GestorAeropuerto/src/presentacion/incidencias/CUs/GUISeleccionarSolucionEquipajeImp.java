package presentacion.incidencias.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

public class GUISeleccionarSolucionEquipajeImp extends GUISeleccionarSolucionEquipaje {

    private JFrame marco;
    private JTextField campoId;
    private JComboBox<String> comboSoluciones;
    private JTextField campoCompensacion;

    public GUISeleccionarSolucionEquipajeImp() {
        marco = new JFrame("Seleccionar Solución - Incidencia de Equipaje");
        marco.setSize(450, 300);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Aplicar solución a incidencia de equipaje", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ID pasajero
        panelCentro.add(new JLabel("ID pasajero (nombre apellido):"));
        campoId = new JTextField();
        panelCentro.add(campoId);

        // Soluciones disponibles
        panelCentro.add(new JLabel("Solución:"));
        comboSoluciones = new JComboBox<>(new String[]{
            "Enviar a domicilio",
            "Enviar a otro vuelo",
            "Retirar manualmente",
            "Almacenar temporalmente",
            "Compensacion economica"
        });
        panelCentro.add(comboSoluciones);

        // Compensación
        panelCentro.add(new JLabel("Compensación (€):"));
        campoCompensacion = new JTextField();
        panelCentro.add(campoCompensacion);

        marco.add(panelCentro, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton botonAplicar = new JButton("Aplicar solución");
        JButton botonVolver = new JButton("Volver");

        botonAplicar.addActionListener(e -> {
            String id = campoId.getText().trim();
            String solucion = (String) comboSoluciones.getSelectedItem();
            String compensacionStr = campoCompensacion.getText().trim();

            if (id.isEmpty() || solucion == null || compensacionStr.isEmpty()) {
                JOptionPane.showMessageDialog(marco, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                float compensacion = Float.parseFloat(compensacionStr);
                Object[] datos = { id, solucion.toLowerCase(), compensacion };
                Controlador.getInstancia().accion(Eventos.SELECCIONAR_SOLUCION_EQUIPAJE, datos);
                JOptionPane.showMessageDialog(marco, "Solución aplicada correctamente.");
                marco.dispose();
                Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(marco, "Introduce una compensación válida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonVolver.addActionListener(e -> {
            marco.dispose();
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        panelBotones.add(botonAplicar);
        panelBotones.add(botonVolver);

        marco.add(panelBotones, BorderLayout.SOUTH);
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
    }

    
    public JFrame getFrame() {
        return marco;
    }
}
