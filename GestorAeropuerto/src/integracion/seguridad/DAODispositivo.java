package integracion.seguridad;

import negocio.seguridad.TransferDispositivo;
import negocio.seguridad.TransferEventoDispositivo;

import java.sql.SQLException;
import java.util.List;

public interface DAODispositivo {
    boolean activar(TransferDispositivo t);
    boolean desactivar(TransferDispositivo t);
    boolean configurar(TransferDispositivo t);
    List<TransferDispositivo> consultarHistorico(int dispositivoId);
    List<TransferEventoDispositivo> getEventosHistorico(int dispositivoId);
    boolean registrarEvento(TransferDispositivo t, String accion);
    TransferDispositivo getDispositivo(int id) throws SQLException;
}
