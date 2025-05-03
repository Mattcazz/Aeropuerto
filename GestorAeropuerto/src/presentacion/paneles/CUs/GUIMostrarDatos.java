package presentacion.paneles.CUs;

import java.util.Set;

public abstract class GUIMostrarDatos {

	static GUIMostrarDatos instancia= null;
	
	public static GUIMostrarDatos getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIMostrarDatosImp();
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);
	public abstract Set<Integer> id_paneles_existentes();
}
