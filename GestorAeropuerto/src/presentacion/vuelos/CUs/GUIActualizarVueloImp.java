package presentacion.vuelos.CUs;

import javax.swing.*;

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

public class GUIActualizarVueloImp extends GUIActualizarVuelo {
	private JFrame frame;
	private JLabel labelVueloID;
	private JComboBox<String> comboAvionID;
	private JTextField textOrigen;
	private JTextField textDestino;
	private JComboBox<String> comboSalidaHora;
	private JComboBox<String> comboSalidaMinuto;
	private JComboBox<String> comboAterrizajeHora;
	private JComboBox<String> comboAterrizajeMinuto;
	private JComboBox<String> comboTipo;
	private JCheckBox checkboxVip;
	private JButton volverButton;
	private JButton actualizarButton;

	private List<String> avionIds;
	private GUIModificarVueloImp daddy;

	public GUIActualizarVueloImp() { }

	public void init(TransferVuelo currentData, List<String> avionIds, GUIModificarVueloImp daddy) {
		frame = new JFrame("Actualizar Vuelo");
		
		this.avionIds = new ArrayList<String>(avionIds);
		this.daddy = daddy;

		initComponents(currentData);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initComponents(TransferVuelo startData) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Vuelo ID (label)
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Vuelo ID:"), constraints);
		constraints.gridx = 1;
		labelVueloID = new JLabel(startData.getVueloId());
		panel.add(labelVueloID, constraints);

		// Avion ID (dropdown)
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(new JLabel("Avion ID:"), constraints);
		constraints.gridx = 1;
		comboAvionID = new JComboBox<>(avionIds.toArray(new String[0]));
		comboAvionID.setSelectedItem(startData.getAvionId());
		panel.add(comboAvionID, constraints);

		// Origen
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(new JLabel("Origen:"), constraints);
		constraints.gridx = 1;
		textOrigen = new JTextField(startData.getOrigen(), 15);
		panel.add(textOrigen, constraints);

		// Destino
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(new JLabel("Destino:"), constraints);
		constraints.gridx = 1;
		textDestino = new JTextField(startData.getDestino(), 15);
		panel.add(textDestino, constraints);

		// Salida time selectors
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(new JLabel("Hora de Salida:"), constraints);
		constraints.gridx = 1;
		JPanel salidaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		comboSalidaHora = new JComboBox<>(generateHourOptions());
		comboSalidaMinuto = new JComboBox<>(generateMinuteOptions());
		comboSalidaHora.setSelectedItem(String.format("%02d", startData.getTiempoSalida().getHour()));
		comboSalidaMinuto.setSelectedItem(String.format("%02d", startData.getTiempoSalida().getMinute() / 5 * 5));
		salidaPanel.add(comboSalidaHora);
		salidaPanel.add(new JLabel(":"));
		salidaPanel.add(comboSalidaMinuto);
		panel.add(salidaPanel, constraints);

		// Aterrizaje time selectors
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(new JLabel("Hora de Aterrizaje:"), constraints);
		constraints.gridx = 1;
		JPanel aterrizajePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		comboAterrizajeHora = new JComboBox<>(generateHourOptions());
		comboAterrizajeMinuto = new JComboBox<>(generateMinuteOptions());
		comboAterrizajeHora.setSelectedItem(String.format("%02d", startData.getTiempoAterrizaje().getHour()));
		comboAterrizajeMinuto.setSelectedItem(String.format("%02d", startData.getTiempoAterrizaje().getMinute() / 5 * 5));
		aterrizajePanel.add(comboAterrizajeHora);
		aterrizajePanel.add(new JLabel(":"));
		aterrizajePanel.add(comboAterrizajeMinuto);
		panel.add(aterrizajePanel, constraints);

		// Tipo
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(new JLabel("Tipo:"), constraints);
		constraints.gridx = 1;
		comboTipo = new JComboBox<>(new String[] { "Domestico", "Internacional" });
		comboTipo.setSelectedItem(startData.getTipoVuelo());
		panel.add(comboTipo, constraints);

		// VIP
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(new JLabel("VIP:"), constraints);
		constraints.gridx = 1;
		checkboxVip = new JCheckBox();
		checkboxVip.setSelected(startData.isVip());
		panel.add(checkboxVip, constraints);

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
				ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIActualizarVueloImp.this);
			}
		});

		actualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ACTUALIZAR_VUELO, GUIActualizarVueloImp.this);
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
	public String getVueloId() {
		return labelVueloID.getText().trim();
	}

	public String getAvionId() {
		return (String) comboAvionID.getSelectedItem();
	}

	public String getOrigen() {
		return textOrigen.getText().trim();
	}

	public String getDestino() {
		return textDestino.getText().trim();
	}

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

	public String getTipo() {
		return (String) comboTipo.getSelectedItem();
	}

	public boolean getVip() {
		return checkboxVip.isSelected();
	}
	
	public GUIModificarVueloImp getParent() {
		return (this.daddy);
	}

	@Override
	public void actualizar(Eventos evento, Object datos) {

	}

	public JFrame getFrame() {
		return (this.frame);
	}
}
