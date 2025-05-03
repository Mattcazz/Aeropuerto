package presentacion.financiera.CUs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.financiera.TCuentaBancaria;
import presentacion.financiera.Controlador;
import presentacion.financiera.Eventos;

public class GUICuentasBancariasImp extends GUICuentasBancarias {

	private final String titulo_panel = "CUENTAS BANCARIAS";
	private final String[] columnas = { "IBAN", "Nombre", "Saldo", "Banco" };

	private JFrame marco;
	private JTable tabla;
	private DefaultTableModel modelo_tabla;

	public GUICuentasBancariasImp() {
		inicializarGUI();
	}

	private void inicializarGUI() {
		marco = new JFrame(titulo_panel);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(600, 300));

		// Título
		JLabel title = new JLabel(titulo_panel);
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Tabla
		modelo_tabla = new DefaultTableModel(columnas, 0); 
		tabla = new JTable(modelo_tabla);
		JScrollPane scrollPane = new JScrollPane(tabla);

		// Botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton aceptar = new JButton("Aceptar");
		JButton crear = new JButton("Crear cuenta");

		JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton volver = new JButton("Volver");

		buttonsPanel.add(aceptar);
		buttonsPanel.add(crear);
		volverPanel.add(volver);

		// Añadir componentes al panel principal
		panel.add(title);
		panel.add(scrollPane);
		panel.add(buttonsPanel);
		panel.add(volverPanel);

		marco.setContentPane(panel);
		marco.pack();
		marco.setLocationRelativeTo(null); // Centrar ventana
		marco.setVisible(true);

		// Listeners
		aceptar.addActionListener(e -> Controlador.getInstancia().accion(Eventos.ACEPTAR_CAMBIOS, null));

		crear.addActionListener(e -> Controlador.getInstancia().accion(Eventos.AÑADIR_CUENTA, null));

		volver.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
		});
	}

	
	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Eventos.MOSTRAR_CUENTAS && datos instanceof List<?>) {
			modelo_tabla.setRowCount(0); 
			List<?> lista = (List<?>) datos;
			for (Object obj : lista) {
				if (obj instanceof TCuentaBancaria cuenta) {
					Object[] fila = { cuenta.getIban(), cuenta.getNombre(), cuenta.getSaldo(), cuenta.getBanco() };
					modelo_tabla.addRow(fila);
				}
			}
		}
	}

	public void mostrar() {
		marco.setVisible(true);
	}

	public JFrame getFrame() {
		return marco;
	}
}
