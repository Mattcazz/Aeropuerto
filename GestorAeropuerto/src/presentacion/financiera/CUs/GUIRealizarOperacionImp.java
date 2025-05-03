package presentacion.financiera.CUs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import negocio.financiera.FactoriaSA;
import negocio.financiera.TCuentaBancaria;
import negocio.financiera.TFlujoCaja;
import negocio.financiera.TFlujoCajaBuilder;
import negocio.financiera.SACuentaBancaria;
import negocio.financiera.SAFlujoCaja;
import presentacion.financiera.Controlador;
import presentacion.financiera.Eventos;

public class GUIRealizarOperacionImp extends GUIRealizarOperacion {

	private JFrame marco;
	private JComboBox<String> tipo_operacion;
	private JComboBox<String> cuenta_bancaria;
	private JComboBox<String> combobox_pendientes;
	private JSpinner spinner_monto;
	private JTextField campo_concepto;

	private List<TFlujoCaja> lista_pendientes;

	private final SACuentaBancaria sa_cuenta_bancaria = FactoriaSA.getInstancia().createSACuentaBancaria();
	private final SAFlujoCaja sa_flujo_caja = FactoriaSA.getInstancia().createSAFlujoCaja();

	public GUIRealizarOperacionImp() {
		inicializarGUI();
	}

	private void inicializarGUI() {
		marco = new JFrame("REALIZAR OPERACIÓN");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		panel.setPreferredSize(new Dimension(650, 400));

		JLabel title = new JLabel("REALIZAR OPERACIÓN");
		title.setFont(new Font("Dialog", Font.BOLD, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Panel de operación manual
		JPanel datosPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		tipo_operacion = new JComboBox<>(new String[] { "ingreso", "gasto" });
		cuenta_bancaria = new JComboBox<>();

		try {
			List<TCuentaBancaria> cuentas = sa_cuenta_bancaria.listarCuentas();
			for (TCuentaBancaria c : cuentas) {
				cuenta_bancaria.addItem(c.getNombre());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(marco, "Error cargando cuentas: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		spinner_monto = new JSpinner(new SpinnerNumberModel(100.0, 0.01, 10000000.0, 1.0));
		campo_concepto = new JTextField();

		datosPanel.add(new JLabel("Tipo de operación"));
		datosPanel.add(tipo_operacion);
		datosPanel.add(new JLabel("Cuenta bancaria"));
		datosPanel.add(cuenta_bancaria);
		datosPanel.add(new JLabel("Monto (€)"));
		datosPanel.add(spinner_monto);
		datosPanel.add(new JLabel("Concepto"));
		datosPanel.add(campo_concepto);

		// Flujos pendientes
		JPanel pendientesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		combobox_pendientes = new JComboBox<>();
		JButton realizarPendiente = new JButton("Completar flujo pendiente");
		pendientesPanel.add(new JLabel("Flujos pendientes:"));
		pendientesPanel.add(combobox_pendientes);
		pendientesPanel.add(realizarPendiente);

		// Botones generales
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		JButton volver = new JButton("Volver");
		botones.add(aceptar);
		botones.add(cancelar);
		botones.add(volver);

		aceptar.addActionListener((ActionEvent e) -> {
			try {
				String tipo = tipo_operacion.getSelectedItem().toString();
				String cuenta = cuenta_bancaria.getSelectedItem().toString();
				float monto = ((Double) spinner_monto.getValue()).floatValue();
				String concepto = campo_concepto.getText().trim();

				if (concepto.isEmpty()) {
					JOptionPane.showMessageDialog(marco, "Por favor, introduce un concepto.");
					return;
				}

				TFlujoCaja flujo = new TFlujoCajaBuilder().conNombreCuenta(cuenta).conTipo(tipo).conCantidad(monto)
						.conConcepto(concepto).conEstado("completado").build();

				Controlador.getInstancia().accion(Eventos.REALIZAR_FLUJO, flujo);

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(marco, "Operación incompleta: " + ex.getMessage(), "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		realizarPendiente.addActionListener(e -> {
			int index = combobox_pendientes.getSelectedIndex();
			if (index >= 0 && index < lista_pendientes.size()) {
				TFlujoCaja flujo = lista_pendientes.get(index);
				try {
					Controlador.getInstancia().accion(Eventos.COMPLETAR_FLUJO_PENDIENTE, flujo.getId());

					cargarFlujosPendientes(); // Refrescar lista
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(marco, "Operación incompleta: " + ex.getMessage(), "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		cancelar.addActionListener(e -> {
			campo_concepto.setText("");
			spinner_monto.setValue(100.0);
		});

		volver.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
		});

		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(datosPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(pendientesPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(botones);

		marco.getContentPane().add(panel);
		marco.pack();
		marco.setLocationRelativeTo(null);
		marco.setVisible(true);

		cargarFlujosPendientes();

	}

	private void cargarFlujosPendientes() {
		combobox_pendientes.removeAllItems();
		try {
			lista_pendientes = sa_flujo_caja.listarPendientes();
			for (TFlujoCaja f : lista_pendientes) {
				combobox_pendientes.addItem("ID: " + f.getId() + " | " + f.getConcepto() + ", " + f.getTipo() + " de "
						+ f.getCantidad() + " €" + " el " + f.getFecha());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(marco, "Error cargando flujos pendientes: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
	}

	public JFrame getFrame() {
		return marco;
	}
}
