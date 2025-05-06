package presentacion.operaciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import negocio.operaciones.TransferAsignacion;
import negocio.operaciones.TransferBloqueo;
import negocio.operaciones.TransferPuerta;

public class GUIInspeccionarPuertaImp extends GUIInspeccionarPuerta{

	 JFrame marco; 
	 TransferPuerta puerta; 
	 TimelinePuertaTableModel modelo;
	 TransferBloqueo bloqueoSeleccionado = null;
	 
	 public void InitGUI(List<TransferAsignacion> asignaciones, List<TransferBloqueo> bloqueos,TransferPuerta puerta) {
			// TODO Auto-generated method stub
			marco = new JFrame("Puerta " + puerta.getPuertaID());	
			marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			 marco.setLayout(new BorderLayout());

			 this.puerta = puerta;

			    JPanel panel = new JPanel();
			    panel.setLayout(new BorderLayout());


			    JLabel title = new JLabel("Puerta " + puerta.getPuertaID());
			    title.setFont(new Font("Dialog", Font.PLAIN, 24));
			    panel.add(title, BorderLayout.WEST);
			    
			    JButton verPuertaButton = new JButton("Ver Puerta");
			    verPuertaButton.addActionListener(e -> verPuertaFunc());
			    JButton añadirBloqueoButton = new JButton("Añadir Bloqueo");
			    
			    añadirBloqueoButton.addActionListener(e -> añadirBloqueoFunc());

			    
			    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); 
			    buttonPanel.add(verPuertaButton);
			    buttonPanel.add(añadirBloqueoButton);
			    
			    panel.add(buttonPanel, BorderLayout.EAST);


			    marco.getContentPane().add(panel, BorderLayout.NORTH);
			    
			    modelo = new TimelinePuertaTableModel(asignaciones,bloqueos);

			    TimelinePuertaTable table = new TimelinePuertaTable(modelo);
			    
			    
				
		        JScrollPane scrollPane = new JScrollPane(table);
		        marco.add(scrollPane, BorderLayout.CENTER);

				JPanel panelAbajo = new JPanel();
				panelAbajo.setLayout(new FlowLayout(FlowLayout.RIGHT));
				
				JButton borrarPuertaButton = new JButton("Borrar Puerta");
				
				borrarPuertaButton.addActionListener(e -> borrarPuertaFunc());

				JButton verBloqueo = new JButton("Ver Bloqueo");
				verBloqueo.setEnabled(false);
				verBloqueo.addActionListener(e -> verBloqueoFunc());
				
				
				
				table.addMouseListener(new MouseAdapter() {
			    	
			    	@Override
			    	public void mouseClicked(MouseEvent e) {
			    		table.setRowSelectionAllowed(true);
			    		int filaSeleccionada = table.rowAtPoint(e.getPoint());
			    		
			    		bloqueoSeleccionado = modelo.getBloqueoInRow(filaSeleccionada);
			    		
			    		if(bloqueoSeleccionado != null) {
			    			verBloqueo.setEnabled(true);
			    		}else {
			    			verBloqueo.setEnabled(false);
			    		}
			    	}
			    
				});
				
				
				JButton volverButton = new JButton("Volver");
				volverButton.addActionListener(e -> {
					marco.setVisible(false);
					Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);});

				panelAbajo.add(verBloqueo);
				panelAbajo.add(borrarPuertaButton);
				panelAbajo.add(volverButton);


				marco.getContentPane().add(panelAbajo, BorderLayout.SOUTH);
			    marco.pack();
			    marco.setLocationRelativeTo(null);
			    marco.setVisible(true);
	 }
	 

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch (evento) {
		case Eventos.BORRAR_PUERTA_OK:
			JOptionPane.showMessageDialog(null, "Puerta borrada correctamente. Gestiona los vuelos correspondientes.", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
			marco.setVisible(false);
			break;
		case Eventos.BORRAR_PUERTA_ERROR:
			JOptionPane.showMessageDialog(null, "ERROR! No se pudo modificar la puerta", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

	private void verPuertaFunc() {
		marco.setVisible(false);
		Controlador.getInstancia().accion(Eventos.VER_PUERTA, puerta, marco);
	}
	
	private void añadirBloqueoFunc() {
		marco.setVisible(false);
		Controlador.getInstancia().accion(Eventos.CREAR_BLOQUEO, puerta, marco);
	}
	
	private void borrarPuertaFunc() {
			int respuesta = JOptionPane.showConfirmDialog(
			        null,
			        "¿Estás seguro de que quieres borrar esta puerta? Los vuelos asignados necesitaran gestionarse.",
			        "Confirmar eliminación",
			        JOptionPane.YES_NO_OPTION,
			        JOptionPane.WARNING_MESSAGE
			    );
			if (respuesta == JOptionPane.YES_OPTION) {
				Controlador.getInstancia().accion(Eventos.BORRAR_PUERTA, puerta, null);	
				marco.setVisible(false);

			}else {
				return;
			}
	}
	
	private void verBloqueoFunc() {
		marco.setVisible(false);
		Controlador.getInstancia().accion(Eventos.VER_BLOQUEO, bloqueoSeleccionado, marco);
	}

}


	class TimelinePuertaTable extends JTable{
		
		public TimelinePuertaTable(AbstractTableModel model){
			
	        setLayout(new BorderLayout());
	        
	        setModel(model);
	        
	        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
	            getColumnModel().getColumn(i).setResizable(false);
	        }
	        
	        getTableHeader().setReorderingAllowed(false);
	     	     
		}
		
		
	    	
	}

	class TimelinePuertaTableModel extends AbstractTableModel{
		
		TransferAsignacion[] asignaciones = new TransferAsignacion[24];
		TransferBloqueo[] bloqueos = new TransferBloqueo[24];
		
		String[] Horas = {"00:00", "01:00", "02:00", "03:00","04:00","05:00","06:00","07:00",
						  "08:00", "09:00", "10:00", "11:00", "12:00","13:00", "14:00", "15:00", "16:00", "17:00",
						  "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
		
		
		
		public  TimelinePuertaTableModel (List<TransferAsignacion> asignaciones, List<TransferBloqueo> bloqueos) {
							
			for (TransferAsignacion asig: asignaciones) {
				this.asignaciones[asig.getHora_llegada().getHour()] = asig;
			}
			
			for (TransferBloqueo bloq : bloqueos) {
				this.bloqueos[bloq.getHoraInicio().getHour()] = bloq;
			}
		
		
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return Horas.length;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		
		@Override
		public String getColumnName (int column) {
			
			if (column == 0) return "Hora";
			return "Id Vuelo";
		}
		
		public TransferBloqueo getBloqueoInRow(int row) {
			return bloqueos[row];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
			if ( columnIndex == 0) return Horas[rowIndex];
			else if (asignaciones[rowIndex] != null) return asignaciones[rowIndex].getVueloId();
			else if (bloqueos[rowIndex] != null) return "BLOQUEADA";
			else return "-";
			
			
		}
		
	}


