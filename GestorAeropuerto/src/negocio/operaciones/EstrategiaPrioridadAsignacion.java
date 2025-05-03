package negocio.operaciones;

import java.time.LocalDateTime;
import java.util.List;

import negocio.vuelos.TransferAvion;

public interface EstrategiaPrioridadAsignacion {
	
	public List<TransferPuerta> getPrioridadPuertas(TransferAvion avion, LocalDateTime hora_llegada);

}


