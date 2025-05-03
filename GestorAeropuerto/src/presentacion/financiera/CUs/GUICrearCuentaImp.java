package presentacion.financiera.CUs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import negocio.financiera.TCuentaBancaria;
import negocio.financiera.TCuentaBancariaBuilder;
import presentacion.financiera.Controlador;
import presentacion.financiera.Eventos;

public class GUICrearCuentaImp extends GUICrearCuenta {

	private JFrame marco;
	private JTextField campo_IBAN;
	private JTextField campo_nombre;
	private JTextField campo_banco;
	private JSpinner spinner_saldo;

	public GUICrearCuentaImp() {
		inicializarGUI();
	}

	private void inicializarGUI() {
		marco = new JFrame("CREAR CUENTA BANCARIA");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		panel.setPreferredSize(new Dimension(500, 300));

		JLabel title = new JLabel("CREAR CUENTA BANCARIA");
		title.setFont(new Font("Dialog", Font.BOLD, 22));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
		campo_IBAN = new JTextField();
		campo_nombre = new JTextField();
		campo_banco = new JTextField();
		spinner_saldo = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10000000.0, 100.0));

		formulario.add(new JLabel("IBAN:"));
		formulario.add(campo_IBAN);
		formulario.add(new JLabel("Nombre de cuenta:"));
		formulario.add(campo_nombre);
		formulario.add(new JLabel("Banco:"));
		formulario.add(campo_banco);
		formulario.add(new JLabel("Saldo inicial:"));
		formulario.add(spinner_saldo);

		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		JButton volver = new JButton("Volver");

		botones.add(aceptar);
		botones.add(cancelar);
		botones.add(volver);

		aceptar.addActionListener((ActionEvent e) -> {
			String iban = campo_IBAN.getText().trim();
			String nombre = campo_nombre.getText().trim();
			String banco = campo_banco.getText().trim();
			float saldo = ((Double) spinner_saldo.getValue()).floatValue();

			if (iban.isEmpty() || nombre.isEmpty()) {
				JOptionPane.showMessageDialog(marco, "IBAN y Nombre son obligatorios.");
				return;
			}

			try {
				TCuentaBancaria cuenta = new TCuentaBancariaBuilder().conIban(iban).conNombre(nombre).conBanco(banco)
						.conSaldo(saldo).build();
				Controlador.getInstancia().accion(Eventos.CREAR_CUENTA, cuenta);
				JOptionPane.showMessageDialog(marco, "Cuenta creada correctamente.");
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.MOSTRAR_CUENTAS, null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(marco, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelar.addActionListener(e -> {
			campo_IBAN.setText("");
			campo_nombre.setText("");
			campo_banco.setText("");
			spinner_saldo.setValue(0.0);
		});

		volver.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
		});

		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(formulario);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(botones);

		marco.getContentPane().add(panel);
		marco.pack();
		marco.setLocationRelativeTo(null);
		marco.setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
	}

	public JFrame getFrame() {
		return marco;
	}
}
