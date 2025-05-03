package presentacion.locales.CUs;

public abstract class GUIAnadirLocales {

	static GUIAnadirLocales instancia= null;
	
	public static GUIAnadirLocales getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAnadirLocalesImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
