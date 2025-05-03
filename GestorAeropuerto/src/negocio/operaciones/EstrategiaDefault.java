package negocio.operaciones;

import java.time.LocalDateTime;
import java.util.List;

import integracion.FactoriaDAO;
import negocio.vuelos.TransferAvion;

public class EstrategiaDefault implements EstrategiaPrioridadAsignacion{

	@Override
	public List<TransferPuerta> getPrioridadPuertas(TransferAvion avion, LocalDateTime hora_llegada) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstancia().nuevoDAOPuerta().getPuertasDisponiblesEnHoraDeLlegada(avion, hora_llegada);
	}

}
