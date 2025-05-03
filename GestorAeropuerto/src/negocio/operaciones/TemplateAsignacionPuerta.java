package negocio.operaciones;

import java.time.LocalDateTime;
import java.util.List;

import integracion.FactoriaDAO;
import integracion.vuelos.DAOAvion;
import negocio.vuelos.TransferAvion;
import negocio.vuelos.TransferVuelo;

public abstract class TemplateAsignacionPuerta {
	
	
	private DAOAvion daoAvion;
	
	public TemplateAsignacionPuerta() {
		daoAvion = FactoriaDAO.getInstancia().nuevoDAOAvion();
	}
	
	public List<TransferPuerta> getPuertasDisponibles(TransferVuelo vuelo, LocalDateTime hora_llegada, EstrategiaPrioridadAsignacion estrategia) {
		
		TransferAvion avion = daoAvion.getAvion(vuelo.getAvionId());
		List<TransferPuerta> puertasDisponibles = estrategia.getPrioridadPuertas(avion, hora_llegada);
		return filtrarPuertasDisponibles(puertasDisponibles);
		
	}
	
	public abstract List<TransferPuerta> filtrarPuertasDisponibles(List<TransferPuerta> puertasDisponibles);
	
}
