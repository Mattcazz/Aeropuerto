package presentacion.incidencias.CUs;

public abstract class GUIVisualizarIncidenciasVuelos {
	static GUIVisualizarIncidenciasVuelos instancia = null;

	public static GUIVisualizarIncidenciasVuelos getInstancia() {
		if (instancia == null)
			instancia = new GUIVisualizarIncidenciasVuelosImp(); 
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
