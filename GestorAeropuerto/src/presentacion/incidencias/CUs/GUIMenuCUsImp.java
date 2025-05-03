package presentacion.incidencias.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

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

	public GUIMenuCUsImp() {
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));

		JLabel title = new JLabel("Gestión de incidencias");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(title);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3, 10, 10));
		buttonsPanel.setPreferredSize(new Dimension(500, 250));

		JButton CrearIncidenciaButton = new JButton("Crear incidencia");
		JButton VisualizarButton= new JButton("Visualizar incidencias");
		JButton visualizarRegistroButton = new JButton("Visualizar registro");
		JButton BorrarIncidenciaButton = new JButton("Borrar incidencia");
		JButton SeleccionarSoluccionButton = new JButton("Seleccionar solucion");
		JButton SalirButton = new JButton("Salir");
		
		SalirButton.addActionListener(e -> {
        	marco.setVisible(false);
        	presentacion.Controlador.getInstancia().accion(presentacion.Eventos.SALIR, null);
        });

		buttonsPanel.add(CrearIncidenciaButton);
		buttonsPanel.add(VisualizarButton);
		buttonsPanel.add(visualizarRegistroButton);
		buttonsPanel.add(BorrarIncidenciaButton);
		buttonsPanel.add(SeleccionarSoluccionButton);
		buttonsPanel.add(SalirButton);
	

		
		panel.add(buttonsPanel);	
		marco.getContentPane().add(panel);
		marco.setTitle("Menu");
		marco.pack();

		CrearIncidenciaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				GUISeleccionarTipoIncidenciaImp menu = new GUISeleccionarTipoIncidenciaImp();
				menu.getFrame().setVisible(true);
			}
		});

		VisualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				GUISeleccionarTipoIncidenciaObservarImp menu = (GUISeleccionarTipoIncidenciaObservarImp) GUISeleccionarTipoIncidenciaObservar.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});

		visualizarRegistroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				GUIVisualizarRegistroImp menu = (GUIVisualizarRegistroImp) GUIVisualizarRegistro.getInstancia();
				Controlador.getInstancia().accion(Eventos.VISUALIZAR_REGISTRO, null);  ////
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});

		BorrarIncidenciaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				GUIBorrarIncidenciaImp menu = (GUIBorrarIncidenciaImp) GUIBorrarIncidencia.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});

		SeleccionarSoluccionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				GUISeleccionarTipoSolucionImp menu = (GUISeleccionarTipoSolucionImp) GUISeleccionarTipoSolucion.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		
		SalirButton.addActionListener(e->{
			this.marco.dispose();
		});

		marco.setVisible(true);
	}

	
	public void actualizar(int evento, Object datos) {
	};

	public JFrame getFrame() {
		return marco;
	}

}