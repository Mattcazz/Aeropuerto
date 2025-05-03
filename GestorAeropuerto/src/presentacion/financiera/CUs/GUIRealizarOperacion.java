package presentacion.financiera.CUs;

public abstract class GUIRealizarOperacion {
	
	static GUIRealizarOperacion instancia= null;
	
	public static GUIRealizarOperacion getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIRealizarOperacionImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);

}
