package negocio.personal;

public class FactoriaSAImp extends FactoriaSA {
	public SAEmpleado nuevoSAEmpleado() {
		return new SAEmpleadoImp();
	}
}