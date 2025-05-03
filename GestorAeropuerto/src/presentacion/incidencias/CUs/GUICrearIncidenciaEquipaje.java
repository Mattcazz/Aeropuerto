package presentacion.incidencias.CUs;

public abstract class GUICrearIncidenciaEquipaje {

	static GUICrearIncidenciaEquipaje instancia = null;

	public static GUICrearIncidenciaEquipaje getInstancia() {
		if (instancia == null)
			instancia = new GUICrearIncidenciaEquipajeImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
