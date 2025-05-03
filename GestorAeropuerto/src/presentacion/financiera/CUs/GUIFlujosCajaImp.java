package presentacion.financiera.CUs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import negocio.financiera.TFlujoCaja;
import presentacion.financiera.Controlador;
import presentacion.financiera.Eventos;

public class GUIFlujosCajaImp extends GUIFlujosCaja {

	private JFrame marco;
	private JPanel panel_flujos;

	public GUIFlujosCajaImp() {
		inicializarGUI();
	}

	private void inicializarGUI() {
		marco = new JFrame("FLUJOS DE CAJA");
		marco.setSize(600, 500);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel title = new JLabel("FLUJOS DE CAJA", SwingConstants.CENTER);
		title.setFont(new Font("Dialog", Font.BOLD, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel_flujos = new JPanel();
		panel_flujos.setLayout(new BoxLayout(panel_flujos, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(panel_flujos);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(550, 300));

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton funcButton = new JButton("Registrar nuevo");
		JButton returnButton = new JButton("Volver");

		buttonPanel.add(funcButton);
		buttonPanel.add(returnButton);

		panel.add(title);
		panel.add(scrollPane);
		panel.add(buttonPanel);

		marco.getContentPane().add(panel);
		marco.setVisible(true);

		funcButton.addActionListener(e -> Controlador.getInstancia().accion(Eventos.REGISTRAR, null));
		returnButton.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Eventos.MOSTRAR_FLUJOS && datos instanceof List<?>) {
			panel_flujos.removeAll();

			List<?> lista = (List<?>) datos;
			for (Object obj : lista) {
				if (obj instanceof TFlujoCaja f) {
					panel_flujos.add(crearTarjetaFlujo(f));
				}
			}

			panel_flujos.revalidate();
			panel_flujos.repaint();
		}
		marco.setVisible(true);
	}

	private JPanel crearTarjetaFlujo(TFlujoCaja flujo) {
		JPanel tarjeta = new JPanel(new GridBagLayout());
		tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		tarjeta.setBackground(new Color(245, 245, 245));
		tarjeta.setPreferredSize(new Dimension(550, 120));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(2, 5, 2, 5);

		JLabel idLabel = new JLabel("ID: " + flujo.getId());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		tarjeta.add(idLabel, gbc);

		JLabel tipoLabel = new JLabel("<html><b>" + flujo.getTipo().toUpperCase() + "</b></html>");
		gbc.gridy = 1;
		tarjeta.add(tipoLabel, gbc);

		JLabel cuentaLabel = new JLabel("Cuenta: " + flujo.getNombreCuenta());
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		tarjeta.add(cuentaLabel, gbc);

		JLabel montoLabel = new JLabel("Monto: " + flujo.getCantidad() + " €");
		gbc.gridx = 1;
		tarjeta.add(montoLabel, gbc);

		JLabel fechaLabel = new JLabel("Fecha: " + flujo.getFecha());
		gbc.gridx = 0;
		gbc.gridy = 3;
		tarjeta.add(fechaLabel, gbc);

		JLabel estadoLabel = new JLabel("Estado: " + flujo.getEstado());
		gbc.gridx = 1;
		tarjeta.add(estadoLabel, gbc);

		JLabel conceptoLabel = new JLabel("<html><i>Concepto:</i> " + flujo.getConcepto() + "</html>");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		tarjeta.add(conceptoLabel, gbc);

		return tarjeta;
	}

	public JFrame getFrame() {
		return marco;
	}
}
