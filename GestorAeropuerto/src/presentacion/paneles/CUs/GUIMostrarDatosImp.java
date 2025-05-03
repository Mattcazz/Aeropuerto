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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIMostrarDatosImp extends GUIMostrarDatos {

	String funcButtonName = "Mostrar";
	
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
	
	String tituloPanel = "Mostrar Datos";
	int filaSeleccionada = -1;

	JFrame marco;
	
	DefaultTableModel modelo_aux;
	
	Set<Integer> ids = new HashSet<Integer>();
	
	JComboBox combobox_orden_paneles = new JComboBox();
	JComboBox combobox_orden_info = new JComboBox();

	public GUIMostrarDatosImp() {
		
		GUIAñadirDatos gui_añadir_datos = GUIAñadirDatos.getInstancia();
		((GUIAñadirDatosImp) gui_añadir_datos).getFrame().setVisible(false);
		modelo_aux = gui_añadir_datos.get_modelo();
		gui_añadir_datos.addPropertyChangeListener(evt -> {
			if ("Modelo".equals(evt.getPropertyName())) {
				modelo_aux = (DefaultTableModel) evt.getNewValue();
			}
		});
		
		table_paneles.getTableHeader().setReorderingAllowed(false);
		table_info.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(650, 400));

		JPanel buttonsPanelInfo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanelPaneles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton orderButtonPaneles = new JButton("Ordenar por");
		JButton orderButtonInfo = new JButton("Ordenar por");
		JButton returnButton = new JButton("Volver");

		buttonsPanelPaneles.add(orderButtonPaneles);
		buttonsPanelInfo.add(orderButtonInfo);
		buttonsPanelPaneles.add(combobox_orden_paneles);
		buttonsPanelInfo.add(combobox_orden_info);
		returnButtonPanel.add(returnButton);
		
		JScrollPane table_paneles_frame = new JScrollPane(table_paneles);

		JLabel title_paneles = new JLabel(tituloTablaPaneles);
		title_paneles.setFont(new Font("Dialog", Font.PLAIN, 24));
		title_paneles.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(title_paneles);
		panel.add(Box.createHorizontalGlue());
		panel.add(table_paneles_frame);
		panel.add(buttonsPanelPaneles);

		JScrollPane table_info_frame = new JScrollPane(table_info);

		JLabel title_info = new JLabel(tituloTablaInfo);
		title_info.setFont(new Font("Dialog", Font.PLAIN, 24));
		title_info.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(title_info);
		panel.add(Box.createHorizontalGlue());
		panel.add(table_info_frame);
		panel.add(returnButtonPanel);
		
		panel.add(buttonsPanelInfo);
		panel.add(returnButtonPanel);
		
		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();

		create_combobox_columnas_paneles(combobox_orden_paneles);	
		
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

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
			}
		});
		
		table_paneles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
           	 table_paneles.setRowSelectionAllowed(true);
             filaSeleccionada = table_paneles.rowAtPoint(e.getPoint());
             
             if (modelo_aux.getRowCount() > 0) {
	             modelo_info.setRowCount(0);
	             Vector<Vector> dataVector = modelo_aux.getDataVector();
	             for (int i = 0; i < modelo_aux.getRowCount(); i++) {
	            	 if (modelo_paneles.getValueAt(filaSeleccionada, 0).equals(modelo_aux.getValueAt(i, 0))) {
	            		 Vector row = dataVector.get(i);
	            		 modelo_info.addRow(row);
	            	 }
	             }
             }
             else {
            	 Controlador.getInstancia().accion(Eventos.SIN_DATOS_EN_PANEL, null);
             }
             
            }
                
        });

		marco.setVisible(true);
	}

	// m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		
		case (Eventos.ABRIR_MENU): { 
			modelo_paneles.setRowCount(0);
			ids.clear();
			for (TransferPaneles transfer: (List<TransferPaneles>) datos) {
				ids.add(transfer.getId());
				modelo_paneles.addRow(new Object[] {
						transfer.getId(),
						transfer.getEncendido() != 0 ? "Si":"No",
						transfer.getN_columnas(),
						transfer.getN_lineas(),
						transfer.getTerminal(),
						transfer.getTieneAviso() != 0 ? "Si":"No",
						transfer.getAviso()
				});
				
				sort_paneles(Integer.parseInt(String.valueOf(table_paneles.getColumnModel().getColumnIndex(combobox_orden_paneles.getSelectedItem()))));
			}
			break;
		}
		}
	};

	public JFrame getFrame() {
		return marco;
	}
	
	public Set<Integer> id_paneles_existentes() {
		return ids;
	}
	
	public void sort_paneles(int index_columna) {
		Vector<Vector> datos = modelo_paneles.getDataVector();
		datos.sort(Comparator.comparingInt(fila -> Integer.parseInt(fila.get(index_columna).toString())));
		modelo_paneles.fireTableDataChanged();
	}
	
	public void create_combobox_columnas_paneles(JComboBox columnas) {
		
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 0; i < modelo_paneles.getColumnCount(); i++) {
			if (i != 1 && i != 5 && i != 6) {lista_opciones_columnas.add(modelo_paneles.getColumnName(i));}//Ni por los bools ni por mensaje
		}
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
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
	
	public void create_combobox_columnas_info(JComboBox columnas) {
		
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 1; i < modelo_info.getColumnCount(); i++) { //Empieza en 1 porque no tiene sentido ordenar por panel
			lista_opciones_columnas.add(modelo_info.getColumnName(i));
		}
		
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
	}
}