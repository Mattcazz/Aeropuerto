package presentacion.paneles.CUs;

import javax.swing.table.DefaultTableModel;

public abstract class GUIEliminarDatos {

	static GUIEliminarDatos instancia= null;
	
	public static GUIEliminarDatos getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIEliminarDatosImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract DefaultTableModel get_modelo();
}
