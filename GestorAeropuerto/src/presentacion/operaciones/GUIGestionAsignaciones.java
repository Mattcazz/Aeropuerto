package presentacion.operaciones;

import negocio.operaciones.TransferPrepararGestionAsignacion;


public abstract class GUIGestionAsignaciones {

		
	static GUIGestionAsignacionesImp instancia= null;
	
	public static GUIGestionAsignacionesImp getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIGestionAsignacionesImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI(TransferPrepararGestionAsignacion datosGUI);
	public abstract void mostrarMensaje(String mensaje);
}

