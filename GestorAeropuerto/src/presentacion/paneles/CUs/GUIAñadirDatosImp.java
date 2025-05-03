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
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUIAñadirDatosImp extends GUIAñadirDatos {

	String[] columnas = {"Panel", "Vuelo", "Salida", "Llegada", "Terminal", "Puerta", "Origen", "Destino", "Avion", "Aerolinea" }; 
	Vector<String> columnas_aux = new Vector<>(Arrays.asList(columnas));
	DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
		 public boolean isCellEditable(int row, int column) {
             return false;
         }
	};
	
	class Memento {
	    private Vector<Vector<Object>> datos;
	    public void setEstado(DefaultTableModel modelo) {
	        datos = new Vector<>();
	        for (int i = 0; i < modelo.getRowCount(); i++) {
	            Vector<Object> fila = new Vector<>();
	            for (int j = 0; j < modelo.getColumnCount(); j++) {
	                fila.add(modelo.getValueAt(i, j));
	            }
	            datos.add(fila);
	        }
	    }
	    public Vector<Vector<Object>> getDataVector() {
	        Vector<Vector<Object>> copia = new Vector<>();
	        for (Vector<Object> fila : datos) {
	            copia.add(new Vector<>(fila));
	        }
	        return copia;
	    }
	}
	
	String funcButtonName = "Añadir"; 
	
	
	JTable table = new JTable(modelo);  
	String tituloTabla = "Información Paneles"; 
	String tituloPanel = "Añadir Datos";
	String descFunc = "Se añadirán los datos";
	
	List<TransferInfoVuelos> datos_mostrados;
	
	Memento memento = new Memento();
	Set<String> vuelos_añadidos = new HashSet<String>();
	
	private final PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	JFrame marco;
	
	JComboBox combobox = new JComboBox();
	
	public GUIAñadirDatosImp()	{
		
		memento.setEstado(modelo);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(650, 350));
		
		JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel mementoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton acceptButton = new JButton("Aceptar");
		JButton cancelButton = new JButton("Cancelar");
		JButton orderButton = new JButton("Ordenar por");
		JButton funcButton = new JButton(funcButtonName);
		JButton returnButton = new JButton("Volver");
		
		mementoPanel.add(acceptButton);
		mementoPanel.add(cancelButton);
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
		panel.add(mementoPanel);
		panel.add(returnButtonPanel);
		
		
		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();
		
		acceptButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				memento.setEstado(modelo);
				vuelos_añadidos.clear();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e){
		    	
		    	GUISeleccionarDatos gui_aux = GUISeleccionarDatos.getInstancia();
				List<TransferInfoVuelos> lista_aux = gui_aux.get_datos_mostrados();
				
				for (int x = 0; x < vuelos_añadidos.size();x++) {
					boolean found = false;
					int i = 0;
					while (!found && i < lista_aux.size()) {
						if (vuelos_añadidos.contains(lista_aux.get(i).getVuelo())) {
							found = true;
							lista_aux.remove(i);
							gui_aux.set_datos_mostrados(lista_aux);
						}
						else {
							i++;
						}
					}
				}
				
		        Vector<Vector<Object>> datosGuardados = memento.getDataVector();
		        vuelos_añadidos.clear();
		        modelo.setDataVector(datosGuardados, columnas_aux);
		        modelo.fireTableDataChanged();
		    }
		});
		
		create_combobox_columnas_info(combobox);	
		
		orderButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			}
		});
		
		funcButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				
				marco.setVisible(false);
				GUISeleccionarDatosImp menu = (GUISeleccionarDatosImp) GUISeleccionarDatos.getInstancia();
				Controlador.getInstancia().accion(Eventos.AÑADIR_DATOS, null);
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		returnButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				memento.setEstado(modelo);
				vuelos_añadidos.clear();
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
			}
		});
		
		
		marco.setVisible(true);
	}
	
	
	//m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento)	{
		
		case (Eventos.FUNCIONALIDAD): { 
			modelo.setRowCount(0);
			GUISeleccionarDatos gui_seleccionar_datos = GUISeleccionarDatosImp.getInstancia();
			for (TransferInfoVuelos transfer: gui_seleccionar_datos.get_datos_mostrados()) {
				modelo.addRow(new Object[]{
						transfer.getPanel(),
						transfer.getVuelo(),
						transfer.getHora_salida(),
						transfer.getHora_llegada(),
						transfer.getTerminal(),
						transfer.getPuerta(),
						transfer.getOrigen(),
						transfer.getDestino(),
						transfer.getAvion(),
						transfer.getAerolinea()
						});
			} 
			
			sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			vuelos_añadidos = gui_seleccionar_datos.getVuelosNuevos();
			support.firePropertyChange("Modelo", null, modelo);
			break;
		}
		
		case (Eventos.ABRIR_MENU): {
			memento.setEstado(modelo);
			vuelos_añadidos.clear();
		}
		
		}
	};
	
	public JFrame getFrame() {
		return marco;
	}
	
	public DefaultTableModel get_modelo() {
		return modelo;
	}
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
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