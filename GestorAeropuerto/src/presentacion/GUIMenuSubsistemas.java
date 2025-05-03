package presentacion;

public abstract class GUIMenuSubsistemas {
	static GUIMenuSubsistemas instancia = null;
	
	public static GUIMenuSubsistemas getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIMenuSubsistemasImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);

}
