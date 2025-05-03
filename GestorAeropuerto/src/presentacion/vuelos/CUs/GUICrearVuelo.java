package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUICrearVuelo implements GUICU {
	static GUICrearVuelo instancia = null;
	
	public static GUICrearVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUICrearVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
