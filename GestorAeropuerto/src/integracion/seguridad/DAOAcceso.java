package integracion.seguridad;

import negocio.seguridad.TransferAcceso;
import java.util.List;

public interface DAOAcceso {
    boolean crearAcceso(TransferAcceso t);
    boolean eliminarAcceso(int id);
    boolean modificarAcceso(TransferAcceso t);
    boolean verificarAcceso(int id);
    List<TransferAcceso> listarHistorial();
    boolean guardarHistoricoAcceso(int accesoId, String descripcion);
}
