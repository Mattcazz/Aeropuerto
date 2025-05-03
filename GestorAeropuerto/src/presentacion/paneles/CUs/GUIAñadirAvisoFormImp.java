package presentacion.paneles.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import negocio.paneles.TransferInfoVuelos;
import presentacion.paneles.Controlador;
import presentacion.paneles.Eventos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIAñadirAvisoFormImp extends GUIAñadirAvisoForm {

	String funcButtonName = "Añadir";
	String tituloPanel = "Añadir Aviso";

	JFrame marco;

	public GUIAñadirAvisoFormImp() {
				
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(400, 115));
		
		JLabel desc = new JLabel("Introduce el nuevo aviso");
		JTextField textField = new JTextField(20);

		JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton funcButton = new JButton(funcButtonName);
		JButton returnButton = new JButton("Volver");

		returnButtonPanel.add(returnButton);
		funcButtonPanel.add(desc);
		funcButtonPanel.add(textField);
		funcButtonPanel.add(funcButton);
		
		panel.add(Box.createHorizontalGlue());
		panel.add(funcButtonPanel);
		panel.add(returnButtonPanel);

		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();

		funcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				GUIOperarPanelesImp gui_aux = (GUIOperarPanelesImp) GUIOperarPaneles.getInstancia();
				JFrame gui_aux_frame = gui_aux.getFrame();
				gui_aux_frame.setVisible(true);
				
				Object datos = new Object[] {gui_aux.getSelectedRowPanelId(), textField.getText()};
				
				Controlador.getInstancia().accion(Eventos.AÑADIR_AVISO, datos);
				Controlador.getInstancia().accion(Eventos.ABRIR_MENU, GUIOperarPaneles.getInstancia());
				
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, GUIOperarPaneles.getInstancia());
			}
		});

		marco.setVisible(true);
	}

	// m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.FUNCIONALIDAD): {
			break;
		}
		}
	};

	public JFrame getFrame() {
		return marco;
	}
}