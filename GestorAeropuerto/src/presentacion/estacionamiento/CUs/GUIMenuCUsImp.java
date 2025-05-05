package presentacion.estacionamiento.CUs;


import javax.swing.*;

import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;

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
		
		JLabel title = new JLabel("Estacionamiento");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		
		JPanel buttonsPanel= new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));
		
		JButton CrearPlazaButton = new JButton("Crear Plaza");
		JButton EliminarPlazaButton = new JButton("Eliminar Plaza");
		JButton ModificarPlazaButton = new JButton("Modificar Plaza");
		JButton ListaPlazasButton = new JButton("Lista Plazas");
		JButton AbandonaVehiculoButton = new JButton("Abandona Vehículo");
		JButton LlegaVehiculoButton = new JButton("Llega Vehículo");
		JButton MantenimientoPlazaButton = new JButton("Mantenimiento Plaza");
		JButton SalirButton = new JButton("Salir");
		
		buttonsPanel.add(CrearPlazaButton);
		buttonsPanel.add(EliminarPlazaButton);
		buttonsPanel.add(ModificarPlazaButton);
		buttonsPanel.add(ListaPlazasButton);
		buttonsPanel.add(AbandonaVehiculoButton);
		buttonsPanel.add(LlegaVehiculoButton);
		buttonsPanel.add(MantenimientoPlazaButton);
		buttonsPanel.add(SalirButton);
		
		panel.add(buttonsPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle("Estacionamiento");
		marco.pack();
		
		
		ListaPlazasButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTA_PLAZAS, null);
			}
		});
		
		AbandonaVehiculoButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIAbandonaVehiculo.getInstancia().mostrar();
			}
		});
		
		LlegaVehiculoButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUILlegaVehiculo.getInstancia().mostrar();
			}
		});
		
		MantenimientoPlazaButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIMantenimientoPlaza.getInstancia().mostrar();
			}
		});
		
		CrearPlazaButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUICrearPlaza.getInstancia().mostrar();
			}
		});
		
		EliminarPlazaButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIEliminarPlaza.getInstancia().mostrar();
			}
		});
		
		ModificarPlazaButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIModificarPlaza.getInstancia().mostrar();
			}
		});
		
		SalirButton.addActionListener(e -> {
        	marco.setVisible(false);
        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
		});
		
		marco.setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {}

	@Override
	public JFrame getFrame() {
		return (marco);
	}
	
}
