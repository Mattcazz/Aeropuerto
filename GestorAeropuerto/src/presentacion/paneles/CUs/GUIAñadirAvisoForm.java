package presentacion.paneles.CUs;

import java.beans.PropertyChangeListener;

import javax.swing.table.DefaultTableModel;

public abstract class GUIAñadirAvisoForm {

	static GUIAñadirAvisoForm instancia= null;
	
	public static GUIAñadirAvisoForm getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAñadirAvisoFormImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
