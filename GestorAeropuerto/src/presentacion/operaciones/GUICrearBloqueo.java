package presentacion.operaciones;

import negocio.operaciones.TransferPuerta;

public abstract class GUICrearBloqueo {
	
static GUICrearBloqueo instancia= null;
	
	public static GUICrearBloqueo getInstancia()	{
		if (instancia == null) 
			   instancia = new GUICrearBloqueoImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI(TransferPuerta puerta);
	public abstract void mostrarMensaje(String mensaje);
}
