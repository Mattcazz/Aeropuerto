package negocio.estacionamiento;

import java.util.List;

public interface SAPlaza {
    List<TransferPlaza> listarPlazas() throws Exception;
    TransferPlaza obtenerPlaza(int numero) throws Exception;
    void llegaVehiculo(int numero, String matricula) throws Exception;
    double calcularCoste(int numero) throws Exception;
    void abandonaVehiculo(int numero) throws Exception;
    void mantenimientoPlaza(int numero) throws Exception;
    void iniciarMantenimiento(int numero) throws Exception;
    void finalizarMantenimiento(int numero) throws Exception;
    void crearPlaza(int numero,EstadoPlaza estado)throws Exception;
    void eliminarPlaza(int numero) throws Exception;
    void modificarPlaza(int numero, EstadoPlaza nuevoEstado) throws Exception;

    void addObserver(PlazaObserver observer);
    void removeObserver(PlazaObserver observer);
}