package negocio.financiera;

public class FactoriaSAImp extends FactoriaSA {

	@Override
	public SAFinanzas createSAFinanzas() {
		return new SAFinanzasImp();
	}

	@Override
	public SAFlujoCaja createSAFlujoCaja() {
		return new SAFlujoCajaImp();
	}

	@Override
	public SACuentaBancaria createSACuentaBancaria() {
		return new SACuentaBancariaImp();
	}
	
	@Override
	public SAIntegracionSubsistemas createSAIntegracionSubsistemas() {
		return new SAIntegracionSubsistemasImp();
	}
}
