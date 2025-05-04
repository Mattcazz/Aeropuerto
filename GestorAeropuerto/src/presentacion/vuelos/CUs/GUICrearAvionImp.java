package presentacion.vuelos.CUs;

import presentacion.vuelos.ControladorImp;
import presentacion.vuelos.Eventos;

import javax.swing.*;

import negocio.InfoAeropuerto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICrearAvionImp extends GUICrearAvion {
	private JFrame frame;
    private JTextField textAvionID;
    private JTextField textAltura;
    private JTextField textAnchura;
    private JTextField textLongitud;
    private JTextField textMaxPasajeros;
    private JTextField textPeso;
    private JComboBox<String> comboAerolinea;
    private JButton volverButton;
    private JButton crearButton;

    public GUICrearAvionImp() { }
    
    public void init() {
    	frame = new JFrame("Crear Avion");
    	
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Vuelo ID
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Avion ID:"), constraints);
        constraints.gridx = 1;
        textAvionID = new JTextField(15);
        panel.add(textAvionID, constraints);

        // Altura
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Altura:"), constraints);
        constraints.gridx = 1;
        textAltura = new JTextField(15);
        panel.add(textAltura, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("m"), constraints);
        
        // Anchura
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Anchura:"), constraints);
        constraints.gridx = 1;
        textAnchura = new JTextField(15);
        panel.add(textAnchura, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("m"), constraints);
        
        // Longitud
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Longitud:"), constraints);
        constraints.gridx = 1;
        textLongitud = new JTextField(15);
        panel.add(textLongitud, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("m"), constraints);
        
        // Peso
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Peso:"), constraints);
        constraints.gridx = 1;
        textPeso = new JTextField(15);
        panel.add(textPeso, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel("kg"), constraints);
        
        // Max Pasajeros
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Max Pasajeros:"), constraints);
        constraints.gridx = 1;
        textMaxPasajeros = new JTextField(15);
        panel.add(textMaxPasajeros, constraints);

        // Aerolinea
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("Aerolinea:"), constraints);
        constraints.gridx = 1;
        comboAerolinea = new JComboBox<>(InfoAeropuerto.aerolineas);
        panel.add(comboAerolinea, constraints);
        
        // Buttons panel placed below inputs
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        volverButton = new JButton("Volver");
        crearButton = new JButton("Crear");
        btnPanel.add(volverButton);
        btnPanel.add(crearButton);
        panel.add(btnPanel, constraints);

        // Assemble components into frame
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUICrearAvionImp.this);
			}
		});
        
        crearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ControladorImp.getInstancia().accion(Eventos.CREAR_AVION, GUICrearAvionImp.this);
        	}
        });
    }

    // Getters for retrieving form values
    public String getAvionId() { return textAvionID.getText().trim(); }
    public String getAltura() { return textAltura.getText().trim(); }
    public String getAnchura() { return textAnchura.getText().trim(); }
    public String getLongitud() { return textLongitud.getText().trim(); }
    public String getPeso() { return textPeso.getText().trim(); }
    public String getMaxPasajeros() { return textMaxPasajeros.getText().trim(); }
    public String getAerolinea() { return (String) comboAerolinea.getSelectedItem(); }
    
    @Override
	public void actualizar(Eventos evento, Object datos) {
		
	}
	
	public JFrame getFrame() {
		return (this.frame);
	}
}
