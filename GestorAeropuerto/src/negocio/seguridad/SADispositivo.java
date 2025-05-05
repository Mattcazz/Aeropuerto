package negocio.seguridad;

import java.util.List;

public interface SADispositivo {
    void activarDispositivo(TransferDispositivo datos) throws Exception;
    void desactivarDispositivo(TransferDispositivo datos) throws Exception;
    void configurarDispositivo(TransferDispositivo datos) throws Exception;
    List<TransferDispositivo> consultarHistorial(int dispositivoId) throws Exception;
}
