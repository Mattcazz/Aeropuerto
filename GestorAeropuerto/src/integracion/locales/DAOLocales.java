package integracion.locales;

import java.util.List;

import negocio.locales.FiltroLocal;
import negocio.locales.TransferLocal;

public interface DAOLocales {
    int insertar(TransferLocal l);
    boolean eliminar(int id);
    boolean modificar(TransferLocal l);
    List<TransferLocal> mostrarTodos();
    List<TransferLocal> filtrarPorNombre(String subcadena);
    List<TransferLocal> filtrarAvanzado(FiltroLocal filtro);

}
