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
import java.awt.event.*;
import java.util.HashMap;

public class GUIFiltrarLocalesImp extends GUIFiltrarLocales {
	
	//Esto es lo que tenéis que cambiar (y el nombre de la clase)
	//---------------------------------
	String funcButtonName = "Filtrar"; //El texto que sale en el botón de la función
	String[] columnas = {"IDLocal", "Empresa", "Ingresos", "Gastos", "Fecha contrato"}; 
	String tituloTabla = "Información Local"; 
	String tituloPanel = "Filtrar Locales";
	int eventoFunc = Eventos.FILTRAR_LOCALES; //Tenéis que añadir el vuestro a la clase Eventos y al Controlador
	String descFunc = "Se Filtrarán los datos"; //Mensaje por defecto hasta implementar funcionalidad real
	//---------------------------------
	
	//Añadid también el evento VOLVER_MENU a la clase Eventos y al Controlador:
	//	case (Eventos.VOLVER_MENU): {
		//	GUIMenuCUsImp menu = (GUIMenuCUsImp) GUIMenuCUs.getInstancia();
			//JFrame menuFrame = menu.getFrame();
			//menuFrame.setVisible(true);
		//}
//	GUIMenuCUsImp es la clase con los botones para acceder a los menus de cada caso de uso 
	
	JFrame victoria;
	
	public GUIFiltrarLocalesImp()	{
		victoria = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));
		
		JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton cancelButton = new JButton("Cancelar");
		JButton acceptButton = new JButton("Aceptar");
		JButton funcButton = new JButton(funcButtonName);
		JButton returnButton = new JButton("Volver");
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(cancelButton);
		returnButtonPanel.add(returnButton);
		funcButtonPanel.add(funcButton);
		
		JScrollPane table = new JScrollPane(new JTable(new DefaultTableModel(columnas, 5)));
		
		JLabel title = new JLabel(tituloTabla);
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		panel.add(title);
		panel.add(Box.createHorizontalGlue());
		panel.add(funcButtonPanel);
		panel.add(table);
		panel.add(buttonsPanel);
		panel.add(returnButtonPanel);
		
		
		victoria.getContentPane().add(panel);
		victoria.setTitle(tituloPanel);
		victoria.pack();
		
					
		acceptButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				Controlador.getInstancia().accion(Eventos.ACEPTAR_CAMBIOS, null);
			}
		});
		
		cancelButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				Controlador.getInstancia().accion(Eventos.CANCELAR_CAMBIOS, null);
			}
		});
		
		funcButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, descFunc);
				Controlador.getInstancia().accion(eventoFunc, null); 
			}
		});
		
		returnButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				victoria.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
			}
		});
		
		
		victoria.setVisible(true);
	}
	
	
	//m�todo actualizar de la vista
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