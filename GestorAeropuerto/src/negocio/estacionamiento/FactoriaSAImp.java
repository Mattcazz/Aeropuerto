package negocio.estacionamiento;

public class FactoriaSAImp extends FactoriaSA {
    private SAPlaza saPlaza = new SAPlazaImp();

    @Override
    public SAPlaza getSAPlaza() {
        return saPlaza;
    }
}