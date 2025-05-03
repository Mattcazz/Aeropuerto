package presentacion.paneles.CUs;

public abstract class GUIFiltrarDatos {

	static GUIFiltrarDatos instancia= null;
	
	public static GUIFiltrarDatos getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIFiltrarDatosImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}