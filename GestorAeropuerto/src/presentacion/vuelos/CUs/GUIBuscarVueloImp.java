package presentacion.vuelos.CUs;

import presentacion.vuelos.ControladorImp;
import presentacion.vuelos.Eventos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalTime;

public class GUIBuscarVueloImp extends GUIBuscarVuelo {
    private JFrame frame;
    private JComboBox<String> comboCondicion;
    private JComboBox<String> comboHora;
    private JComboBox<String> comboMinuto;
    private JButton volverButton;
    private JButton buscarButton;

    public GUIBuscarVueloImp() { }
    
    public void init() {
    	frame = new JFrame("Buscar Vuelo");
    	
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Condition ComboBox (Antes / Despues)
        comboCondicion = new JComboBox<>(new String[]{"Antes", "Despues"});

        // Time selectors
        comboHora = new JComboBox<>(generateHourOptions());
        comboMinuto = new JComboBox<>(generateMinuteOptions());

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        timePanel.add(comboCondicion);
        timePanel.add(new JLabel(" de "));
        timePanel.add(comboHora);
        timePanel.add(new JLabel(":"));
        timePanel.add(comboMinuto);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(timePanel, constraints);

        // Buttons panel
        volverButton = new JButton("Volver");
        buscarButton = new JButton("Buscar");

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIBuscarVueloImp.this);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorImp.getInstancia().accion(Eventos.BUSCAR_VUELO, GUIBuscarVueloImp.this);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(buscarButton);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(buttonPanel, constraints);

        frame.getContentPane().add(panel);
    }

    private String[] generateHourOptions() {
        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i);
        }
        return hours;
    }

    private String[] generateMinuteOptions() {
        String[] minutes = new String[12];
        for (int i = 0; i < 60; i += 5) {
            minutes[i / 5] = String.format("%02d", i);
        }
        return minutes;
    }
    
    public String getCondicion() {
        return (String) comboCondicion.getSelectedItem();
    }

    public LocalTime getHoraSeleccionada() {
        int hora = Integer.parseInt((String) comboHora.getSelectedItem());
        int minuto = Integer.parseInt((String) comboMinuto.getSelectedItem());
        return LocalTime.of(hora, minuto);
    }

	@Override
	public void actualizar(Eventos evento, Object datos) {
		
	}
	
	public JFrame getFrame() {
		return (this.frame);
	}
}
