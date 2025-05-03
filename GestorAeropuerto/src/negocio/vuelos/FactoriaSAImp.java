package negocio.vuelos;

public class FactoriaSAImp extends FactoriaSA {
	
	public SAVuelos nuevoSAVuelos() {
		return (new SAVuelosImp());
	}

	public SAAviones nuevoSAAviones() {
		return (new SAAvionesImp());
	}

}
