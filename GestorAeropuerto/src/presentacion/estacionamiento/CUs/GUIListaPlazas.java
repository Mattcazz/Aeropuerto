package presentacion.estacionamiento.CUs;

public abstract class GUIListaPlazas {

	static GUIListaPlazas instancia= null;
	
	public static GUIListaPlazas getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIListaPlazasImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
    public abstract void mostrar();

}
