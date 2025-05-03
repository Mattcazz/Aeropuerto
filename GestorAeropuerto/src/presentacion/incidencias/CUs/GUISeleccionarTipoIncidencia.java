package presentacion.incidencias.CUs;

public abstract class GUISeleccionarTipoIncidencia {
	
	static GUISeleccionarTipoIncidencia instancia = null;

	public static GUISeleccionarTipoIncidencia getInstancia() {
		if (instancia == null)
			instancia = new GUISeleccionarTipoIncidenciaImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
