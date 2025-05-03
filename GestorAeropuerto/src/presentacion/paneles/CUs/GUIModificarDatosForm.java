package presentacion.paneles.CUs;
import javax.swing.table.DefaultTableModel;


public abstract class GUIModificarDatosForm {

	static GUIModificarDatosForm instancia= null;
	
	public static GUIModificarDatosForm getInstancia()	{
		if (instancia == null) 
			   instancia= new GUIModificarDatosFormImp();
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
