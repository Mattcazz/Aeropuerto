package presentacion.incidencias.CUs;

public abstract class GUIVisualizarIncidenciasEquipajes {
	static GUIVisualizarIncidenciasEquipajes instancia = null;

	public static GUIVisualizarIncidenciasEquipajes getInstancia() {
		if (instancia == null)
			instancia = new GUIVisualizarIncidenciasEquipajesImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
