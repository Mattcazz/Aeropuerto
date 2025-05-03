package presentacion.paneles.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import negocio.InfoAeropuerto;
import negocio.paneles.TransferInfoVuelos;
import presentacion.paneles.Controlador;
import presentacion.paneles.Eventos;
import negocio.paneles.TransferPaneles;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.HashMap;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIAñadirPanelFormImp extends GUIAñadirPanelForm {

	String funcButtonName = "Añadir";
	String tituloPanel = "Añadir Panel";
	
	String[] columnas = {"ID", "Nº Columnas", "Nº Lineas", "Terminal"}; 
	DefaultTableModel modelo = new DefaultTableModel(columnas, 1);
	JTable table = new JTable(modelo);
	String tituloTabla = "Nuevo Panel";

	JFrame marco;

	public GUIAñadirPanelFormImp() {
				
		table.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 150));

		JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JScrollPane table_frame = new JScrollPane(table);
		JButton funcButton = new JButton(funcButtonName);
		JButton returnButton = new JButton("Volver");

		returnButtonPanel.add(returnButton);
		funcButtonPanel.add(funcButton);
		
		JLabel title = new JLabel(tituloTabla);
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		panel.add(title);
		panel.add(Box.createHorizontalGlue());
		panel.add(funcButtonPanel);
		panel.add(table_frame);
		panel.add(returnButtonPanel);

		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();

		funcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean empty = false;
				for (int i = 0; i < modelo.getColumnCount(); i++) {
					if (modelo.getValueAt(0, i) == null) {empty = true;}
				}
				if (!empty) {
					marco.setVisible(false);					
					TransferPaneles transfer = new TransferPaneles();
					
					try {
						
						int id = Integer.parseInt((String) modelo.getValueAt(0, 0));
						int rows = Integer.parseInt((String) modelo.getValueAt(0, 1));
						int cols = Integer.parseInt((String) modelo.getValueAt(0, 2));
						int terminal = Integer.parseInt((String) modelo.getValueAt(0, 3));
						
						int i = 0; Boolean found = false; Integer[] terminales = InfoAeropuerto.terminales;
						while (i < terminales.length && !found) {
							if (terminal == terminales[i]) {found = true;}
							else {i++;}
						}
						
						if (found) {
							if (id >= 0 && rows >= 0 && cols >= 0) {
								transfer.setId(id);
								transfer.setN_columnas(rows);
								transfer.setN_lineas(cols);
								transfer.setTerminal(terminal);
								transfer.setEncendido(0);
								transfer.setAviso("");
								transfer.setTieneAviso(0);
								
								Controlador.getInstancia().accion(Eventos.AÑADIR_PANEL, transfer);
							}
							else {
								Controlador.getInstancia().accion(Eventos.DATOS_NEGATIVOS, transfer);

							}
						}
						else {
							Controlador.getInstancia().accion(Eventos.TERMINAL_INEXISTENTE, null);
						}
						
					} catch (NumberFormatException nfe) {
						Controlador.getInstancia().accion(Eventos.TIPO_INCORRECTO_AÑADIR_PANEL, null);
					}
					finally {
						modelo.setNumRows(0);
						modelo.setNumRows(1);
						Controlador.getInstancia().accion(Eventos.ABRIR_MENU, GUIAñadirPanel.getInstancia());
						GUIAñadirPanelImp gui_aux = (GUIAñadirPanelImp) GUIAñadirPanel.getInstancia();
						JFrame gui_aux_frame = gui_aux.getFrame();
						gui_aux_frame.setVisible(true);
					}
				}
				else {
					Controlador.getInstancia().accion(Eventos.CAMPOS_VACIOS, null);
				}
				
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, GUIAñadirPanel.getInstancia());
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