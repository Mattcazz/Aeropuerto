package negocio.locales;

import java.util.List;
import integracion.locales.DAOLocales;
import integracion.FactoriaDAO;

public class SALocalesImp implements SALocales {

    private DAOLocales dao = FactoriaDAO.getInstancia().crearDAOLocales();

    @Override
    public int anadir(TransferLocal l) {
        return dao.insertar(l);
    }

    @Override
    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }

    @Override
    public boolean modificar(TransferLocal l) {
        return dao.modificar(l);
    }

    @Override
    public List<TransferLocal> mostrar() {
        return dao.mostrarTodos();
    }

    @Override
    public List<TransferLocal> filtrarPorNombre(String subcadena) {
        return dao.filtrarPorNombre(subcadena);
    }
    
    @Override
    public List<TransferLocal> filtrarAvanzado(FiltroLocal filtro) {
        return dao.filtrarAvanzado(filtro);
    }

}
