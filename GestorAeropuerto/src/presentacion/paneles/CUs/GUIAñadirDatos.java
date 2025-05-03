package presentacion.paneles.CUs;

import java.beans.PropertyChangeListener;

import javax.swing.table.DefaultTableModel;

public abstract class GUIAñadirDatos {

	static GUIAñadirDatos instancia= null;
	
	public static GUIAñadirDatos getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAñadirDatosImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract DefaultTableModel get_modelo();
	public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
