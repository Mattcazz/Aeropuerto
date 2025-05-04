package presentacion.equipajes.CUs;

public abstract class GUISeleccionVuelo {
	static GUISeleccionVuelo instancia= null;
	
	public static GUISeleccionVuelo getInstancia()	{
		if (instancia == null) 
			   instancia= new GUISeleccionVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
