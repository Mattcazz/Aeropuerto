package presentacion.paneles.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import negocio.paneles.TransferInfoVuelos;
import negocio.paneles.TransferPaneles;
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
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.HashSet;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIFiltrarDatosImp extends GUIFiltrarDatos {

	String funcButtonName = "Filtrar";
	
	String[] columnas_paneles = {"ID", "Encendido", "Nº Columnas", "Nº Lineas", "Terminal", "Aviso", "Mensaje"};
	DefaultTableModel modelo_paneles = new DefaultTableModel(columnas_paneles, 0) {
		 public boolean isCellEditable(int row, int column) {
            return false;
        }
	};
	JTable table_paneles = new JTable(modelo_paneles);
	String tituloTablaPaneles = "Paneles";
	
	String[] columnas_info = {"Panel", "Vuelo", "Salida", "Llegada", "Terminal", "Puerta", "Origen", "Destino", "Avion", "Aerolinea" }; 
	DefaultTableModel modelo_info = new DefaultTableModel(columnas_info, 0) {
		 public boolean isCellEditable(int row, int column) {
            return false;
        }
	};
	JTable table_info = new JTable(modelo_info);
	String tituloTablaInfo = "Información Paneles";
	
	String tituloPanel = "Filtrar Datos";
	int filaSeleccionada = -1;

	JFrame marco;
	
	
	DefaultTableModel modelo_paneles_aux =  new DefaultTableModel(columnas_paneles, 0);
	DefaultTableModel modelo_info_aux;
	
	JComboBox combobox_paneles1 = new JComboBox();
	JComboBox combobox_info1 = new JComboBox();
	JComboBox combobox_paneles2 = new JComboBox();
	JComboBox combobox_info2 = new JComboBox();
	JComboBox combobox_paneles3 = new JComboBox();
	JComboBox combobox_info3 = new JComboBox();
	
	JComboBox combobox_orden_paneles = new JComboBox();
	JComboBox combobox_orden_info = new JComboBox();

	public GUIFiltrarDatosImp() {
		
		GUIAñadirDatos gui_añadir_datos = GUIAñadirDatos.getInstancia();
		((GUIAñadirDatosImp) gui_añadir_datos).getFrame().setVisible(false);
		modelo_info_aux = gui_añadir_datos.get_modelo();
		gui_añadir_datos.addPropertyChangeListener(evt -> {
			if ("Modelo".equals(evt.getPropertyName())) {
				modelo_info_aux = (DefaultTableModel) evt.getNewValue();
			}
		});
		
		combobox_paneles1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    			create_combobox_comparacion_paneles(combobox_paneles2, (String) combobox_paneles1.getSelectedItem());
    			create_combobox_filas_paneles(combobox_paneles3, (String) combobox_paneles1.getSelectedItem());
            }
        });
		
		combobox_info1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				create_combobox_comparacion_info(combobox_info2, (String) combobox_info1.getSelectedItem());
				create_combobox_filas_info(combobox_info3, (String) combobox_info1.getSelectedItem());
			}
		});
		
		table_paneles.getTableHeader().setReorderingAllowed(false);
		table_info.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(650, 400));

		JPanel funcButtonPanelesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel funcButtonInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanelInfo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanelPaneles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton orderButtonPaneles = new JButton("Ordenar por");
		JButton orderButtonInfo = new JButton("Ordenar por");
		JButton funcButtonPaneles = new JButton(funcButtonName);
		JButton funcButtonInfo = new JButton(funcButtonName);
		JButton returnButton = new JButton("Volver");

		buttonsPanelPaneles.add(orderButtonPaneles);
		buttonsPanelInfo.add(orderButtonInfo);
		buttonsPanelPaneles.add(combobox_orden_paneles);
		buttonsPanelInfo.add(combobox_orden_info);
		returnButtonPanel.add(returnButton);
		funcButtonPanelesPanel.add(funcButtonPaneles);
		funcButtonPanelesPanel.add(combobox_paneles1);
		funcButtonPanelesPanel.add(combobox_paneles2);
		funcButtonPanelesPanel.add(combobox_paneles3);
		funcButtonInfoPanel.add(funcButtonInfo);
		funcButtonInfoPanel.add(combobox_info1);
		funcButtonInfoPanel.add(combobox_info2);
		funcButtonInfoPanel.add(combobox_info3);
		
		JScrollPane table_paneles_frame = new JScrollPane(table_paneles);

		JLabel title_paneles = new JLabel(tituloTablaPaneles);
		title_paneles.setFont(new Font("Dialog", Font.PLAIN, 24));
		title_paneles.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title_paneles);
		panel.add(Box.createHorizontalGlue());
		panel.add(funcButtonPanelesPanel);
		panel.add(table_paneles_frame);
		panel.add(buttonsPanelPaneles);

		JScrollPane table_info_frame = new JScrollPane(table_info);

		JLabel title_info = new JLabel(tituloTablaInfo);
		title_info.setFont(new Font("Dialog", Font.PLAIN, 24));
		title_info.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(title_info);
		panel.add(Box.createHorizontalGlue());
		panel.add(funcButtonInfoPanel);
		panel.add(table_info_frame);
		panel.add(returnButtonPanel);
		
		panel.add(buttonsPanelInfo);
		panel.add(returnButtonPanel);
		

		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();

		create_combobox_columnas_paneles_order(combobox_orden_paneles);	
		
		orderButtonPaneles.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				sort_paneles(Integer.parseInt(String.valueOf(table_paneles.getColumnModel().getColumnIndex(combobox_orden_paneles.getSelectedItem()))));
			}
		});
		
		create_combobox_columnas_info(combobox_orden_info);	
		
		orderButtonInfo.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				sort_info(Integer.parseInt(String.valueOf(table_info.getColumnModel().getColumnIndex(combobox_orden_info.getSelectedItem()))));
			}
		});

		funcButtonPaneles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar(modelo_paneles_aux, modelo_paneles, combobox_paneles1, combobox_paneles2, combobox_paneles3);
			}
		});
		
		funcButtonInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar(modelo_info_aux, modelo_info, combobox_info1, combobox_info2, combobox_info3);
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
			}
		});

		marco.setVisible(true);
	}

	// m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		
		case (Eventos.ABRIR_MENU): { 
			modelo_paneles_aux.setRowCount(0);
			for (TransferPaneles transfer: (List<TransferPaneles>) datos) {
				modelo_paneles_aux.addRow(new Object[] {
						transfer.getId(),
						transfer.getEncendido() != 0 ? "Si":"No",
						transfer.getN_columnas(),
						transfer.getN_lineas(),
						transfer.getTerminal(),
						transfer.getTieneAviso() != 0 ? "Si":"No",
						transfer.getAviso()
				});
			}
			
			sort_paneles(Integer.parseInt(String.valueOf(table_paneles.getColumnModel().getColumnIndex(combobox_orden_paneles.getSelectedItem()))));
			
			create_combobox_columnas_paneles(combobox_paneles1);
			create_combobox_comparacion_paneles(combobox_paneles2, (String) combobox_paneles1.getSelectedItem());
			create_combobox_filas_paneles(combobox_paneles3, (String) combobox_paneles1.getSelectedItem());
			
			create_combobox_columnas_info(combobox_info1);
			create_combobox_comparacion_info(combobox_info2, (String) combobox_info1.getSelectedItem());
			create_combobox_filas_info(combobox_info3, (String) combobox_info1.getSelectedItem());
			
			break;
		}
		}
	};

	public JFrame getFrame() {
		return marco;
	}
	
	public void sort_paneles(int index_columna) {
		Vector<Vector> datos = modelo_paneles.getDataVector();
		datos.sort(Comparator.comparingInt(fila -> Integer.parseInt(fila.get(index_columna).toString())));
		modelo_paneles.fireTableDataChanged();
	}
	
	public void sort_info(int index_columna) {
	    Vector<Vector> datos = modelo_info.getDataVector();
	    datos.sort((fila1, fila2) -> {
	        Object val1 = fila1.get(index_columna);
	        Object val2 = fila2.get(index_columna);

	        if (val1 instanceof Integer && val2 instanceof Integer) {
	            return Integer.compare((Integer) val1, (Integer) val2);
	        } else {
	            return val1.toString().compareToIgnoreCase(val2.toString());
	        }
	    });

	    modelo_info.fireTableDataChanged();
	}
	
	public void create_combobox_columnas_paneles_order(JComboBox columnas) {
		
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 0; i < modelo_paneles.getColumnCount(); i++) {
			if (i != 1 && i != 5 && i != 6) {lista_opciones_columnas.add(modelo_paneles.getColumnName(i));}//Ni por los bools ni por mensaje
		}
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
	}
	
	public void create_combobox_columnas_paneles(JComboBox columnas) {
				
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 0; i < modelo_paneles_aux.getColumnCount() - 1; i++) { //-1 para no poder filtrar por mensaje
			lista_opciones_columnas.add(modelo_paneles_aux.getColumnName(i));
		}
		
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
	}
	
	public void create_combobox_comparacion_paneles(JComboBox comparaciones, String columna) {
		
		String[] opciones;

		if (columna.equals("Encendido") || columna.equals("Aviso")) {
		    opciones = new String[]{"="};
		} 
		
		else {
		    opciones = new String[]{"=", "<", ">"};
		}

		comparaciones.setModel(new DefaultComboBoxModel<>(opciones)); 
	}
	
	public void create_combobox_filas_paneles(JComboBox filas, String columna) {
				
		Set<String> lista_opciones_filas = new HashSet<>();

		for (int x = 0; x < modelo_paneles_aux.getRowCount(); x++) {
			lista_opciones_filas.add(String.valueOf(modelo_paneles_aux.getValueAt(x, modelo_paneles_aux.findColumn(columna))));
		}
		
		Vector<String> aux = new Vector<>(lista_opciones_filas);
		Collections.sort(aux);
		
		filas.setModel(new DefaultComboBoxModel<>(aux)); 
	}
	
	public void create_combobox_columnas_info(JComboBox columnas) {
		
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 0; i < modelo_info_aux.getColumnCount(); i++) { 
			lista_opciones_columnas.add(modelo_info_aux.getColumnName(i));
		}
		
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
	}
	
	public void create_combobox_comparacion_info(JComboBox comparaciones, String columna) {
		comparaciones.setModel(new DefaultComboBoxModel<>(new String[]{"=", "<", ">"}));
	}
	
	public void create_combobox_filas_info(JComboBox filas, String columna) {
				
		Set<String> lista_opciones_filas = new HashSet<>();
		
		for (int x = 0; x < modelo_info_aux.getRowCount(); x++) {
			lista_opciones_filas.add(String.valueOf(modelo_info_aux.getValueAt(x, modelo_info_aux.findColumn(columna))));
		}
		
		Vector<String> aux = new Vector<>(lista_opciones_filas);
		Collections.sort(aux);
		filas.setModel(new DefaultComboBoxModel<>(aux)); 
	}

	
	public int comparador(int i, DefaultTableModel modelo_aux, String item1, String item3) {
		
		Object valor = modelo_aux.getValueAt(i, modelo_aux.findColumn(item1));
		
		if (valor instanceof Number) {
			if (Integer.parseInt(String.valueOf(valor)) > Integer.parseInt(item3)) {return 1;}
			else if (Integer.parseInt(String.valueOf(valor)) < Integer.parseInt(item3)) {return 0;}
			return -1;
		}
		else {
			int x = String.valueOf(valor).compareTo(item3);
			if (x > 0) {return 1;}
			else if (x < 0) {return 0;}
			return -1;
		} 
	}
	
	public void filtrar(DefaultTableModel modelo_aux, DefaultTableModel modelo, JComboBox comb1, JComboBox comb2, JComboBox comb3) {
		modelo.setRowCount(0);
		int n_columnas = modelo_aux.getColumnCount();
		int n_filas = modelo_aux.getRowCount();
		
		String item1 = (String) comb1.getSelectedItem();
		String item2 = (String) comb2.getSelectedItem();
		String item3 = (String) comb3.getSelectedItem();
		
		for (int i = 0; i < n_filas; i++) {
			String[] fila = new String[n_columnas];
			int comparation = comparador(i, modelo_aux, item1, item3);
			
			if (item2.equals("=") && String.valueOf(modelo_aux.getValueAt(i, modelo_aux.findColumn(item1))).equals(item3)) {
				for (int x = 0; x < n_columnas; x++) {
					fila[x] = String.valueOf(modelo_aux.getValueAt(i, x));
				}	
				modelo.addRow(fila);
			}

			else if (comparation != -1) {
				
				if (item2.equals(">") && comparation != 0) {
					for (int x = 0; x < n_columnas; x++) {
						fila[x] = String.valueOf(modelo_aux.getValueAt(i, x));
					}	
					modelo.addRow(fila);
				}
				
				else if (item2.equals("<") && !(comparation != 0)) {
					for (int x = 0; x < n_columnas; x++) {
						fila[x] = String.valueOf(modelo_aux.getValueAt(i, x));
					}
					modelo.addRow(fila);
				}
			}
			
		}
		
		sort_paneles(Integer.parseInt(String.valueOf(table_paneles.getColumnModel().getColumnIndex(combobox_orden_paneles.getSelectedItem()))));
		sort_info(Integer.parseInt(String.valueOf(table_info.getColumnModel().getColumnIndex(combobox_orden_info.getSelectedItem()))));
	}

}