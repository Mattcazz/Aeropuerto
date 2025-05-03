package negocio.vuelos;

import java.util.List;

import integracion.vuelos.DAOAvion;
import integracion.vuelos.DAOVuelo;
import integracion.FactoriaDAO;
import integracion.FactoriaDAOImp;

public class SAAvionesImp implements SAAviones {
	FactoriaDAO factoriaDAO = new FactoriaDAOImp();

	@Override
	public boolean crearAvion(String avionId, double altura, double anchura, double longitud,
	int maxPasajeros, double peso, String aerolinea) {
		DAOAvion daoAviones = this.factoriaDAO.nuevoDAOAvion();
		TransferAvion transferAvion = new TransferAvion(avionId, altura, anchura, longitud, maxPasajeros, peso, aerolinea);
		
		return (daoAviones.insertarAvion(transferAvion));
	}

	@Override
	public boolean actualizarAvion(String avionId, double altura, double anchura, double longitud,
			int maxPasajeros, double peso, String aerolinea) {
		DAOAvion daoAviones = this.factoriaDAO.nuevoDAOAvion();
		TransferAvion transferAvion = new TransferAvion(avionId, altura, anchura, longitud, maxPasajeros, peso, aerolinea);
		
		return (daoAviones.actualizarAvion(transferAvion));
	}

	@Override
	public boolean eliminarAvion(String avionId) {
		DAOAvion daoAviones = this.factoriaDAO.nuevoDAOAvion();
		
		return (daoAviones.eliminarAvion(avionId));
	}

	@Override
	public List<String> getAllAvionIds() {
		DAOAvion daoAviones = this.factoriaDAO.nuevoDAOAvion();
		
		return (daoAviones.getAllAvionIds());
	}
}
