package integracion.vuelos;

import java.util.List;

import negocio.vuelos.TransferAvion;
import negocio.vuelos.TransferVuelo;

public interface DAOAvion {
	public TransferAvion getAvion(String avion_id);
	public List<TransferAvion> getAllAviones();
	public boolean insertarAvion(TransferAvion avion);
	public boolean actualizarAvion(TransferAvion avion);
	public boolean eliminarAvion(String avionId);
	public List<String> getAllAvionIds();
}