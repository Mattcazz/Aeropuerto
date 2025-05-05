package presentacion.estacionamiento.CUs;

public abstract class GUIModificarPlaza {

	static GUIModificarPlaza instancia= null;
	
	public static GUIModificarPlaza getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIModificarPlazaImp();
		return instancia;
	}
	
	public abstract void mostrar();
	
	public abstract void actualizar(int evento, Object datos);
}