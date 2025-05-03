package presentacion.paneles.CUs;

public abstract class GUIEliminarPanel {

	static GUIEliminarPanel instancia= null;
	
	public static GUIEliminarPanel getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIEliminarPanelImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
