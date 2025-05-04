package presentacion.equipajes.CUs;

public abstract class GUIAnadirEquipaje {
	static GUIAnadirEquipaje instancia= null;
	
	public static GUIAnadirEquipaje getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIAnadirEquipajeImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
