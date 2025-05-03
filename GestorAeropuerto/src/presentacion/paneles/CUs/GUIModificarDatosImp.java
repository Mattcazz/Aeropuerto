package presentacion.paneles.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import negocio.InfoAeropuerto;
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
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Vector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIModificarDatosImp extends GUIModificarDatos {

	String funcButtonName = "Modificar";
	String[] columnas = {"Panel", "Vuelo", "Salida", "Llegada", "Terminal", "Puerta", "Origen", "Destino", "Avion", "Aerolinea" }; 
	DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
		 public boolean isCellEditable(int row, int column) {
            return false;
        }
	};
	JTable table = new JTable(modelo);
	String tituloTabla = "Información Paneles";
	String tituloPanel = "Modificar Datos";
	String descFunc = "Se modificarán los datos";
	int filaSeleccionada = -1;
	int columnaSeleccionada = -1;

	JFrame marco;
	
	JComboBox combobox = new JComboBox();

	public GUIModificarDatosImp() {
		
		table.getTableHeader().setReorderingAllowed(false);
		
		GUIAñadirDatos gui_añadir_datos = GUIAñadirDatos.getInstancia();
		((GUIAñadirDatosImp) gui_añadir_datos).getFrame().setVisible(false);
		modelo = gui_añadir_datos.get_modelo();
		table.setModel(modelo);
		gui_añadir_datos.addPropertyChangeListener(evt -> {
			if ("Modelo".equals(evt.getPropertyName())) {
				modelo = (DefaultTableModel) evt.getNewValue();
				table.setModel(modelo);
			}
		});
		
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(650, 350));

		JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton orderButton = new JButton("Ordenar por");
		JButton funcButton = new JButton(funcButtonName);
		JButton returnButton = new JButton("Volver");

		buttonsPanel.add(orderButton);
		buttonsPanel.add(combobox);
		returnButtonPanel.add(returnButton);
		funcButtonPanel.add(funcButton);
		
		JScrollPane table_frame = new JScrollPane(table);

		JLabel title = new JLabel(tituloTabla);
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(title);
		panel.add(Box.createHorizontalGlue());
		panel.add(funcButtonPanel);
		panel.add(table_frame);
		panel.add(buttonsPanel);
		panel.add(returnButtonPanel);

		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();

		create_combobox_columnas_info(combobox);	
		
		orderButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			}
		});

		funcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Eventos.MODIFICAR_DATOS, null);
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
           	 table.setRowSelectionAllowed(true);
             filaSeleccionada = table.rowAtPoint(e.getPoint());
             columnaSeleccionada = table.columnAtPoint(e.getPoint());
            }
                
        });

		marco.setVisible(true);
	}

	// m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.FUNCIONALIDAD): {
			if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
				marco.setVisible(false);
				GUIModificarDatosFormImp gui_aux = (GUIModificarDatosFormImp) GUIModificarDatosForm.getInstancia();
				JFrame gui_aux_frame = gui_aux.getFrame();
				gui_aux_frame.setVisible(true);
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			}
			else {
				Controlador.getInstancia().accion(Eventos.SIN_DATOS, GUIAñadirDatos.getInstancia());
			}
			break;
		}
		
		case (Eventos.MODIFICAR_DATOS_TEXTFIELD): {
			if (comprobar_datos(datos)) {
				modelo.setValueAt(datos, filaSeleccionada, columnaSeleccionada);
				filaSeleccionada = -1;
				columnaSeleccionada = -1;
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
				table.setModel(modelo);
			}
		}
		}
	};

	public JFrame getFrame() {
		return marco;
	}

	public DefaultTableModel get_modelo() {
		return modelo;
	}
	
	public void create_combobox_columnas_info(JComboBox columnas) {
		
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 0; i < modelo.getColumnCount(); i++) { 
			lista_opciones_columnas.add(modelo.getColumnName(i));
		}
		
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
	}
	
	public void sort(int index_columna) {
	    Vector<Vector> datos = modelo.getDataVector();
	    datos.sort((fila1, fila2) -> {
	        Object val1 = fila1.get(index_columna);
	        Object val2 = fila2.get(index_columna);

	        if (val1 instanceof Integer && val2 instanceof Integer) {
	            return Integer.compare((Integer) val1, (Integer) val2);
	        } else {
	            return val1.toString().compareToIgnoreCase(val2.toString());
	        }
	    });

	    modelo.fireTableDataChanged();
	}
	
	public Boolean comprobar_datos(Object datos) {
		Boolean b = true;
		
		if (columnaSeleccionada == 0) {
			GUIMostrarDatosImp menu = (GUIMostrarDatosImp) GUIMostrarDatos.getInstancia();
			JFrame menuFrame = menu.getFrame();
			menuFrame.setVisible(false);
			Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu); 
			 if (!(menu.id_paneles_existentes().contains(Integer.parseInt(String.valueOf(datos))))) {
				Controlador.getInstancia().accion(Eventos.ID_INEXISTENTE, null);
				b = false;
			 }
		}
		
		else if (columnaSeleccionada == 2 || columnaSeleccionada == 3) {
			try {
	            LocalTime.parse((CharSequence) datos); 
	        } catch (DateTimeParseException e) {
				Controlador.getInstancia().accion(Eventos.FORMATO_HORA_INCORRECTO, null);
	            b = false;;  // Si ocurre una excepción, no es un LocalTime válido
	        }
		}
		
		else if (columnaSeleccionada == 4 || columnaSeleccionada == 5) {
			try {
				Integer.parseInt((String) datos);
			} catch (NumberFormatException nfe) {
				Controlador.getInstancia().accion(Eventos.TIPO_INCORRECTO_AÑADIR_PANEL, null);
				b = false;
			}
			
			if (columnaSeleccionada == 4) {
				int i = 0; Boolean found = false; Integer[] terminales = InfoAeropuerto.terminales;
				while (i < terminales.length && !found) {
					if (datos == terminales[i]) {found = true;}
					else {i++;}
				}
				
				if (!found) {Controlador.getInstancia().accion(Eventos.TERMINAL_INEXISTENTE, null); b = false;}
			}
		}
		
		else if (columnaSeleccionada == 6 || columnaSeleccionada == 7) {
			try {
				Integer.parseInt((String) datos);
				Controlador.getInstancia().accion(Eventos.FORMATO_ORIGEN_DESTINO_INCORRECTO, null);
				b = false;
			} catch (NumberFormatException nfe) {
				if (datos.toString().length() != 3) {
					b = false;
					Controlador.getInstancia().accion(Eventos.FORMATO_ORIGEN_DESTINO_INCORRECTO, null);
				}
			}
		}
		
		else if (columnaSeleccionada == 9) {
			int i = 0; Boolean found = false; String[] aerolineas = InfoAeropuerto.aerolineas;
			while (i < aerolineas.length && !found) {
				if (datos.toString().toLowerCase().equals(aerolineas[i].toLowerCase())) {found = true;}
				else {i++;}
			}
			
			if (!found) {Controlador.getInstancia().accion(Eventos.AEROLINEA_INEXISTENTE, null); b = false;}
		}
		return b;
	}
}