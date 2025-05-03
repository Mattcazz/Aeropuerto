package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIBuscarVuelo implements GUICU {
	static GUIBuscarVuelo instancia = null;
	
	public static GUIBuscarVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIBuscarVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
