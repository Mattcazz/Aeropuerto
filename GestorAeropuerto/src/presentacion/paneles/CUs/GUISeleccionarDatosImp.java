package presentacion.paneles.CUs;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Collections;

public class GUISeleccionarDatosImp extends GUISeleccionarDatos {

	String funcButtonName = "Añadir"; 
	
	String[] columnas = {"Vuelo", "Salida", "Llegada", "Terminal", "Puerta", "Origen", "Destino", "Avion", "Aerolinea" }; 
	DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
		 public boolean isCellEditable(int row, int column) {
            return false;
        }
	};
	JTable table = new JTable(modelo);
	String tituloTabla = "Datos"; 
	String tituloPanel = "Seleccionar Datos"; 
	
	List<TransferInfoVuelos> datos_mostrados = new ArrayList<>();
	
	JComboBox combobox = new JComboBox();
	
	int filaSeleccionada = -1;
	
	JFrame marco;
	
	Set<String> vuelos_añadidos_nuevos = new HashSet<String>();
	
	public GUISeleccionarDatosImp()	{
		
		table.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(650, 350));
		
		JPanel comboboxPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton returnButton = new JButton("Volver");
		JButton funcButton = new JButton("Seleccionar");
		
		buttonsPanel.add(funcButton);
		returnButtonPanel.add(returnButton);
		comboboxPanel.add(new JLabel("Panel: "));
		comboboxPanel.add(combobox);
		
		JScrollPane table_frame = new JScrollPane(table);
		
		JLabel title = new JLabel(tituloTabla);
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		panel.add(title);
		panel.add(Box.createHorizontalGlue());
		panel.add(comboboxPanel);
		panel.add(table_frame);
		panel.add(buttonsPanel);
		panel.add(returnButtonPanel);
		
		
		marco.getContentPane().add(panel);
		marco.setTitle(tituloPanel);
		marco.pack();

		returnButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, GUIAñadirDatos.getInstancia());
				
			}
		});
		
		funcButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				
				GUIMostrarDatosImp menu_aux = (GUIMostrarDatosImp) GUIMostrarDatos.getInstancia();
				JFrame menuFrame = menu_aux.getFrame();
				menuFrame.setVisible(false);
				
				if (!(menu_aux.id_paneles_existentes().isEmpty())) {
				
					GUIAñadirDatos gui = GUIAñadirDatos.getInstancia();
					   
	                if (filaSeleccionada != -1) {
	               	 TransferInfoVuelos transfer = new TransferInfoVuelos();
	                    
	                    boolean añadir = true;
	                    for (int i = 0; i < datos_mostrados.size(); i++) {
		                   	 if (table.getValueAt(filaSeleccionada, 0).equals(datos_mostrados.get(i).getVuelo())) {
		                   		 añadir = false;
		                   	 }
	                    }
	                    
	                    if (añadir) {
	                    	vuelos_añadidos_nuevos.add((String) table.getValueAt(filaSeleccionada, 0));
	                    	
							transfer.setPanel(Integer.parseInt(String.valueOf(combobox.getSelectedItem())));
	                    	transfer.setVuelo((String) table.getValueAt(filaSeleccionada, 0));
	   	                 	transfer.setHora_llegada((LocalTime) table.getValueAt(filaSeleccionada, 1)); 
	   	                 	transfer.setHora_salida((LocalTime) table.getValueAt(filaSeleccionada, 2));
	   	                 	transfer.setTerminal((int) table.getValueAt(filaSeleccionada, 3));
	   	                 	transfer.setPuerta((int) table.getValueAt(filaSeleccionada, 4)); 
							transfer.setOrigen((String) table.getValueAt(filaSeleccionada, 5));
							transfer.setDestino((String) table.getValueAt(filaSeleccionada, 6));
							transfer.setAvion((String) table.getValueAt(filaSeleccionada, 7));
							transfer.setAerolinea((String) table.getValueAt(filaSeleccionada, 8));
	                    	
	   	                 	datos_mostrados.add(transfer);
	                    }
	                    
	                    else {
	                      	 Controlador.getInstancia().accion(Eventos.DATOS_YA_EXISTENTES, null);
	                    }
	                    filaSeleccionada = -1;
	                }
	                else {
	               	 Controlador.getInstancia().accion(Eventos.SIN_DATOS, GUIAñadirDatos.getInstancia());
	                }
	                
					gui.actualizar(Eventos.FUNCIONALIDAD, datos_mostrados);
				
				}
				else {
	               	 Controlador.getInstancia().accion(Eventos.SIN_PANELES, null);
				}
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
	
	public List<TransferInfoVuelos> devolver_datos_mostrar() {
		return datos_mostrados;
	}
	
	//m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento)	{
		
		case (Eventos.FUNCIONALIDAD): { 
			modelo.setRowCount(0);
			vuelos_añadidos_nuevos.clear();
			combobox.removeAllItems();
			for (TransferInfoVuelos transfer: (List<TransferInfoVuelos>) datos) {
				modelo.addRow(new Object[]{
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
			
			GUIMostrarDatosImp menu = (GUIMostrarDatosImp) GUIMostrarDatos.getInstancia();
			JFrame menuFrame = menu.getFrame();
			menuFrame.setVisible(false);
			Controlador.getInstancia().accion(Eventos.ABRIR_MENU, menu); 
			
			Vector<Integer> aux = new Vector<Integer>(menu.id_paneles_existentes());
			Collections.sort(aux);
			combobox.setModel(new DefaultComboBoxModel<>(aux));
			
			break;
		}
		
		}
	};
	
	public JFrame getFrame() {
		return marco;
	}
	
	public List<TransferInfoVuelos> get_datos_mostrados() {
		return datos_mostrados;
	}
	
	public void set_datos_mostrados(List<TransferInfoVuelos> datos) {
		datos_mostrados = datos;
	}
	
	public Set<String> getVuelosNuevos() {
		return vuelos_añadidos_nuevos;
	}
}