package presentacion.equipajes.CUs;


import javax.swing.*;

import presentacion.equipajes.Controlador;
import presentacion.equipajes.Eventos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;


public class GUIMenuCUsImp extends GUIMenuCUs{
	JFrame marco;

	
	public GUIMenuCUsImp()	{
		marco = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));
		
		JLabel title = new JLabel("Menu Equipajes");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		
		JPanel buttonsPanel= new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));
		
		JButton salirButton = new JButton("Salir");
		JButton accederButton = new JButton("Acceder");
		
		buttonsPanel.add(accederButton);
		buttonsPanel.add(salirButton);
		
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Menu");
		marco.pack();
		
		
		accederButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				marco.setVisible(true);
				Controlador.getInstancia().accion(Eventos.ACCESO, null);
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
