package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIActualizarVuelo implements GUICU {
	static GUIActualizarVuelo instancia = null;
	
	public static GUIActualizarVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIActualizarVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
