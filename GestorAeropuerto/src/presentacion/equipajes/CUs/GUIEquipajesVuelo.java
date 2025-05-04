package presentacion.equipajes.CUs;

public abstract class GUIEquipajesVuelo {
	static GUIEquipajesVuelo instancia= null;
	
	public static GUIEquipajesVuelo getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIEquipajesVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
