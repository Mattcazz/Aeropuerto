package presentacion.operaciones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import negocio.operaciones.TransferAsignacion;
import negocio.operaciones.TransferBloqueo;
import negocio.operaciones.TransferPuerta;

public abstract class GUIInspeccionarPuerta{
	

	static GUIInspeccionarPuerta instancia= null;
	
	public static GUIInspeccionarPuerta getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIInspeccionarPuertaImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI(List<TransferAsignacion> asignaciones, List<TransferBloqueo> bloqueos,TransferPuerta puerta);
}


    
        
    
