package negocio.incidencias;

public interface SolucionStrategy {
    void aplicarSolucion(TransferIncidencia incidencia, String solucionSeleccionada, float compensacion);
}
