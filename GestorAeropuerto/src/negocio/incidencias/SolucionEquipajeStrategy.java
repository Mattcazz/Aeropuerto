package negocio.incidencias;

public class SolucionEquipajeStrategy implements SolucionStrategy {

    @Override
    public void aplicarSolucion(TransferIncidencia incidencia, String solucionSeleccionada, float compensacion) {
        incidencia.setSolucion(solucionSeleccionada);
        incidencia.setCompensacion(compensacion);
        incidencia.setEstado(EstadoIncidencia.RESUELTA);

    }
}
