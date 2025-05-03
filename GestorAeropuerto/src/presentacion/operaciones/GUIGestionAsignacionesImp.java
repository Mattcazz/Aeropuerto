package presentacion.operaciones;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import negocio.operaciones.TransferActualizarGUIGestionAsignaciones;
import negocio.operaciones.TransferAsignacion;
import negocio.operaciones.TransferCrearAsignacion;
import negocio.operaciones.TransferPrepararGestionAsignacion;
import negocio.operaciones.TransferPuerta;
import negocio.vuelos.TransferVuelo;
import negocio.operaciones.TransferVueloEstrategia;

public class GUIGestionAsignacionesImp extends GUIGestionAsignaciones {

	JFrame marco;
	CrearAsignacionPanel crearAsignacionPanel;
	TimelineAsignacionesTable tablaAsignaciones;
	TimelineAsignacionesTableModel modelo;
	TransferAsignacion asignacionSeleccionada = null;
	
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
		switch(evento) {
		case Eventos.ACTUALIZAR_PUERTAS_DISPONIBLES_RESP:
			crearAsignacionPanel.actualizarPuertasDisponibles((List<TransferPuerta>)datos);
			break;
		case Eventos.BORRAR_ASIGNACION_RESP_OK:
			aceptamosActualizacion((TransferActualizarGUIGestionAsignaciones) datos);
			mostrarMensaje("Se ha borrado la asignacion con exito");
			break;
		case Eventos.BORRAR_ASIGNACION_RESP_ERROR:
			mostrarMensaje("ERROR: No se pudo eliminar la asignacion seleccionada");
			break;
		case Eventos.CREAR_ASIGNACION_RESP_OK:
			aceptamosActualizacion((TransferActualizarGUIGestionAsignaciones) datos);
			mostrarMensaje("Se ha creado la asignacion con exito");
			break;
		case Eventos.CREAR_ASIGNACION_RESP_ERROR:
			mostrarMensaje("ERROR: No se pudo crear la asignacion con los datos indicados");
			break;
		
		
		
		}
		
	}
	
	private void aceptamosActualizacion(TransferActualizarGUIGestionAsignaciones transfer) {
		crearAsignacionPanel.actualizarPuertasDisponibles(transfer.getPuertasDisponiblesParaVuelo());
		crearAsignacionPanel.actualizarVuelosPendientes(transfer.getVuelosPendientes());
		modelo.fill_data(transfer.getAsignaciones());
	}

	@Override
	public void InitGUI(TransferPrepararGestionAsignacion datosGUI) {
		
		 marco = new JFrame("Gestion de Asignaciones");	
		 marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 marco.setLayout(new BorderLayout());
    	    
	    crearAsignacionPanel = new CrearAsignacionPanel(datosGUI.getVuelosPendientes(),datosGUI.getPuertasDisponiblesParaVuelo(),datosGUI.getEstrategias());
	    
	    modelo = new TimelineAsignacionesTableModel(datosGUI.getAsignaciones());
	    
	    tablaAsignaciones = new TimelineAsignacionesTable(modelo);

	    JScrollPane tablaScrollPane = new JScrollPane(tablaAsignaciones );
	    
	    	
	    	
	    JPanel centerPanel = new JPanel();
	    centerPanel.setLayout(new BorderLayout());
	
	    JLabel titleTable = new JLabel("Timeline Asignaciones");
	    titleTable.setFont(new Font("Dialog", Font.PLAIN, 24));
	    titleTable.setAlignmentX(Component.CENTER_ALIGNMENT);

	    
	    centerPanel.add(crearAsignacionPanel, BorderLayout.NORTH);
	    centerPanel.add(titleTable, BorderLayout.CENTER);
	    centerPanel.add(tablaScrollPane, BorderLayout.SOUTH);
	    
	    marco.getContentPane().add(centerPanel, BorderLayout.CENTER);
	  
	    
	    JPanel panelAbajo = new JPanel();
		panelAbajo.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

		JButton volverButton = new JButton("Volver");
		volverButton.addActionListener(e -> {
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);
			});
		
		JButton borrarAsignacionButton = new JButton("Borrar Asignacion");
		borrarAsignacionButton.setEnabled(false);
		
	    tablaAsignaciones.addMouseListener(new MouseAdapter() {
	    	
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		tablaAsignaciones.setRowSelectionAllowed(true);
	    		int filaSeleccionada = tablaAsignaciones.rowAtPoint(e.getPoint());
	    		
	    		asignacionSeleccionada = modelo.getAsignacionInRow(filaSeleccionada);
	    		borrarAsignacionButton.setEnabled(true);
	    	}
	    
    });
    
		
		borrarAsignacionButton.addActionListener(e -> {
			if (asignacionSeleccionada != null) {
				int respuesta = JOptionPane.showConfirmDialog(
				        null,
				        "¿Estás seguro de que quieres borrar esta asignacion?",
				        "Confirmar eliminación",
				        JOptionPane.YES_NO_OPTION,
				        JOptionPane.WARNING_MESSAGE
				    );
				if (respuesta == JOptionPane.YES_OPTION) {
					Controlador.getInstancia().accion(Eventos.BORRAR_ASIGNACION, asignacionSeleccionada, null);	
	
				}else {
					return;
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Selecciona una puerta a borrar", "Warning", JOptionPane.WARNING_MESSAGE);
			}
						
		});

		panelAbajo.add(borrarAsignacionButton);
		panelAbajo.add(volverButton);


		marco.getContentPane().add(panelAbajo, BorderLayout.SOUTH);

		
	    marco.pack();
	    marco.setLocationRelativeTo(null);
	    marco.setVisible(true);

	}

	@Override
	public void mostrarMensaje(String mensaje) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
	
}


class CrearAsignacionPanel extends JPanel{
	
	private JList<Integer> idPuertasList;
	private DefaultListModel<Integer> idPuertas;
	private JComboBox<String> comboBoxEstrategias;
	private JList<String> idVuelosList;
	private boolean actualizandoLista = false;
	DefaultListModel<String> idVuelos;

	
	public CrearAsignacionPanel(List<TransferVuelo> vuelosPendientes, List<TransferPuerta> puertasDisponiblesPrimerVuelo, Set<String> nombres_estrategias) {
		
		setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Estrategia de asignacion");
	    title.setFont(new Font("Dialog", Font.PLAIN, 24));
	    add(title, BorderLayout.WEST);
		
	    String[] nombreEstrategias = new String[nombres_estrategias.size()];
	    
	    int i = 0; 
		for (String estrategia: nombres_estrategias) {
			nombreEstrategias[i] = estrategia;
			i++;
		}
		
		comboBoxEstrategias = new JComboBox<>(nombreEstrategias);
		
		JButton aplicarEstrategiaButton = new JButton ("Aplicar");
		aplicarEstrategiaButton.addActionListener(e -> aplicarEstrategiaFunc());
		
		
		JPanel aplicarEstrategiasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		aplicarEstrategiasPanel.add(comboBoxEstrategias);
		aplicarEstrategiasPanel.add(aplicarEstrategiaButton);
		
		add(aplicarEstrategiasPanel, BorderLayout.EAST);
		
		JPanel listasPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		idVuelos = new DefaultListModel<>();
		idPuertas = new DefaultListModel<>();
		
		for (TransferVuelo vp : vuelosPendientes) {
			idVuelos.addElement(vp.getVueloId());
		}
		
		for (TransferPuerta pd : puertasDisponiblesPrimerVuelo) {
			idPuertas.addElement(pd.getPuertaID());
		}
		
		idVuelosList = new JList<String>(idVuelos);
		idVuelosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		idVuelosList.setSelectedIndex(0);
		idVuelosList.setVisibleRowCount(5);        
		
		idVuelosList.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting() && !actualizandoLista) {
		    	aplicarEstrategiaFunc();
		    }
		});
		
		idPuertasList = new JList<Integer>(idPuertas);
		idPuertasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		idPuertasList.setSelectedIndex(0);
		idPuertasList.setVisibleRowCount(5);        		
		
		
		JScrollPane  idVuelosScrollPane = new JScrollPane(idVuelosList);
		JScrollPane  idPuertasScrollPane = new JScrollPane(idPuertasList);
		
		JPanel vuelosPanel = new JPanel();
		vuelosPanel.setLayout(new BoxLayout(vuelosPanel, BoxLayout.Y_AXIS));
		vuelosPanel.add(new JLabel("Vuelos pendientes"));
		vuelosPanel.add(idVuelosScrollPane);

		JPanel puertasPanel = new JPanel();
		puertasPanel.setLayout(new BoxLayout(puertasPanel, BoxLayout.Y_AXIS));
		puertasPanel.add(new JLabel("Puertas disponibles"));
		puertasPanel.add(idPuertasScrollPane);

		listasPanel.add(vuelosPanel);
		listasPanel.add(puertasPanel);
		
		
		comboBoxEstrategias.setPreferredSize(new Dimension(200, 30)); // fixed size
		idVuelosList.setFixedCellWidth(120);
		idPuertasList.setFixedCellWidth(120);
		
		JButton crearAsignacionButton = new JButton("Asignar");
		crearAsignacionButton.addActionListener(e -> crearAsignacionButtonFunc());
		
		listasPanel.add(crearAsignacionButton);
		
		add(listasPanel, BorderLayout.SOUTH);
		
	}
	
	private void crearAsignacionButtonFunc() {

		TransferCrearAsignacion transferAsignacion = null; 
		
		int vueloIndex = idVuelosList.getSelectedIndex();
		int puertaIndex = idPuertasList.getSelectedIndex();
		
		if (vueloIndex >= 0 && puertaIndex >= 0) {
			transferAsignacion = new TransferCrearAsignacion(idVuelosList.getSelectedValue(), idPuertasList.getSelectedValue());
			Controlador.getInstancia().accion(Eventos.CREAR_ASIGNACION, transferAsignacion, null);
		}else {
			JOptionPane.showMessageDialog(null, "Tienes que seleccionar un avion y una puerta", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	
	private void aplicarEstrategiaFunc() {
		TransferVueloEstrategia transferVueloEstrategia = null;
		
		String id = idVuelosList.getSelectedValue();
		
		 
		if (id != null) {
			transferVueloEstrategia = new TransferVueloEstrategia(id,(String)comboBoxEstrategias.getSelectedItem());
			Controlador.getInstancia().accion(Eventos.ACTUALIZAR_PUERTAS_DISPONIBLES, transferVueloEstrategia, null);
		}
			
	}
	
	public void actualizarPuertasDisponibles(List<TransferPuerta> nuevasPuertas) {
	   idPuertas.clear();
	   for (TransferPuerta puerta : nuevasPuertas) {
		   idPuertas.addElement(puerta.getPuertaID());
	   }
	   idPuertasList.setSelectedIndex(0);
	}
	
	public void actualizarVuelosPendientes(List<TransferVuelo> nuevosVuelos) {
	    
		actualizandoLista = true;
		
		idVuelos.clear();
	    for (TransferVuelo vuelo : nuevosVuelos) {
	        idVuelos.addElement(vuelo.getVueloId());
	    }
	    idVuelosList.setSelectedIndex(0);
	    actualizandoLista = false;
	}
}


class TimelineAsignacionesTable extends JTable{
	
	public TimelineAsignacionesTable (AbstractTableModel model){
		
        setLayout(new BorderLayout());
        
        setModel(model);
        
        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setResizable(false);
        }
        
        getTableHeader().setReorderingAllowed(false);
     
     
	}
	
    	
}




class TimelineAsignacionesTableModel extends AbstractTableModel{
	
	List<TransferAsignacion> asignaciones;
	DateTimeFormatter formatter;
	
	
	public  TimelineAsignacionesTableModel (List<TransferAsignacion> asignaciones) {
		this.asignaciones = asignaciones;
		 formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	}
	
	public void fill_data(List<TransferAsignacion> asignaciones) {
		this.asignaciones = asignaciones;
		fireTableDataChanged(); 
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return asignaciones.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	public TransferAsignacion getAsignacionInRow(int row) {
		return asignaciones.get(row);
	}
	
	@Override
	public String getColumnName (int column) {
		
		if (column == 0) return "Id Puerta";
		if (column == 1) return "Id Vuelo";
		if (column == 2) return "Hora LLegada";
		else return "Hora Salida";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		TransferAsignacion asignacion = asignaciones.get(rowIndex);
		
		if (columnIndex == 0) return asignacion.getPuertaId();
		if (columnIndex == 1) return asignacion.getVueloId();
		if (columnIndex == 2) return asignacion.getHora_llegada().format(this.formatter);
		else return asignacion.getHora_salida().format(this.formatter);
		
		
	}
	

}
