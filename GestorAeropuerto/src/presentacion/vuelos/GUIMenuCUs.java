package presentacion.vuelos;

public abstract class GUIMenuCUs {
	static GUIMenuCUs instancia = null;
	
	public static GUIMenuCUs getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIMenuCUsImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
