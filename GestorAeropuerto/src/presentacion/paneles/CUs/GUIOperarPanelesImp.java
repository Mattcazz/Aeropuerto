package presentacion.paneles.CUs;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import negocio.paneles.TransferPaneles;
import presentacion.paneles.Controlador;
import presentacion.paneles.Eventos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class GUIOperarPanelesImp extends GUIOperarPaneles {
	
	String[] columnas = {"ID", "Encendido", "Nº Columnas", "Nº Lineas", "Terminal", "Aviso", "Mensaje"}; 
	DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
		 public boolean isCellEditable(int row, int column) {
           return false;
       }
	};
	JTable table = new JTable(modelo);
	String tituloTabla = "Paneles"; 
	String tituloPanel = "Operar Panel"; 
	
	int filaSeleccionada = -1;
	
	JFrame marco;
	
	JComboBox combobox = new JComboBox();
	
	public GUIOperarPanelesImp() {
		
		table.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));
		
		JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		JButton orderButton = new JButton("Ordenar por");
		JButton añadirAvisoButton = new JButton("Añadir Aviso");
		JButton eliminarAvisoButton = new JButton("Eliminar Aviso");
		JButton onButton = new JButton("ON/OFF");
		JButton returnButton = new JButton("Volver");
		
		buttonsPanel.add(orderButton);
		buttonsPanel.add(combobox);
		returnButtonPanel.add(returnButton);
		funcButtonPanel.add(onButton);
		funcButtonPanel.add(añadirAvisoButton);
		funcButtonPanel.add(eliminarAvisoButton);
		
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
		
		create_combobox_columnas_paneles(combobox);	
		
		orderButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			}
		});
		
		onButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e) {
				if (filaSeleccionada != -1) {
					
					Boolean encender = false;
					if (modelo.getValueAt(filaSeleccionada, 1).equals("No")) { encender = true;}
					
					Object datos = new Object[] {modelo.getValueAt(filaSeleccionada, 0), encender};
					Controlador.getInstancia().accion(Eventos.ENCENDER_APAGAR_PANEL, datos);
					sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
					filaSeleccionada = -1;
				}
				else {
					Controlador.getInstancia().accion(Eventos.SIN_DATOS, null);
				}
				
			}
		});
		
		añadirAvisoButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				if (filaSeleccionada != -1) {
					marco.setVisible(false);
					GUIAñadirAvisoFormImp menu = (GUIAñadirAvisoFormImp) GUIAñadirAvisoForm.getInstancia();
					JFrame menuFrame = menu.getFrame();
					menuFrame.setVisible(true);
				}
				else {
					Controlador.getInstancia().accion(Eventos.SIN_DATOS, null);
				}
			}
		});
		
		eliminarAvisoButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				if (filaSeleccionada != -1) {
					if (((String) modelo.getValueAt(filaSeleccionada, 5)).equals("Si")) {
						Controlador.getInstancia().accion(Eventos.ELIMINAR_AVISO, (int) modelo.getValueAt(filaSeleccionada, 0));
						sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
						filaSeleccionada = -1;
						
					}
				}
				else {
					Controlador.getInstancia().accion(Eventos.SIN_DATOS, null);
				}
			}
		});
		
		returnButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
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
	
	
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		
		case (Eventos.ABRIR_MENU): { 
			modelo.setRowCount(0);
			for (TransferPaneles transfer: (List<TransferPaneles>) datos) {
				modelo.addRow(new Object[] {
						transfer.getId(),
						transfer.getEncendido() != 0 ? "Si":"No",
						transfer.getN_columnas(),
						transfer.getN_lineas(),
						transfer.getTerminal(),
						transfer.getTieneAviso() != 0 ? "Si":"No",
						transfer.getAviso()
				});
			}
			
			sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			
			break;
		}
		}
	};
	
	public JFrame getFrame() {
		return marco;
	}
	
	public int getSelectedRowPanelId() {
		return (int) modelo.getValueAt(filaSeleccionada, 0);
	}
	
	public void sort(int index_columna) {
		Vector<Vector> datos = modelo.getDataVector();
		datos.sort(Comparator.comparingInt(fila -> Integer.parseInt(fila.get(index_columna).toString())));
		modelo.fireTableDataChanged();
	}
	
	public void create_combobox_columnas_paneles(JComboBox columnas) {
		
		Vector<String> lista_opciones_columnas = new Vector<>();
		
		for (int i = 0; i < modelo.getColumnCount(); i++) {
			if (i != 1 && i != 5 && i != 6) {lista_opciones_columnas.add(modelo.getColumnName(i));}//Ni por los bools ni por mensaje
		}
		
		columnas.setModel(new DefaultComboBoxModel<>(lista_opciones_columnas)); 
	}
  	
}