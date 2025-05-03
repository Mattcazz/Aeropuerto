package presentacion.paneles.CUs;

public abstract class GUIOperarPaneles {

	static GUIOperarPaneles instancia= null;
	
	public static GUIOperarPaneles getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIOperarPanelesImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
