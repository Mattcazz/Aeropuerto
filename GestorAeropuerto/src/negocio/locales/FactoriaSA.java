package negocio.locales;

public abstract class FactoriaSA {
    private static FactoriaSA instancia;

    public static FactoriaSA getInstancia() {
        if (instancia == null)
            instancia = new FactoriaSAImp();
        return instancia;
    }

    public abstract SALocales crearSALocales();
}
