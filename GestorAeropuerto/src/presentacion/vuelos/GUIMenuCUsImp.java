package presentacion.vuelos;

import javax.swing.*;

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
		panel.setPreferredSize(new Dimension(500, 250));
		
		JLabel title = new JLabel("Menu");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));
		
		JButton crearVueloButton = new JButton("Crear Vuelo");
		JButton modificarVueloButton = new JButton("Modificar Vuelos");
		JButton buscarVueloButton = new JButton("Buscar Vuelo");
		
		JButton crearAvionButton = new JButton("Crear Avion");
		JButton modificarAvionButton = new JButton("Modificar Aviones");
		
		 JButton salirBtn = new JButton("Salir");
	     salirBtn.addActionListener(e -> {
	        	marco.setVisible(false);
	        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
	        });
		
		buttonsPanel.add(crearVueloButton);
		buttonsPanel.add(modificarVueloButton);
		buttonsPanel.add(buscarVueloButton);

		buttonsPanel.add(crearAvionButton);
		buttonsPanel.add(modificarAvionButton);

		buttonsPanel.add(salirBtn);
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Menu");
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
	
	
	//m�todo actualizar de la vista
	public void actualizar(Eventos evento, Object datos) { };
	
	public JFrame getFrame() {
		return marco;
	}

}
