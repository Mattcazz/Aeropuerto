package presentacion.personal.CUs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.personal.Controlador;
import presentacion.personal.Eventos;

public class GUIMenuCUs {

		JFrame marco;
		
		public GUIMenuCUs()	{
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
			
			JButton AsignacionButton = new JButton("Asignar Rol/Turno/Función");
			JButton MostrarListaButton = new JButton("Mostrar Lista Empleados");
			JButton CrearNominaButton = new JButton("Crear Nómina");
			JButton AnadirEmpleadosButton = new JButton("Añadir Empleados");
			JButton EliminarEmpleadosButton = new JButton("Eliminar Empleados");
			JButton GenerarInformeButton = new JButton("Generar Informe");
			
			 JButton salirBtn = new JButton("Salir");
		     salirBtn.addActionListener(e -> {
		        	marco.setVisible(false);
		        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
		        });

			buttonsPanel.add(AsignacionButton);
			buttonsPanel.add(MostrarListaButton);
			buttonsPanel.add(AnadirEmpleadosButton);
			buttonsPanel.add(EliminarEmpleadosButton);
			buttonsPanel.add(CrearNominaButton);
			buttonsPanel.add(GenerarInformeButton);
			buttonsPanel.add(salirBtn);
		
			AsignacionButton.setFocusPainted(false);
			
			panel.add(buttonsPanel);

			marco.getContentPane().add(panel);
			marco.setTitle("Menu");
			marco.pack();
			
						
			AsignacionButton.addActionListener(new ActionListener()	{ 
				public void actionPerformed(ActionEvent e){
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VISTA_ASIGNACION, null);
				}
			});
			
			MostrarListaButton.addActionListener(new ActionListener()	{ 
				public void actionPerformed(ActionEvent e){
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VISTA_MOSTRARLISTA, null);
				}
			});

			CrearNominaButton.addActionListener(new ActionListener()	{ 
				public void actionPerformed(ActionEvent e){
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VISTA_CREARNOMINA, null);
				}
			});
			AnadirEmpleadosButton.addActionListener(new ActionListener()	{ 
				public void actionPerformed(ActionEvent e){
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VISTA_ANADIR, null);
				}
			});
			
			EliminarEmpleadosButton.addActionListener(new ActionListener()	{ 
				public void actionPerformed(ActionEvent e){
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VISTA_ELIMINAR, null);
				}
			});
			
			GenerarInformeButton.addActionListener(new ActionListener()	{ 
				public void actionPerformed(ActionEvent e){
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VISTA_GENERARINFORME, null);
					
				}
			});
			marco.setLocationRelativeTo(null);
			marco.setVisible(true);
		}
		
		
		public void actualizar(int evento, Object datos) {};
		
		public JFrame getFrame() {
			return marco;
		}
		
}
