package presentacion.vuelos;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;


public class GUIMenuCUsImp extends GUIMenuCUs {
	JFrame marco;
	
	public GUIMenuCUsImp()	{
		marco = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
//		panel.setPreferredSize(new Dimension(500, 250));
		
		JLabel title = new JLabel("Vuelos");
		title.setFont(new Font("SansSerif", Font.BOLD, 26));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		
		panel.add(title);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 15, 15));
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.setMaximumSize(new Dimension(600, 300));
		
		JButton crearVueloButton = this.createCUButton("Crear Vuelo");
		JButton modificarVueloButton = this.createCUButton("Modificar Vuelos");
		JButton buscarVueloButton = this.createCUButton("Buscar Vuelo");
		
		JButton crearAvionButton = this.createCUButton("Crear Avion");
		JButton modificarAvionButton = this.createCUButton("Modificar Aviones");
		
		JButton salirButton = this.createCUButton("Salir");
	    salirButton.addActionListener(e -> {
	       	marco.setVisible(false);
	       	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
	    });
		
		buttonsPanel.add(crearVueloButton);
		buttonsPanel.add(modificarVueloButton);
		buttonsPanel.add(buscarVueloButton);

		buttonsPanel.add(crearAvionButton);
		buttonsPanel.add(modificarAvionButton);
		buttonsPanel.add(new JPanel()).setBackground(Color.WHITE); // spacing

		buttonsPanel.add(new JPanel()).setBackground(Color.WHITE); // spacing
		buttonsPanel.add(salirButton);
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Vuelos");
		marco.pack();
		
					
		crearVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_CREAR_VUELO, null);
			}
		});
		
		modificarVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_MODIFICAR_VUELO, null);
			}
		});
		
		buscarVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_BUSCAR_VUELO, null);
			}
		});
		
		crearAvionButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_CREAR_AVION, null);
			}
		});
		
		modificarAvionButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_MODIFICAR_AVION, null);
			}
		});
		
		
		marco.setVisible(true);
	}
	
	private JButton createCUButton(String label) {
		JButton button = new JButton(label);
		button.setFocusPainted(false);
		
		return (button);
	}
	
	
	//m�todo actualizar de la vista
	public void actualizar(Eventos evento, Object datos) { };
	
	public JFrame getFrame() {
		return marco;
	}

}
