package negocio.incidencias;

public class FactoriaSAImp extends FactoriaSA {

	public SAGestionIncidencias nuevoSAGestionIncidencias() {
		return new SAGestionIncidenciasImp();
	}

}
