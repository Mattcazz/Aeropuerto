package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIEliminarVuelo implements GUICU {
	static GUIEliminarVuelo instancia = null;
	
	public static GUIEliminarVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIEliminarVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
