package presentacion.paneles.CUs;

import javax.swing.table.DefaultTableModel;

public abstract class GUIModificarDatos {

	static GUIModificarDatos instancia= null;
	
	public static GUIModificarDatos getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIModificarDatosImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract DefaultTableModel get_modelo();
}
