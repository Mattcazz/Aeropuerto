package presentacion.paneles.CUs;

public abstract class GUIAñadirPanel {

	static GUIAñadirPanel instancia= null;
	
	public static GUIAñadirPanel getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAñadirPanelImp();
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
