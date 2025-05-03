package presentacion.locales.CUs;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import presentacion.locales.Controlador;
import presentacion.locales.Eventos;

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

	JFrame victoria;
	
	public GUIMenuCUsImp()	{
		victoria = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));
		
		JLabel title = new JLabel("Menu");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		
		JPanel buttonsPanel= new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));
		
		JButton MostrarLocalesButton = new JButton("Mostrar Locales");
		JButton ModificarLocalesButton = new JButton("Modificar Locales");
		JButton AnadirLocalesButton = new JButton("Anadir Locales");
		JButton EliminarLocalesButton = new JButton("Eliminar Locales");
		//JButton FiltrarLocalesButton = new JButton("Filtar Locales"); //Menu bienvenida
		JButton SalirButton = new JButton("Salir");
		
		SalirButton.addActionListener(e -> {
        	victoria.setVisible(false);
        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
        });
		
		buttonsPanel.add(MostrarLocalesButton);
		buttonsPanel.add(ModificarLocalesButton);
		buttonsPanel.add(AnadirLocalesButton);
		buttonsPanel.add(EliminarLocalesButton);
		//buttonsPanel.add(FiltrarLocalesButton);
		buttonsPanel.add(SalirButton);
		
		panel.add(buttonsPanel);
		
		victoria.getContentPane().add(panel);
		victoria.setTitle("Menu");
		victoria.pack();
		
					
		MostrarLocalesButton.addActionListener(e -> {
		    GUIMostrarLocales gui = GUIMostrarLocales.getInstancia();
		    gui.mostrar();     
		    victoria.setVisible(false);
		});

		
		ModificarLocalesButton.addActionListener(e -> {
		    GUIModificarLocales gui = GUIModificarLocales.getInstancia();
		    gui.mostrar();  
		    victoria.setVisible(false);  // oculta el menú
		});


		AnadirLocalesButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				victoria.setVisible(false);
				GUIAnadirLocalesImp menu = (GUIAnadirLocalesImp) GUIAnadirLocales.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		EliminarLocalesButton.addActionListener(e -> {
		    GUIEliminarLocales gui = GUIEliminarLocales.getInstancia();
		    gui.mostrar(); // ✅ esto carga los datos automáticamente
		    victoria.setVisible(false);  // oculta el menú
		});

		
		/*FiltrarLocalesButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				victoria.setVisible(false);
				GUIFiltrarLocalesImp menu = (GUIFiltrarLocalesImp) GUIFiltrarLocales.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});*/
		
		
		
		
		victoria.setVisible(true);
	}
	
	
	//metodo actualizar de la vista
	public void actualizar(int evento, Object datos) {
//		switch (evento)		{
//		case (Eventos.CLIENTES_LIMPIAR): { 
//			                               campo3.setText(null);
//										   campo4.setText(null);
//										   victoria.setVisible(true);
//										  break;
//										 }
//		}
	};
	
	public JFrame getFrame() {
		return victoria;
	}
	
}