package presentacion.equipajes.CUs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.equipajes.TransferEquipaje;
import presentacion.equipajes.Controlador;
import presentacion.equipajes.Eventos;

public class GUIEquipajesVueloImp extends GUIEquipajesVuelo {
	
	
	
	private final String[] columnas = { "ID", "ID Vuelo", "Peso(kg)"};
	private JTable tabla;
	private DefaultTableModel modelo_tabla;
	
	private String id_vuelo;
	
	private final String titulo_panel = "Lista Equipajes";

	
	
	
	JFrame marco;
	
	public GUIEquipajesVueloImp(){
		id_vuelo=" ";
		initGUI();
	}

	
	private void initGUI() {
		// TODO Auto-generated method stub
		marco=new JFrame(titulo_panel);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(600, 300));
		
		// Título
		JLabel title = new JLabel(titulo_panel);
		title.setFont(new Font("Dialog", Font.PLAIN, 20));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Tabla
		modelo_tabla = new DefaultTableModel(columnas, 0); 
		tabla = new JTable(modelo_tabla);
		tabla.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tabla);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton modificar = new JButton("Modificar");
		JButton eliminar = new JButton("Eliminar");
		JButton anadir =  new JButton("Añadir");
		
		JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton volver = new JButton("Volver");
		
		buttonsPanel.add(modificar);
		buttonsPanel.add(eliminar);
		buttonsPanel.add(anadir);
		volverPanel.add(volver);
		
		panel.add(title);
		panel.add(scrollPane);
		panel.add(buttonsPanel);
		panel.add(volverPanel);
		
		marco.setContentPane(panel);
		marco.pack();
		marco.setLocationRelativeTo(null); // Centrar ventana
		marco.setVisible(true);
		
		modificar.addActionListener(e-> {
			int filaSeleccionada = tabla.getSelectedRow();
			
			if (filaSeleccionada != -1) {
				TransferEquipaje te= new TransferEquipaje((int)tabla.getValueAt(filaSeleccionada, 0),(String)tabla.getValueAt(filaSeleccionada, 1),(double)tabla.getValueAt(filaSeleccionada, 2));
				Controlador.getInstancia().accion(Eventos.MODIFICAR_EQUIPAJE, te);
			}
			else {
				JOptionPane.showMessageDialog(null, "Por favor selecciona una fila.");
			}
		});
		
		eliminar.addActionListener(e->{
			int filaSeleccionada = tabla.getSelectedRow();
			
			if (filaSeleccionada != -1) {
				TransferEquipaje te= new TransferEquipaje((int)tabla.getValueAt(filaSeleccionada, 0),(String)tabla.getValueAt(filaSeleccionada, 1),(double)tabla.getValueAt(filaSeleccionada, 2));
				Controlador.getInstancia().accion(Eventos.ELIMINAR_EQUIPAJE, te);
			}
			else {
				JOptionPane.showMessageDialog(null, "Por favor selecciona una fila.");
			}
		});
		
		anadir.addActionListener(e->{
			Controlador.getInstancia().accion(Eventos.ANADIR_EQUIPAJE, this.id_vuelo);
			
		});
		
		volver.addActionListener(e -> {
			marco.setVisible(false);
		});
		
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		if(evento ==Eventos.ACCEDER_VUELO && datos instanceof List<?>) {
			modelo_tabla.setRowCount(0);
			List<?> lista = (List<?>) datos;
			for (Object obj : lista) {
				if (obj instanceof TransferEquipaje equ) {
					Object[] fila = { equ.getId(), equ.getIdVuelo(), equ.getPeso() };
					modelo_tabla.addRow(fila);
				}
			}
		}
	}

	public void mostrar(String idVuelo) {
		// TODO Auto-generated method stub
		marco.setVisible(true);
	}


	public void setIdVuelo(String _id) {
		// TODO Auto-generated method stub
		this.id_vuelo=_id;
	}

	

}
