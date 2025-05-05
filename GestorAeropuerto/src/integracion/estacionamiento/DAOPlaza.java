package integracion.estacionamiento;

import java.sql.SQLException;
import java.util.List;
import negocio.estacionamiento.EstadoPlaza;
import negocio.estacionamiento.TransferPlaza;

public interface DAOPlaza {
    List<TransferPlaza> obtenerPlazas() throws SQLException;
    TransferPlaza obtenerPlaza(int numero) throws Exception;
    boolean llegaVehiculo(int numero, String matricula) throws Exception;
    boolean abandonaVehiculo(int numero) throws Exception;
    boolean mantenimientoPlaza(int numero) throws Exception;
    boolean iniciarMantenimiento(int numero) throws Exception;
    boolean finalizarMantenimiento(int numero) throws Exception;
    boolean crearPlaza(TransferPlaza p) throws Exception;
    boolean eliminarPlaza(int numero) throws Exception;
    boolean modificarPlaza(int numero, EstadoPlaza nuevoEstado) throws Exception;

}