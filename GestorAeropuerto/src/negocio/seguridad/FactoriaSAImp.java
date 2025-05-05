package negocio.seguridad;

public class FactoriaSAImp extends FactoriaSA {
	@Override
    public SAAcceso nuevoSAAccesoImp() {
        return new SAAccesoImp();
    }

    @Override
    public SADispositivo nuevoSADispositivoImp() {
        return new SADispositivoImp();
    }
}
