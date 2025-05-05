package negocio;

public class FactoriaSAImp extends FactoriaSA {
    private SAPlaza saPlaza = new SAPlazaImp();

    @Override
    public SAPlaza getSAPlaza() {
        return saPlaza;
    }
}