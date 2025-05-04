package presentacion.vuelos.CUs;

import presentacion.vuelos.Eventos;

public abstract class GUICrearAvion implements GUICU {
	static GUICrearAvion instancia = null;
	
	public static GUICrearAvion getInstancia()	{
		if (instancia == null) 
			   instancia = new GUICrearAvionImp();
		return instancia;
	}
	
	public abstract void actualizar(Eventos evento, Object datos);

}
