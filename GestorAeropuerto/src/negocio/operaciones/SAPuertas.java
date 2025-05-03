package negocio.operaciones;

import java.util.List;

public interface SAPuertas {
	public List<TransferPuerta> mostrarPuertas();
	public List<TransferPuerta> actualizarPuertasDisponibles(TransferVueloEstrategia transferVueloEstrategia);
	public PeticionPuerta guardarModificacionPuerta(PeticionPuerta transfer);
	public PeticionPuerta crearPuerta(PeticionPuerta transfer);
	public PeticionBloqueo bloquearPuerta(PeticionBloqueo bloqueo);
	public List<TransferBloqueo> getBloqueosEnPuerta(int puerta_id);
	public boolean borrarPuerta(int puerta_id);
	public void borrarBloqueo(TransferBloqueo bloqueo);
	public TransferPuerta getPuerta (int puerta_id);
}
