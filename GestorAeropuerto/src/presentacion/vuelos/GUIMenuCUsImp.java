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
		
		JButton CrearVueloButton = new JButton("Crear Vuelo");
		JButton ActualizarVueloButton = new JButton("Actualizar Vuelo");
		JButton EliminarVueloButton = new JButton("Eliminar Vuelo");
		JButton BuscarVueloButton = new JButton("Buscar Vuelo");
		
		 JButton salirBtn = new JButton("Salir");
	     salirBtn.addActionListener(e -> {
	        	marco.setVisible(false);
	        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
	        });
		
		buttonsPanel.add(CrearVueloButton);
		buttonsPanel.add(ActualizarVueloButton);
		buttonsPanel.add(EliminarVueloButton);
		buttonsPanel.add(BuscarVueloButton);
		buttonsPanel.add(salirBtn);
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Menu");
		marco.pack();
		
					
		CrearVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_CREAR_VUELO, null);
			}
		});
		
		ActualizarVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_ACTUALIZAR_VUELO, null);
			}
		});
		
		EliminarVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_ELIMINAR_VUELO, null);
			}
		});
		
		BuscarVueloButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ControladorImp.getInstancia().accion(Eventos.ABRIR_MENU_BUSCAR_VUELO, null);
			}
		});	
		
		
		marco.setVisible(true);
	}
	
	
	//m�todo actualizar de la vista
	public void actualizar(Eventos evento, Object datos) {
//		switch (evento)		{
//		case (Eventos.CLIENTES_LIMPIAR): { 
//			                               campo3.setText(null);
//										   campo4.setText(null);
//										   marco.setVisible(true);
//										  break;
//										 }
//		}
	};
	
	public JFrame getFrame() {
		return marco;
	}

}
