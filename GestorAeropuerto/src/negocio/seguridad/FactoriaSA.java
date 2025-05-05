package negocio.seguridad;

public abstract class FactoriaSA {
	private static FactoriaSA instancia;

    public static FactoriaSA getInstancia() {
        if (instancia == null) instancia = new FactoriaSAImp();
        return instancia;
    }

    public abstract SAAcceso nuevoSAAccesoImp();
    public abstract SADispositivo nuevoSADispositivoImp();
}
