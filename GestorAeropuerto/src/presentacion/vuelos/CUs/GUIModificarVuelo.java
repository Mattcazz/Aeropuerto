package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIModificarVuelo implements GUICU {
	static GUIModificarVuelo instancia = null;
	
	public static GUIModificarVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIModificarVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
