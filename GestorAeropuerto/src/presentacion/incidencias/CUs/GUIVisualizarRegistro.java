package presentacion.incidencias.CUs;

public abstract class GUIVisualizarRegistro {
	static GUIVisualizarRegistro instancia = null;

	public static GUIVisualizarRegistro getInstancia() {
		if (instancia == null)
			instancia = new GUIVisualizarRegistroImp(); 
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
