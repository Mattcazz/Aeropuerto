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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class GUIAñadirPanelImp extends GUIAñadirPanel {
	
	String funcButtonName = "Añadir"; 
	String[] columnas = {"ID", "Encendido", "Nº Columnas", "Nº Lineas", "Terminal", "Aviso", "Mensaje"}; 
	DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
		 public boolean isCellEditable(int row, int column) {
           return false;
       }
	};
	JTable table = new JTable(modelo);
	String tituloTabla = "Paneles"; 
	String tituloPanel = "Añadir Panel"; 
	
	JFrame marco;
	
	JComboBox combobox = new JComboBox();
	
	public GUIAñadirPanelImp()	{
		
		table.getTableHeader().setReorderingAllowed(false);
		
		marco = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(500, 250));
		
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
		
		create_combobox_columnas_paneles(combobox);	
		
		orderButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				sort(Integer.parseInt(String.valueOf(table.getColumnModel().getColumnIndex(combobox.getSelectedItem()))));
			}
		});
		
		funcButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				GUIAñadirPanelFormImp menu = (GUIAñadirPanelFormImp) GUIAñadirPanelForm.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}
		});
		
		returnButton.addActionListener(new ActionListener()	{ 
			public void actionPerformed(ActionEvent e){
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
			}
		});
		
		
		marco.setVisible(true);
	}
	
	
	//m�todo actualizar de la vista
	public void actualizar(int evento, Object datos) {
		switch (evento)	{
		
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