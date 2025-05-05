package presentacion.CUs;

public abstract class GUICrearPlaza {

	static GUICrearPlaza instancia= null;
	
	public static GUICrearPlaza getInstancia()	{
		if (instancia == null) 
			   instancia= new GUICrearPlazaImp();
		return instancia;
	}
	
	public abstract void mostrar();
	
	public abstract void actualizar(int evento, Object datos);
}