package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUIModificarAvion implements GUICU {
	static GUIModificarAvion instancia = null;
	
	public static GUIModificarAvion getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIModificarAvionImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
