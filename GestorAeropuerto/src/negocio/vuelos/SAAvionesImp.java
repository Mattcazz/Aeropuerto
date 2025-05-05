package negocio.vuelos;

import java.util.ArrayList;
import java.util.List;

import integracion.vuelos.DAOAvion;
import integracion.vuelos.DAOVuelo;
import negocio.vuelos.event.AvionEliminado;
import integracion.FactoriaDAO;
import integracion.FactoriaDAOImp;

public class SAAvionesImp implements SAAviones {
	FactoriaDAO factoriaDAO = new FactoriaDAOImp();

	@Override
	public boolean crearAvion(String avionId, double altura, double anchura, double longitud,
	int maxPasajeros, double peso, String aerolinea) {
		DAOAvion daoAvion = this.factoriaDAO.nuevoDAOAvion();
		TransferAvion transferAvion = new TransferAvion(avionId, altura, anchura, longitud, maxPasajeros, peso, aerolinea);
		
		return (daoAvion.insertarAvion(transferAvion));
	}

	@Override
	public boolean actualizarAvion(String avionId, double altura, double anchura, double longitud,
			int maxPasajeros, double peso, String aerolinea) {
		DAOAvion daoAvion = this.factoriaDAO.nuevoDAOAvion();
		TransferAvion transferAvion = new TransferAvion(avionId, altura, anchura, longitud, maxPasajeros, peso, aerolinea);
		
		return (daoAvion.actualizarAvion(transferAvion));
	}

	@Override
	public boolean eliminarAvion(String avionId) {
		DAOAvion daoAvion = this.factoriaDAO.nuevoDAOAvion();
		AvionEliminado.publish(avionId);
		
		return (daoAvion.eliminarAvion(avionId));
	}
	
	public TransferAvion getAvion(String avionId) {
		DAOAvion daoAvion = this.factoriaDAO.nuevoDAOAvion();
		
		return (daoAvion.getAvion(avionId));
	}
	
	public List<TransferAvion> getAllAviones() {
		DAOAvion daoAvion = this.factoriaDAO.nuevoDAOAvion();
		
		List<TransferAvion> aviones = daoAvion.getAllAviones();
		if (aviones == null) {
			// Devolvemos una lista vacia en vez de null
			return (new ArrayList<TransferAvion>());
		}
		return (aviones);
	}
	
	private boolean isValidAvionId(String avionId) {
		return (avionId.matches("[0-9A-Z]+"));
	}
	
	@Override
	public String checkAvion(String avionId, String altura, String anchura, String longitud,
			String maxPasajeros, String peso, String aerolinea) {
		DAOAvion daoAvion = this.factoriaDAO.nuevoDAOAvion();
		// Comprobar que el ID tiene el formato correcto
		if (!this.isValidAvionId(avionId)) {
			return ("VueloID solo puede contener A-Z y 0-9");
		}
			
		// Comprobar que las dimensiones y el peso son Doubles validos
		try {
			Double.parseDouble(altura);
			Double.parseDouble(anchura);
			Double.parseDouble(longitud);
			Double.parseDouble(peso);
		}
		catch (NumberFormatException e) {
			return ("Los campos [Altura, Anchura, Longitud, Peso] deben contener numeros validos\nUse el formato `1234.56`");
		}
		
		// Comprobar que Max Pasajeroses un numero valido
		try {
			Integer.parseInt(maxPasajeros);
		}
		catch (NumberFormatException e) {
			return ("Max Pasajeros debe contener un numero valido sin decimales");
		}
		

		return null;
	}
}
