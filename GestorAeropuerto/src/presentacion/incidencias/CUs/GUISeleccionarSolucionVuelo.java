package presentacion.incidencias.CUs;

public abstract class GUISeleccionarSolucionVuelo {

	static GUISeleccionarSolucionVuelo instancia = null;

	public static GUISeleccionarSolucionVuelo getInstancia() {
		if (instancia == null)
			instancia = new GUISeleccionarSolucionVueloImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
