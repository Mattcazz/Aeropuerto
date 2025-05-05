package integracion;

public class FactoriaDAOImp extends FactoriaDAO {

    @Override
    public DAOPlaza getDAOPlaza() {
        return new DAOPlazaProxy();
    }
}
