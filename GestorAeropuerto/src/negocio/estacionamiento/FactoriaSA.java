package negocio.estacionamiento;

public abstract class FactoriaSA {
    private static FactoriaSA instancia = new FactoriaSAImp();
    public static FactoriaSA getInstancia() {
        return instancia;
    }
    public abstract SAPlaza getSAPlaza();
}