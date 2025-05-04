package presentacion.paneles.CUs;

import java.util.List;
import java.util.Set;

import negocio.paneles.TransferInfoVuelos;
import java.util.Set;

import javax.swing.JFrame;

import negocio.vuelos.*;

public abstract class GUISeleccionarDatos {

	static GUISeleccionarDatos instancia= null;
	
	public static GUISeleccionarDatos getInstancia()	{
		if (instancia == null) 
			   instancia= new GUISeleccionarDatosImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
	public abstract List<TransferInfoVuelos> get_datos_mostrados();
	public abstract void set_datos_mostrados(List<TransferInfoVuelos> datos);
	public abstract Set<String> getVuelosNuevos();
	public abstract JFrame getFrame();

}
