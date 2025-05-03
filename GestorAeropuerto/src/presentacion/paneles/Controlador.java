package presentacion.paneles;

import java.util.List;

import javax.swing.JFrame;

import negocio.paneles.TransferInfoVuelos;
import presentacion.paneles.ControladorImp;

public abstract class Controlador {
	
	static Controlador instancia= null;
	
	static public Controlador getInstancia()
	{
		if (instancia == null) instancia= new ControladorImp();
		
		return instancia;
	}
	
	public abstract void accion(int evento, Object datos);
}
