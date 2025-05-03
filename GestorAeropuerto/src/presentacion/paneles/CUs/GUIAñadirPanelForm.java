package presentacion.paneles.CUs;

public abstract class GUIAñadirPanelForm {

	static GUIAñadirPanelForm instancia= null;
	
	public static GUIAñadirPanelForm getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAñadirPanelFormImp();
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
