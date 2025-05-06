package presentacion.paneles.CUs;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import presentacion.paneles.Controlador;
import presentacion.paneles.Eventos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.HashMap;

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
		
		JPanel buttonsPanel= new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));
		
		JButton AñadirDatosButton = new JButton("Añadir Datos");
		JButton EliminarDatosButton = new JButton("Eliminar Datos");
		JButton ModificarDatosButton = new JButton("Modificar Datos");
		JButton MostrarDatosButton = new JButton("Mostrar Datos");
		JButton FiltrarDatosButton = new JButton("Filtrar Datos");
		JButton AñadirPanelButton = new JButton("Añadir Panel");
		JButton EliminarPanelButton = new JButton("Eliminar Panel");
		JButton OperarPanelesButton = new JButton("Operar Panel");
		JButton SalirButton = new JButton("Salir");
		
		buttonsPanel.add(AñadirPanelButton);
		buttonsPanel.add(EliminarPanelButton);
		buttonsPanel.add(OperarPanelesButton);
		buttonsPanel.add(AñadirDatosButton);
		buttonsPanel.add(EliminarDatosButton);
		buttonsPanel.add(ModificarDatosButton);
		buttonsPanel.add(MostrarDatosButton);
		buttonsPanel.add(FiltrarDatosButton);
		buttonsPanel.add(SalirButton);
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Menu");
		marco.pack();
		
					
		AñadirDatosButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIAñadirDatosImp menu = (GUIAñadirDatosImp) GUIAñadirDatos.getInstancia();
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu); 
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		FiltrarDatosButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIFiltrarDatosImp menu = (GUIFiltrarDatosImp) GUIFiltrarDatos.getInstancia();
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu); 
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});

		EliminarDatosButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIEliminarDatosImp menu = (GUIEliminarDatosImp) GUIEliminarDatos.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		ModificarDatosButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIModificarDatosImp menu = (GUIModificarDatosImp) GUIModificarDatos.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		MostrarDatosButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIMostrarDatosImp menu = (GUIMostrarDatosImp) GUIMostrarDatos.getInstancia();
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu); 
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		AñadirPanelButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIAñadirPanelImp menu = (GUIAñadirPanelImp) GUIAñadirPanel.getInstancia();
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu); 
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		OperarPanelesButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIOperarPanelesImp menu = (GUIOperarPanelesImp) GUIOperarPaneles.getInstancia();
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu);
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		EliminarPanelButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIEliminarPanelImp menu = (GUIEliminarPanelImp) GUIEliminarPanel.getInstancia();
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu);
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		SalirButton.addActionListener(e -> {
		        	marco.setVisible(false);
		        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
		});
		
		marco.setVisible(true);
	}
	
	
	//m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
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