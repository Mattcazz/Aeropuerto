package presentacion.incidencias.CUs;

public abstract class GUISeleccionarTipoIncidenciaObservar {
	static GUISeleccionarTipoIncidenciaObservar instancia = null;

	public static GUISeleccionarTipoIncidenciaObservar getInstancia() {
		if (instancia == null)
			instancia = new GUISeleccionarTipoIncidenciaObservarImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
