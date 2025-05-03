package presentacion.locales.CUs;

public abstract class GUIFiltrarLocales {

	static GUIFiltrarLocales instancia= null;
	
	public static GUIFiltrarLocales getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIFiltrarLocalesImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
