package presentacion.operaciones;


import negocio.operaciones.TransferPrepararGestionAsignacion;

public abstract class GUICrearPuerta {

static GUICrearPuertaImp instancia= null;
	
	public static GUICrearPuertaImp getInstancia()	{
		if (instancia == null) 
			   instancia = new GUICrearPuertaImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI();
	public abstract void mostrarMensaje(String mensaje);
}
