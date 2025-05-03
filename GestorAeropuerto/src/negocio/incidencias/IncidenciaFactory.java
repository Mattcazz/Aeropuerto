package negocio.incidencias;

import java.sql.Date;

public class IncidenciaFactory {

    // Método para crear una incidencia según el tipo
    public static TransferIncidencia crearIncidencia(TipoIncidencia tipo, Date fecha, String descripcion, EstadoIncidencia estado, String solucion, float compensacion, String idEspecifico) {
        switch (tipo) {
            case VUELO:
                // Crear y devolver una IncidenciaVuelo
                return new TransferIncidenciaVuelo(tipo, fecha, descripcion, estado, solucion, compensacion, idEspecifico);
            case EQUIPAJE:
                // Crear y devolver una IncidenciaEquipaje
                return new TransferIncidenciaEquipaje(tipo, fecha, descripcion, estado, solucion, compensacion, idEspecifico);
            default:
                throw new IllegalArgumentException("Tipo de incidencia desconocido");
        }
    }
}
