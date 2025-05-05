package presentacion.CUs;

public abstract class GUIMantenimientoPlaza {

	static GUIMantenimientoPlaza instancia= null;
	
	public static GUIMantenimientoPlaza getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIMantenimientoPlazaImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);

	protected abstract void mostrar();
}
