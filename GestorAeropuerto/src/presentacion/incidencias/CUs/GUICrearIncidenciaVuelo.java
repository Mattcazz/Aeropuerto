package presentacion.incidencias.CUs;

public abstract class GUICrearIncidenciaVuelo {

	static GUICrearIncidenciaVuelo instancia = null;

	public static GUICrearIncidenciaVuelo getInstancia() {
		if (instancia == null)
			instancia = new GUICrearIncidenciaVueloImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
