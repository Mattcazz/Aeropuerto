package presentacion.operaciones;

import java.util.List;

import javax.swing.JFrame;

import negocio.operaciones.TransferPuerta;

public abstract class GUIPuertas {
	

	static GUIPuertasImp instancia= null;
	
	public static GUIPuertasImp getInstancia()	{
		if (instancia == null) 
			   instancia = new GUIPuertasImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract void InitGUI(List<TransferPuerta> puertas);
}
