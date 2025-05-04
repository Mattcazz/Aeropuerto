package presentacion.equipajes.CUs;

public abstract class GUIModificacionEquipaje {
	static GUIModificacionEquipaje instancia= null;
	
	public static GUIModificacionEquipaje getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIModificacionEquipajeImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
