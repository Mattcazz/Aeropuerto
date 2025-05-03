package presentacion.financiera.CUs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.financiera.Controlador;
import presentacion.financiera.Eventos;

public class GUIMenuCUsImp extends GUIMenuCUs {

	JFrame marco;

	public GUIMenuCUsImp() {
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));

		JLabel title = new JLabel("Gestión financiera");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalStrut(15));
		panel.add(title);
		panel.add(Box.createVerticalStrut(15));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));

		JButton CuentasBancariasButton = new JButton("Cuentas bancarias");
		JButton FlujosCajaButton = new JButton("Flujos de caja");
		JButton RealizarOperacionButton = new JButton("Realizar operación");
		JButton ActualizarSubsistemasButton = new JButton("Actualizar flujos desde subsistemas");
		JButton SalirButton = new JButton("Salir");

		buttonsPanel.add(CuentasBancariasButton);
		buttonsPanel.add(RealizarOperacionButton);
		buttonsPanel.add(FlujosCajaButton);
		buttonsPanel.add(ActualizarSubsistemasButton);
		buttonsPanel.add(SalirButton);
		
		SalirButton.addActionListener(e -> {
	        	marco.setVisible(false);
	        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
	        });

		panel.add(buttonsPanel);

		marco.getContentPane().add(panel);
		marco.setTitle("FINANZAS");
		marco.pack();

		CuentasBancariasButton.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.MOSTRAR_CUENTAS, null);
		});

		FlujosCajaButton.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.MOSTRAR_FLUJOS, null);
		});

		RealizarOperacionButton.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.REGISTRAR, null);
		});

		ActualizarSubsistemasButton.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.INTEGRAR_SUBSISTEMAS, null);
		});

		SalirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
			}
		});

		marco.setVisible(true);
		marco.setLocationRelativeTo(null);
	}

	public void actualizar(int evento, Object datos) {
	};

	public JFrame getFrame() {
		return marco;
	}

}