package negocio.locales;

import java.util.List;

public interface SALocales {
    int anadir(TransferLocal l);
    boolean eliminar(int id);
    boolean modificar(TransferLocal l);
    List<TransferLocal> mostrar();
    List<TransferLocal> filtrarPorNombre(String subcadena);
    List<TransferLocal> filtrarAvanzado(FiltroLocal filtro);

}
