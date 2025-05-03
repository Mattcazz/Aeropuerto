package presentacion.financiera.CUs;

public abstract class GUICuentasBancarias {

	static GUICuentasBancarias instancia = null;

	public static GUICuentasBancarias getInstancia() {
		if (instancia == null)
			instancia = new GUICuentasBancariasImp();
		return instancia;
	}

	public abstract void actualizar(int evento, Object datos);

}
