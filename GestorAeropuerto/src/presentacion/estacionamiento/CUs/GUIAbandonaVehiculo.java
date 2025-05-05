package presentacion.estacionamiento.CUs;

public abstract class GUIAbandonaVehiculo {

	static GUIAbandonaVehiculo instancia= null;
	
	public static GUIAbandonaVehiculo getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAbandonaVehiculoImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);

	protected abstract void mostrar();
}
