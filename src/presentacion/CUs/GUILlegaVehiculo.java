package presentacion.CUs;

public abstract class GUILlegaVehiculo {

	static GUILlegaVehiculo instancia= null;
	
	public static GUILlegaVehiculo getInstancia()	{
		if (instancia == null) 
			   instancia= new GUILlegaVehiculoImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);

	protected abstract void mostrar();
}
