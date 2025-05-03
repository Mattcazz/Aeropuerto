package presentacion.vuelos;

public abstract class Controlador {
	
	static Controlador instancia = null;
	
	static public Controlador getInstancia() {
		if (instancia == null) {
			instancia = new ControladorImp();
		}
		return (instancia);
	}
	
	public abstract void accion(Eventos evento, Object datos);
}
