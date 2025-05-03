package negocio.financiera;

public abstract class FactoriaSA {
    private static FactoriaSA instancia;

    public static FactoriaSA getInstancia() {
        if (instancia == null) instancia = new FactoriaSAImp();
        return instancia;
    }

    public abstract SAFinanzas createSAFinanzas();
    public abstract SAFlujoCaja createSAFlujoCaja();
    public abstract SACuentaBancaria createSACuentaBancaria();
    public abstract SAIntegracionSubsistemas createSAIntegracionSubsistemas();
    
}
