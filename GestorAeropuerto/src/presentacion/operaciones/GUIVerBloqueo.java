package presentacion.operaciones;

import negocio.operaciones.TransferBloqueo;

public abstract class GUIVerBloqueo {

	static GUIVerBloqueo instancia= null;
	
	public static GUIVerBloqueo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIVerBloqueoImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI(TransferBloqueo bloqueo);
}
