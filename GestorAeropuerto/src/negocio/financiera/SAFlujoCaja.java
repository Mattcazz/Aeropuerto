package negocio.financiera;

import java.util.List;

public interface SAFlujoCaja {

    public void registrarFlujo(TFlujoCaja flujo) throws Exception;

    public void completarFlujo(int id) throws Exception;

    public List<TFlujoCaja> listarFlujos() throws Exception;

    public List<TFlujoCaja> listarPendientes();
    
    public boolean existeFlujoSimilar(TFlujoCaja flujo);

}
