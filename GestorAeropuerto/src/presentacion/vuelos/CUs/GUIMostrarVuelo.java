package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIMostrarVuelo implements GUICU {
	static GUIMostrarVuelo instancia = null;
	
	public static GUIMostrarVuelo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIMostrarVueloImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
