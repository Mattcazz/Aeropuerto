package negocio.operaciones;

import java.time.LocalDateTime;
import java.util.List;

import integracion.FactoriaDAO;
import negocio.vuelos.TransferAvion;

public class EstrategiaMenosUsadas implements EstrategiaPrioridadAsignacion{

	@Override
	public List<TransferPuerta> getPrioridadPuertas(TransferAvion avion, LocalDateTime hora_llegada) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstancia().nuevoDAOPuerta().getPuertasDisponiblesEnHoraDeLlegadaMenosUsadas(avion, hora_llegada);
	}

}
