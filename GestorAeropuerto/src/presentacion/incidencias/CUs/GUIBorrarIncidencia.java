package presentacion.incidencias.CUs;

public abstract class GUIBorrarIncidencia {
	static GUIBorrarIncidencia instancia = null;

	public static GUIBorrarIncidencia getInstancia() {
		if (instancia == null)
			instancia = new GUIBorrarIncidenciaImp();  //
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
