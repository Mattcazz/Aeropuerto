package presentacion;

import javax.swing.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;


public class GUIMenuSubsistemasImp extends GUIMenuSubsistemas {
	JFrame marco;
	
	public GUIMenuSubsistemasImp()	{
		marco = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));
		
		JLabel title = new JLabel("Menu");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));
		
		JButton PanelesButton = new JButton("Paneles");
		JButton PersonalButton = new JButton("Personal");
		JButton EquipajeButton = new JButton("Equipaje");
		JButton OperacionesButton = new JButton("Operaciones");
		JButton FinancieraButton = new JButton("Financiera");
		JButton IncidenciasButton = new JButton("Incidencias");
		JButton LocalesButton = new JButton("Locales");
		JButton EstacionamientoButton = new JButton("Estacionamiento");
		JButton VuelosButton = new JButton("Vuelos");
		JButton SeguridadButton = new JButton("Seguridad");
		
		buttonsPanel.add(PanelesButton);
		buttonsPanel.add(PersonalButton);
		buttonsPanel.add(EquipajeButton);
		buttonsPanel.add(OperacionesButton);
		buttonsPanel.add(FinancieraButton);
		buttonsPanel.add(IncidenciasButton);
		buttonsPanel.add(LocalesButton);
		buttonsPanel.add(EstacionamientoButton);
		buttonsPanel.add(VuelosButton);
		buttonsPanel.add(SeguridadButton);
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Menu");
		marco.pack();
		
					
		PanelesButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_PANELES, marco);
			}
		});
		PersonalButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_PERSONAL, marco);
			}
		});
		EquipajeButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_EQUIPAJE, marco);
			}
		});
		OperacionesButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_OPERACIONES, marco);
			}
		});
		FinancieraButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_FINANCIERA, marco);
			}
		});
		IncidenciasButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_INCIDENCIAS, marco);
			}
		});
		LocalesButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_LOCALES, marco);
			}
		});
		EstacionamientoButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_ESTACIONAMIENTO, marco);
			}
		});
		VuelosButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_VUELOS, marco);
			}
		});
		SeguridadButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.SUB_SEGURIDAD, marco);
			}
		});

		
		marco.setVisible(true);
	}

	public void actualizar(int evento, Object datos) {
		
	};
	
	public JFrame getFrame() {
		return marco;
	}

}
