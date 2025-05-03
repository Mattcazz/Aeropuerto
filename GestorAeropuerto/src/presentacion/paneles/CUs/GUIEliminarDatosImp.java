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
import java.util.List;
import java.util.Vector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIEliminarDatosImp extends GUIEliminarDatos {

	String funcButtonName = "Eliminar";
	String[] columnas = {"Panel", "Vuelo", "Salida", "Llegada", "Terminal", "Puerta", "Origen", "Destino", "Avion", "Aerolinea" }; 
	DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
		 public boolean isCellEditable(int row, int column) {
            return false;
        }
	};
	JTable table = new JTable(modelo);
	String tituloTabla = "Información Paneles";
	String tituloPanel = "Eliminar Datos";
	String descFunc = "Se eliminarán los datos";
	int filaSeleccionada = -1;

	JFrame marco;
	
	JComboBox combobox = new JComboBox();

	public GUIEliminarDatosImp() {
		
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
				Controlador.getInstancia().accion(Eventos.ELIMINAR_DATOS, null);
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
            }
                
        });

		marco.setVisible(true);
	}

	// m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.FUNCIONALIDAD): {
			
			if (filaSeleccionada != -1) {
				
				GUISeleccionarDatos gui_aux = GUISeleccionarDatos.getInstancia();
				List<TransferInfoVuelos> lista_aux = gui_aux.get_datos_mostrados();
				
				boolean found = false;
				int i = 0;
				while (!found && i < lista_aux.size()) {
					if (lista_aux.get(i).getVuelo().equals(table.getValueAt(filaSeleccionada, 0))) {
						found = true;
						lista_aux.remove(i);
						gui_aux.set_datos_mostrados(lista_aux);
					}
					else {
						i++;
					}
				}
				
				modelo.removeRow(filaSeleccionada);
				filaSeleccionada = -1;
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
				table.setModel(modelo);
				
			}
			else {
				Controlador.getInstancia().accion(Eventos.SIN_DATOS, GUIEliminarDatos.getInstancia());
			}
			break;
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
}