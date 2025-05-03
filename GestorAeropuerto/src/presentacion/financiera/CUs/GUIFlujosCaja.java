package presentacion.financiera.CUs;

public abstract class GUIFlujosCaja {
	
	static GUIFlujosCaja instancia= null;
	
	public static GUIFlujosCaja getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIFlujosCajaImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);

}
