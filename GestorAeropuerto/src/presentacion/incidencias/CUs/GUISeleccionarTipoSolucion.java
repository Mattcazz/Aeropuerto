package presentacion.incidencias.CUs;

public abstract class GUISeleccionarTipoSolucion {
	static GUISeleccionarTipoSolucion instancia = null;

	public static GUISeleccionarTipoSolucion getInstancia() {
		if (instancia == null)
			instancia = new GUISeleccionarTipoSolucionImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
