package presentacion.operaciones;

import java.util.List;

import negocio.operaciones.TransferPuerta;

public abstract class GUIVerPuerta {

	static GUIVerPuertaImp instancia= null;
	
	public static GUIVerPuertaImp getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIVerPuertaImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI(TransferPuerta puerta);

}
