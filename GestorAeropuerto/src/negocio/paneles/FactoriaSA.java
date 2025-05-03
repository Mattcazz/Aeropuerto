package negocio.paneles;

import negocio.paneles.FactoriaSAImp;

public abstract class FactoriaSA {
	
	static FactoriaSA instancia= null;
	
	static public FactoriaSA getInstancia()
	{
		if (instancia == null) instancia= new FactoriaSAImp();
		
		return instancia;
	}
	
	abstract public SAPaneles nuevoSAPaneles();

}
