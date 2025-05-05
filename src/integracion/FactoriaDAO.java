package integracion;

public abstract class FactoriaDAO {
    private static FactoriaDAO instancia;

    public static FactoriaDAO getInstancia() {
        if (instancia == null) {
            instancia = new FactoriaDAOImp();
        }
        return instancia;
    }

    public abstract DAOPlaza getDAOPlaza();
}