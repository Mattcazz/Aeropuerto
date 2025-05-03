package presentacion.incidencias.CUs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

public class GUISeleccionarTipoIncidenciaImp extends GUISeleccionarTipoIncidencia {

    private JFrame marco;

    public GUISeleccionarTipoIncidenciaImp() {
        marco = new JFrame("Seleccionar Tipo de Incidencia");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(400, 300);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Selecciona el tipo de incidencia", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        JButton botonVuelo = new JButton("Incidencia de Vuelo");
        JButton botonEquipaje = new JButton("Incidencia de Equipaje");

        Dimension botonSize = new Dimension(200, 40);
        botonVuelo.setMaximumSize(botonSize);
        botonEquipaje.setMaximumSize(botonSize);

        botonVuelo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonEquipaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(Box.createVerticalStrut(30));
        panelBotones.add(botonVuelo);
        panelBotones.add(Box.createVerticalStrut(20));
        panelBotones.add(botonEquipaje);

        marco.add(panelBotones, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonVolver = new JButton("Volver");
        botonVolver.setPreferredSize(new Dimension(100, 30));
        panelInferior.add(botonVolver);
        marco.add(panelInferior, BorderLayout.SOUTH);

        // Acciones
        botonVuelo.addActionListener(e -> {
            marco.setVisible(false);
            GUICrearIncidenciaVueloImp guiVuelo = new GUICrearIncidenciaVueloImp();
            guiVuelo.getFrame().setVisible(true);
        });

        botonEquipaje.addActionListener(e -> {
            marco.setVisible(false);
            GUICrearIncidenciaEquipajeImp guiEquipaje = new GUICrearIncidenciaEquipajeImp();
            guiEquipaje.getFrame().setVisible(true);
        });

        botonVolver.addActionListener(e -> {
            marco.dispose();
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        // Mostrar
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
