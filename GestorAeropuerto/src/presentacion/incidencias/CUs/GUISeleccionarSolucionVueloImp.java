package presentacion.incidencias.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

public class GUISeleccionarSolucionVueloImp extends GUISeleccionarSolucionVuelo {

    private JFrame marco;
    private JTextField campoId;
    private JComboBox<String> comboSoluciones;
    private JTextField campoCompensacion;

    public GUISeleccionarSolucionVueloImp() {
        marco = new JFrame("Seleccionar Solución - Incidencia de Vuelo");
        marco.setSize(450, 300);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Aplicar solución a incidencia de vuelo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel central con campos
        JPanel panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Identificador
        panelCentro.add(new JLabel("ID del vuelo:"));
        campoId = new JTextField();
        panelCentro.add(campoId);

        // Combo soluciones
        panelCentro.add(new JLabel("Solución:"));
        comboSoluciones = new JComboBox<>(new String[]{
            "Reprogramar vuelo",
            "Cambiar puerta de embarque",
            "Contactar tripulacion",
            "Redirigir pasajeros",
            "Compensacion economica"
        });
        panelCentro.add(comboSoluciones);

        // Compensación económica
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
                Controlador.getInstancia().accion(Eventos.SELECCIONAR_SOLUCION_VUELO, datos);
                JOptionPane.showMessageDialog(marco, "Solución aplicada correctamente.");
                marco.dispose();
                Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);  ////
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
    	    switch (evento) {
    	        case Eventos.SOLUCION_APLICADA_OK -> {
    	            JOptionPane.showMessageDialog(marco, "Solución aplicada correctamente.");
    	            marco.dispose(); 
    	        }
    	        case Eventos.SOLUCION_APLICADA_NO -> {
    	            String error = (datos instanceof String s) ? s : "Ha ocurrido un error al aplicar la solución.";
    	            JOptionPane.showMessageDialog(marco, error, "Error", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }
    	}

    
    public JFrame getFrame() {
        return marco;
    }
}
