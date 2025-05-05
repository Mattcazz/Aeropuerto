package presentacion.seguridad.CUs;
import javax.swing.JFrame;

import presentacion.seguridad.CUs.GUIMenuCUsImp;

public abstract class GUIMenuCUs {

	static GUIMenuCUs instancia= null;
	
	public static GUIMenuCUs getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIMenuCUsImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract JFrame getFrame();
}