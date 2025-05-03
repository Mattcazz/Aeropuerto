package presentacion.incidencias.CUs;

import java.awt.*;
import javax.swing.*;

import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

public class GUISeleccionarTipoIncidenciaObservarImp extends GUISeleccionarTipoIncidenciaObservar {

    private JFrame marco;

    public GUISeleccionarTipoIncidenciaObservarImp() {
        marco = new JFrame("Seleccionar Tipo de Incidencia");
        marco.setSize(400, 300);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Selecciona el tipo de incidencia", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel central (botones)
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;

        // Botón vuelo
        JButton botonVuelo = new JButton("Incidencias de Vuelo");
        botonVuelo.setPreferredSize(new Dimension(200, 35));
        botonVuelo.addActionListener(e ->
                Controlador.getInstancia().accion(Eventos.VISUALIZAR_INCIDENCIAS_VUELO, null));
        gbc.gridy = 0;
        panelCentral.add(botonVuelo, gbc);

        // Botón equipaje
        JButton botonEquipaje = new JButton("Incidencias de Equipaje");
        botonEquipaje.setPreferredSize(new Dimension(200, 35));
        botonEquipaje.addActionListener(e ->
                Controlador.getInstancia().accion(Eventos.VISUALIZAR_INCIDENCIAS_EQUIPAJE, null));
        gbc.gridy = 1;
        panelCentral.add(botonEquipaje, gbc);

        // Botón volver
        JButton botonVolver = new JButton("Volver");
        botonVolver.setPreferredSize(new Dimension(120, 30));
        botonVolver.addActionListener(e -> {
        	 Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        	this.marco.dispose();
        });
        gbc.gridy = 2;
        panelCentral.add(botonVolver, gbc);

        marco.add(panelCentral, BorderLayout.CENTER);

        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    public JFrame getFrame() {
        return marco;
    }

    @Override
    public void actualizar(int evento, Object datos) {
    }
}
