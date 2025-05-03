package negocio.operaciones;

import negocio.operaciones.FactoriaSAImp;

public abstract class FactoriaSA {
	
	static FactoriaSA instancia= null;
	
	static public FactoriaSA getInstancia()
	{
		if (instancia == null) instancia= new FactoriaSAImp();
		
		return instancia;
	}
	
	abstract public SAPuertas nuevoSAPuertas();
	abstract public SAAsignacion nuevoSAAsignacion();
	

}
