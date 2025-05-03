package integracion.vuelos;

import java.util.List;

import negocio.vuelos.TransferAvion;

public interface DAOAvion {
	public TransferAvion getAvion(String avion_id);
	public boolean insertarAvion(TransferAvion avion);
	public boolean actualizarAvion(TransferAvion avion);
	public boolean eliminarAvion(String avionId);
	public List<String> getAllAvionIds();
}