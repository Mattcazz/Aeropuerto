package presentacion.locales.CUs;

import javax.swing.JFrame;

public abstract class GUIModificarLocales {

	static GUIModificarLocales instancia= null;
	
	public static GUIModificarLocales getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIModificarLocalesImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract JFrame getFrame();
	public abstract void mostrar();

}
