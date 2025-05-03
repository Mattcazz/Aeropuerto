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
    private JTextField txtVueloID;
    private JComboBox<String> cmbAvionID;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JComboBox<String> cmbSalidaHora;
    private JComboBox<String> cmbSalidaMinuto;
    private JComboBox<String> cmbAterrizajeHora;
    private JComboBox<String> cmbAterrizajeMinuto;
    private JComboBox<String> cmbTipo;
    private JCheckBox chkVIP;
    private JButton volverButton;
    private JButton crearButton;

    private List<String> aviones;

    public GUICrearVueloImp() {
        frame = new JFrame("Crear Vuelo");
    }
    
    public void init(List<String> avionIds) {
    	this.aviones = new ArrayList<String>(avionIds);
    	
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Vuelo ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Vuelo ID:"), gbc);
        gbc.gridx = 1;
        txtVueloID = new JTextField(15);
        panel.add(txtVueloID, gbc);

        // Avion ID (dropdown)
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Avion ID:"), gbc);
        gbc.gridx = 1;
        cmbAvionID = new JComboBox<>(aviones.toArray(new String[0]));
        panel.add(cmbAvionID, gbc);

        // Origen
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Origen:"), gbc);
        gbc.gridx = 1;
        txtOrigen = new JTextField(15);
        panel.add(txtOrigen, gbc);

        // Destino
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Destino:"), gbc);
        gbc.gridx = 1;
        txtDestino = new JTextField(15);
        panel.add(txtDestino, gbc);

        // Salida (hour and minute selectors)
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Hora de Salida:"), gbc);
        gbc.gridx = 1;
        JPanel salidaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cmbSalidaHora = new JComboBox<>(generateHourOptions());
        cmbSalidaMinuto = new JComboBox<>(generateMinuteOptions());
        salidaPanel.add(cmbSalidaHora);
        salidaPanel.add(new JLabel(":"));
        salidaPanel.add(cmbSalidaMinuto);
        panel.add(salidaPanel, gbc);

        // Aterrizaje (hour and minute selectors)
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Hora de Aterrizaje:"), gbc);
        gbc.gridx = 1;
        JPanel aterrizajePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cmbAterrizajeHora = new JComboBox<>(generateHourOptions());
        cmbAterrizajeMinuto = new JComboBox<>(generateMinuteOptions());
        aterrizajePanel.add(cmbAterrizajeHora);
        aterrizajePanel.add(new JLabel(":"));
        aterrizajePanel.add(cmbAterrizajeMinuto);
        panel.add(aterrizajePanel, gbc);

        // Tipo (dropdown)
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        cmbTipo = new JComboBox<>(new String[]{"Domestico", "Internacional"});
        panel.add(cmbTipo, gbc);

        // VIP (checkbox)
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("VIP:"), gbc);
        gbc.gridx = 1;
        chkVIP = new JCheckBox();
        panel.add(chkVIP, gbc);
        
        // Buttons panel placed below inputs
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        volverButton = new JButton("Volver");
        crearButton = new JButton("Crear");
        btnPanel.add(volverButton);
        btnPanel.add(crearButton);
        panel.add(btnPanel, gbc);

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
    public String getVueloId() { return txtVueloID.getText().trim(); }
    public String getAvionId() { return (String) cmbAvionID.getSelectedItem(); }
    public String getOrigen() { return txtOrigen.getText().trim(); }
    public String getDestino() { return txtDestino.getText().trim(); }
    public LocalDateTime getSalida() {
    	String hora = (String) cmbSalidaHora.getSelectedItem() + ":" + (String) cmbSalidaMinuto.getSelectedItem();
        LocalTime time = LocalTime.parse(hora);
        System.out.println("Salida Time: '" + hora + "' - " + time.toString());
        return LocalDateTime.of(LocalDate.now(), time);
    }
    public LocalDateTime getAterrizaje() {
    	String hora = (String) cmbAterrizajeHora.getSelectedItem() + ":" + (String) cmbAterrizajeMinuto.getSelectedItem();
        LocalTime time = LocalTime.parse(hora);
        System.out.println("Aterrizaje Time: '" + hora + "' - " + time.toString());
        return LocalDateTime.of(LocalDate.now(), time);
    }
    public String getTipo() { return (String) cmbTipo.getSelectedItem(); }
    public boolean getVip() { return chkVIP.isSelected(); }

	@Override
	public void actualizar(Eventos evento, Object datos) {
		
	}
	
	public JFrame getFrame() {
		return (this.frame);
	}
}
