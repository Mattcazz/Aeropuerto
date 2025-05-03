package negocio.operaciones;

import negocio.operaciones.FactoriaSA;

public class FactoriaSAImp extends FactoriaSA {

	
	public SAPuertas nuevoSAPuertas() {
		return new SAPuertasImp();
	}

	@Override
	public SAAsignacionImp nuevoSAAsignacion() {
		// TODO Auto-generated method stub
		return new SAAsignacionImp();
	}

}
