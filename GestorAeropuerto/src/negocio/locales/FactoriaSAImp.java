package negocio.locales;

public class FactoriaSAImp extends FactoriaSA {
    public SALocales crearSALocales() {
        return new SALocalesImp();
    }
}
