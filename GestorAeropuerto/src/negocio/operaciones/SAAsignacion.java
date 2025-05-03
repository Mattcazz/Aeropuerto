package negocio.operaciones;

import java.util.List;

public interface SAAsignacion {
	public List<TransferAsignacion>getAsignacionesInPuerta(int puerta_id);
	
	public TransferPrepararGestionAsignacion prepareGestionarAsignaciones();
	public TransferActualizarGUIGestionAsignaciones borrarAsignacion(TransferAsignacion asignacion);
	public List<TransferAsignacion> getAsignaciones();
	public TransferActualizarGUIGestionAsignaciones crearAsignacion(TransferCrearAsignacion request);
}
