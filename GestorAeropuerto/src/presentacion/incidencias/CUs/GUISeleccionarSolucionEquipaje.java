package presentacion.incidencias.CUs;

public abstract class GUISeleccionarSolucionEquipaje {
	
	static GUISeleccionarSolucionEquipaje instancia = null;

	public static GUISeleccionarSolucionEquipaje getInstancia() {
		if (instancia == null)
			instancia = new GUISeleccionarSolucionEquipajeImp();  
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
}
