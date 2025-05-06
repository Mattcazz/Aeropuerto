package presentacion.vuelos.CUs;

import presentacion.vuelos.ControladorImp;
import presentacion.vuelos.Eventos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class GUICrearVueloImp extends GUICrearVuelo {
	private JFrame frame;
    private JTextField textVueloID;
    private JComboBox<String> comboAvionID;
    private JTextField textOrigen;
    private JTextField textDestino;
    private JComboBox<String> comboSalidaHora;
    private JComboBox<String> comboSalidaMinuto;
    private JComboBox<String> comboAterrizajeHora;
    private JComboBox<String> comboAterrizajeMinuto;
    private JComboBox<String> comboTipo;
    private JCheckBox checkVIP;
    private JButton volverButton;
    private JButton crearButton;

    private List<String> aviones;

    public GUICrearVueloImp() { }
    
    public void init(List<String> avionIds) {
        frame = new JFrame("Crear Vuelo");
        
    	this.aviones = new ArrayList<String>(avionIds);
    	
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints contraints = new GridBagConstraints();
        contraints.insets = new Insets(5, 5, 5, 5);
        contraints.fill = GridBagConstraints.HORIZONTAL;

        // Vuelo ID
        contraints.gridx = 0;
        contraints.gridy = 0;
        panel.add(new JLabel("Vuelo ID:"), contraints);
        contraints.gridx = 1;
        textVueloID = new JTextField(15);
        panel.add(textVueloID, contraints);

        // Avion ID (dropdown)
        contraints.gridx = 0;
        contraints.gridy = 1;
        panel.add(new JLabel("Avion ID:"), contraints);
        contraints.gridx = 1;
        comboAvionID = new JComboBox<>(aviones.toArray(new String[0]));
        panel.add(comboAvionID, contraints);

        // Origen
        contraints.gridx = 0;
        contraints.gridy = 2;
        panel.add(new JLabel("Origen:"), contraints);
        contraints.gridx = 1;
        textOrigen = new JTextField(15);
        panel.add(textOrigen, contraints);

        // Destino
        contraints.gridx = 0;
        contraints.gridy = 3;
        panel.add(new JLabel("Destino:"), contraints);
        contraints.gridx = 1;
        textDestino = new JTextField(15);
        panel.add(textDestino, contraints);

        // Salida (hour and minute selectors)
        contraints.gridx = 0;
        contraints.gridy = 4;
        panel.add(new JLabel("Hora de Salida:"), contraints);
        contraints.gridx = 1;
        JPanel salidaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        comboSalidaHora = new JComboBox<>(generateHourOptions());
        comboSalidaMinuto = new JComboBox<>(generateMinuteOptions());
        comboSalidaMinuto.setEnabled(false);
        salidaPanel.add(comboSalidaHora);
        salidaPanel.add(new JLabel(":"));
        salidaPanel.add(comboSalidaMinuto);
        panel.add(salidaPanel, contraints);

        // Aterrizaje (hour and minute selectors)
        contraints.gridx = 0;
        contraints.gridy = 5;
        panel.add(new JLabel("Hora de Aterrizaje:"), contraints);
        contraints.gridx = 1;
        JPanel aterrizajePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        comboAterrizajeHora = new JComboBox<>(generateHourOptions());
        comboAterrizajeMinuto = new JComboBox<>(generateMinuteOptions());
        comboAterrizajeMinuto.setEnabled(false);
        aterrizajePanel.add(comboAterrizajeHora);
        aterrizajePanel.add(new JLabel(":"));
        aterrizajePanel.add(comboAterrizajeMinuto);
        panel.add(aterrizajePanel, contraints);

        // Tipo (dropdown)
        contraints.gridx = 0;
        contraints.gridy = 6;
        panel.add(new JLabel("Tipo:"), contraints);
        contraints.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Domestico", "Internacional"});
        panel.add(comboTipo, contraints);

        // VIP (checkbox)
        contraints.gridx = 0;
        contraints.gridy = 7;
        panel.add(new JLabel("VIP:"), contraints);
        contraints.gridx = 1;
        checkVIP = new JCheckBox();
        panel.add(checkVIP, contraints);
        
        // Buttons panel placed below inputs
        contraints.gridx = 0;
        contraints.gridy = 8;
        contraints.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        volverButton = new JButton("Volver");
        crearButton = new JButton("Crear");
        btnPanel.add(volverButton);
        btnPanel.add(crearButton);
        panel.add(btnPanel, contraints);

        // Assemble components into frame
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUICrearVueloImp.this);
			}
		});
        
        crearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ControladorImp.getInstancia().accion(Eventos.CREAR_VUELO, GUICrearVueloImp.this);
        	}
        });
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

    // Getters for retrieving form values
    public String getVueloId() { return textVueloID.getText().trim(); }
    public String getAvionId() { return (String) comboAvionID.getSelectedItem(); }
    public String getOrigen() { return textOrigen.getText().trim(); }
    public String getDestino() { return textDestino.getText().trim(); }
    public LocalDateTime getSalida() {
    	String hora = (String) comboSalidaHora.getSelectedItem() + ":" + (String) comboSalidaMinuto.getSelectedItem();
        LocalTime time = LocalTime.parse(hora);
        return LocalDateTime.of(LocalDate.now(), time);
    }
    public LocalDateTime getAterrizaje() {
    	String hora = (String) comboAterrizajeHora.getSelectedItem() + ":" + (String) comboAterrizajeMinuto.getSelectedItem();
        LocalTime time = LocalTime.parse(hora);
        return LocalDateTime.of(LocalDate.now(), time);
    }
    public String getTipo() { return (String) comboTipo.getSelectedItem(); }
    public boolean getVip() { return checkVIP.isSelected(); }

	@Override
	public void actualizar(Eventos evento, Object datos) {
		
	}
	
	public JFrame getFrame() {
		return (this.frame);
	}
}
