package presentacion.vuelos.CUs;

import javax.swing.*;

import negocio.InfoAeropuerto;
import negocio.vuelos.TransferAvion;
import negocio.vuelos.TransferVuelo;
import presentacion.vuelos.ControladorImp;
import presentacion.vuelos.Eventos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GUIActualizarAvionImp extends GUIActualizarAvion {
	private JFrame frame;
    private JLabel labelAvionID;
    private JTextField textAltura;
    private JTextField textAnchura;
    private JTextField textLongitud;
    private JTextField textMaxPasajeros;
    private JTextField textPeso;
    private JComboBox<String> comboAerolinea;
    private JButton volverButton;
    private JButton actualizarButton;

	private GUIModificarAvionImp daddy;

	public GUIActualizarAvionImp() { }

	public void init(TransferAvion currentData, GUIModificarAvionImp daddy) {
		frame = new JFrame("Actualizar Avion");
		
		this.daddy = daddy;

		initComponents(currentData);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initComponents(TransferAvion startData) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Avion ID (label)
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Avion ID:"), constraints);
		constraints.gridx = 1;
		labelAvionID = new JLabel(startData.getAvionId());
		panel.add(labelAvionID, constraints);

		// Altura
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Altura:"), constraints);
        constraints.gridx = 1;
        textAltura = new JTextField(String.valueOf(startData.getAltura()), 15);
        panel.add(textAltura, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("m"), constraints);
        
        // Anchura
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Anchura:"), constraints);
        constraints.gridx = 1;
        textAnchura = new JTextField(String.valueOf(startData.getAnchura()), 15);
        panel.add(textAnchura, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("m"), constraints);
        
        // Longitud
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Longitud:"), constraints);
        constraints.gridx = 1;
        textLongitud = new JTextField(String.valueOf(startData.getLongitud()), 15);
        panel.add(textLongitud, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("m"), constraints);
        
        // Peso
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Peso:"), constraints);
        constraints.gridx = 1;
        textPeso = new JTextField(String.valueOf(startData.getPeso()), 15);
        panel.add(textPeso, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("kg"), constraints);
        
        // Max Pasajeros
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Max Pasajeros:"), constraints);
        constraints.gridx = 1;
        textMaxPasajeros = new JTextField(String.valueOf(startData.getMaxPasajeros()), 15);
        panel.add(textMaxPasajeros, constraints);

        // Aerolinea
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("Aerolinea:"), constraints);
        constraints.gridx = 1;
        comboAerolinea = new JComboBox<>(InfoAeropuerto.aerolineas);
        comboAerolinea.setSelectedItem(startData.getAerolinea());
        panel.add(comboAerolinea, constraints);

		// Buttons
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		volverButton = new JButton("Volver");
		actualizarButton = new JButton("Actualizar");
		buttonPanel.add(volverButton);
		buttonPanel.add(actualizarButton);
		panel.add(buttonPanel, constraints);

		// Frame layout
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIActualizarAvionImp.this);
			}
		});

		actualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ACTUALIZAR_AVION, GUIActualizarAvionImp.this);
			}
		});
	}

	// Getters for retrieving form values
	public String getAvionId() { return labelAvionID.getText().trim(); }
    public String getAltura() { return textAltura.getText().trim(); }
    public String getAnchura() { return textAnchura.getText().trim(); }
    public String getLongitud() { return textLongitud.getText().trim(); }
    public String getPeso() { return textPeso.getText().trim(); }
    public String getMaxPasajeros() { return textMaxPasajeros.getText().trim(); }
    public String getAerolinea() { return (String) comboAerolinea.getSelectedItem(); }
	
	public GUIModificarAvionImp getParent() {
		return (this.daddy);
	}

	@Override
	public void actualizar(Eventos evento, Object datos) {

	}

	public JFrame getFrame() {
		return (this.frame);
	}
}
