package integracion.operaciones;

import java.time.LocalDateTime;
import java.util.List;

import negocio.vuelos.TransferAvion;
import negocio.operaciones.TransferBloqueo;
import negocio.operaciones.TransferPuerta;
import negocio.vuelos.TransferVuelo;

public interface DAOPuerta {
	
	public TransferPuerta getPuerta(int puerta_id);
	public List<TransferPuerta> getPuertas();
	public List<TransferPuerta> getPuertasInTerminal(int terminal);
	public List<TransferPuerta> getPuertasDisponiblesEnHoraDeLlegada(TransferAvion avion, LocalDateTime hora_llegada);
	public TransferPuerta getPuertaMasCercana();	
	public List<TransferPuerta>  getPuertasDisponiblesEnHoraDeLlegadaAerolineaPriorizada(TransferAvion avion,LocalDateTime hora_llegada);
	public List<TransferPuerta>  getPuertasDisponiblesEnHoraDeLlegadaMenosUsadas(TransferAvion avion,LocalDateTime hora_llegada);
	public List<TransferPuerta>  getPuertasDisponiblesEnHoraDeLlegadaMayorPeso(TransferAvion avion,LocalDateTime hora_llegada);
	public boolean modificarPuerta(TransferPuerta puertaModificada);
	public boolean crearPuerta(TransferPuerta puerta);
	public boolean crearBloqueoEnPuerta(TransferBloqueo bloqueo);
	public boolean borrarBloqueoEnPuerta(TransferBloqueo bloqueo);
	public boolean borrarPuerta(int puerta_id);
	public List<TransferBloqueo> getBloqueosEnPuerta(int puerta_id);
	public boolean borrarBloqueosEnPuerta(int puerta_id);
	public TransferBloqueo getBloqueoDePuertaEnHora(int puerta_id, LocalDateTime hora_inicio, LocalDateTime hora_fin);
}
