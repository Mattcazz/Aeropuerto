package presentacion.operaciones;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.AbstractCellEditor;

import negocio.operaciones.TransferPuerta;


public class GUIPuertasImp extends GUIPuertas{

	JFrame marco;
	List<TransferPuerta> puertas;
		
	public void InitGUI(List<TransferPuerta> puertas) {
		
		this.puertas = puertas;
		
		marco = new JFrame("Gestion de puertas");	
		marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 marco.setLayout(new BorderLayout());

		    JPanel panel = new JPanel();
		    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		    panel.setPreferredSize(new Dimension(500, 40));

		    JLabel title = new JLabel("Puertas");
		    title.setFont(new Font("Dialog", Font.PLAIN, 24));
		    title.setAlignmentX(Component.CENTER_ALIGNMENT);

		    panel.add(title);

		    marco.getContentPane().add(panel, BorderLayout.NORTH);

			marco.getContentPane().add(new InfoPuertaTable(this.puertas, marco), BorderLayout.CENTER);
			
			JPanel panelAbajo = new JPanel();
			panelAbajo.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			JButton crearPuertaButton = new JButton("Crear Puerta");
			
			crearPuertaButton.addActionListener(e -> {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.CREAR_PUERTA, null, marco);
			});

			JButton volverButton = new JButton("Volver");
			volverButton.addActionListener(e -> {
				marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);});

			panelAbajo.add(crearPuertaButton);
			panelAbajo.add(volverButton);


			marco.getContentPane().add(panelAbajo, BorderLayout.SOUTH);
			
		    marco.pack();
		    marco.setLocationRelativeTo(null); 
		    marco.setVisible(true);
	}
	
	
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Eventos.CREAR_PUERTA_RESP_OK:
			InitGUI((List<TransferPuerta>) datos);
			JOptionPane.showMessageDialog(null, "Puerta creada correctamente","Confirmacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}

class InfoPuertaTable extends JPanel{
	
	public InfoPuertaTable(List<TransferPuerta> puertas, JFrame marco){
		
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500,200));
	   
        JTable table = new JTable(new InfoPuertasTableModel(puertas));
     
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        
        table.getTableHeader().setReorderingAllowed(false);
        
        
        
        CustomCellEditor editor_renderer = new CustomCellEditor(puertas, marco);
        table.getColumnModel().getColumn(3).setCellRenderer(editor_renderer);
        table.getColumnModel().getColumn(3).setCellEditor(editor_renderer);
	    
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
     
	}
	
	
    	
}

class CustomCellEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	List<TransferPuerta> puertas;
	JFrame marco; 
	
	public CustomCellEditor(List<TransferPuerta> puertas, JFrame marco) {
		this.puertas = puertas;
		this.marco = marco;
	}
	

	void modificarFunc(){
		JOptionPane.showMessageDialog(null, "Se muestra la pantalla de modificar puerta");
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return "Modificar";
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		 JButton modificar = new JButton("Inspeccionar");
	        modificar.addActionListener(e -> {
	        	Controlador.getInstancia().accion(Eventos.INSPECCIONAR_PUERTA, puertas.get(row), marco);
	        	fireEditingStopped();
	        	marco.setVisible(false);
	        });
	        return modificar;
	}


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return new JButton("Inspeccionar");
	}
	
}

class InfoPuertasTableModel extends AbstractTableModel{
	
	List<TransferPuerta> puertas;
	String[] columnNames = {"ID", "Terminal", "Estado",""};
	
	public InfoPuertasTableModel(List<TransferPuerta> puertas) {
		this.puertas = puertas; 
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return puertas.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	@Override
	public String getColumnName (int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		TransferPuerta puerta =puertas.get(rowIndex);
		
		if (columnIndex == 0) return puerta.getPuertaID();
		if (columnIndex == 1) return puerta.getTerminal();
		if (columnIndex == 2) return puerta.getEstado();
		else return "Modificar";
		
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    return columnIndex == 3; 
    }
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 4) return JButton.class;
		return String.class; 
	}
	
}
