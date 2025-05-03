package integracion.operaciones;

import java.time.LocalDateTime;
import java.util.List;

import negocio.operaciones.TransferAsignacion;
import negocio.operaciones.TransferPuerta;
import negocio.vuelos.TransferVuelo;

public interface DAOAsignacion {

	public boolean crearAsignacion (TransferVuelo vuelo_id, int puerta_id); 
	public boolean borrarAsignacion(int asignacion_id);
	public boolean borrarAsignacionesEnPuerta(int puerta_id);
	public boolean cambiarEstadoAsignacion(int asignacion_id, String nuevo_estado);
	public TransferAsignacion getAsignacion(int asignacion_id);
	public List<TransferAsignacion>getAsignaciones();
	public List<TransferAsignacion> getAsignacionesInPuerta(int puerta_id);

	public List<TransferAsignacion> getAsignacionesDePuertaEnFranja(int puerta_id, LocalDateTime hora_inicio, LocalDateTime hora_fin);
}
