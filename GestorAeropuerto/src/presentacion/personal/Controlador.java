package presentacion.personal;

public abstract class Controlador {
	
	static Controlador instancia= null;
	
	static public Controlador getInstancia()
	{
		if (instancia == null) instancia= new ControladorImp();
		
		return instancia;
	}
	
	public abstract int accion(int evento, Object datos);
}
