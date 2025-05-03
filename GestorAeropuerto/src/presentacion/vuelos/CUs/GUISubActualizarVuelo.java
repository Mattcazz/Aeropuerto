package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUISubActualizarVuelo implements GUICU {
	static GUISubActualizarVuelo instancia = null;
	
	public static GUISubActualizarVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUISubActualizarVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
