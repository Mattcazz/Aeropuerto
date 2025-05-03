package negocio.paneles;

import negocio.paneles.FactoriaSA;

public class FactoriaSAImp extends FactoriaSA {
	
	public SAPaneles nuevoSAPaneles()
	{
		return new SAPanelesImp();
	}

}
