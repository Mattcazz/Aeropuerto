package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIActualizarAvion implements GUICU {
	static GUIActualizarAvion instancia = null;
	
	public static GUIActualizarAvion getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIActualizarAvionImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
