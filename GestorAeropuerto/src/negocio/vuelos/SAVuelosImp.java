package negocio.vuelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import integracion.vuelos.DAOVuelo;
import negocio.vuelos.event.VueloEliminado;
import integracion.FactoriaDAO;
import integracion.FactoriaDAOImp;

public class SAVuelosImp implements SAVuelos {
	FactoriaDAO factoriaDAO = new FactoriaDAOImp();

	@Override
	public boolean crearVuelo(String vueloId, String avionId, String origen, String destino, LocalDateTime tiempoSalida,
	LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip) {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		TransferVuelo transferVuelo = new TransferVuelo(vueloId, avionId, origen, destino, tiempoSalida, tiempoAterrizaje, tipoVuelo, vip);
		
		return (daoVuelos.insertarVuelo(transferVuelo));
	}

	@Override
	public boolean actualizarVuelo(String vueloId, String avionId, String origen, String destino,
	LocalDateTime tiempoSalida, LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip) {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		TransferVuelo transferVuelo = new TransferVuelo(vueloId, avionId, origen, destino, tiempoSalida, tiempoAterrizaje, tipoVuelo, vip);
		
		return (daoVuelos.actualizarVuelo(transferVuelo));
	}

	@Override
	public boolean eliminarVuelo(String vueloId) {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		VueloEliminado.publish(vueloId);
		
		return (daoVuelos.eliminarVuelo(vueloId));
	}
	
	@Override
	public TransferVuelo getVuelo(String vueloId) {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		
		return (daoVuelos.getVuelo(vueloId));
	}
	
	@Override
	public List<TransferVuelo> getAllVuelos() {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		
		List<TransferVuelo> vuelos = daoVuelos.getAllVuelos();
		if (vuelos == null) {
			// Devolvemos una lista vacia en vez de null
			return (new ArrayList<TransferVuelo>());
		}
		return (vuelos);
	}

	@Override
	public List<TransferVuelo> buscarVuelo(LocalDateTime tiempo, boolean antes) {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		
		List<TransferVuelo> vuelos = daoVuelos.buscarVuelo(tiempo, antes);
		if (vuelos == null) {
			// Devolvemos una lista vacia en vez de null
			return (new ArrayList<TransferVuelo>());
		}
		return (vuelos);
	}
	
	private boolean isValidVueloId(String vueloId) {
		return (vueloId.matches("[0-9A-Z]+"));
	}
	
	private boolean isAllUpper(String str) {
		// '+' indica que deber haber 1 o mas tokens
		return (str.matches("[A-Z]+"));
	}

	@Override
	public String checkVuelo(String vueloId, String avionId, String origen, String destino, LocalDateTime tiempoSalida,
			LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip) {
		DAOVuelo daoVuelos = this.factoriaDAO.nuevoDAOVuelo();
		
		// Comprobar que el ID tiene el formato correcto
		if (!this.isValidVueloId(vueloId)) {
			return ("VueloID solo puede contener A-Z y 0-9");
		}
		
		// Comprobar que un vuelo con este ID no existe ya
		TransferVuelo transferVuelo = daoVuelos.getVuelo(vueloId);
		if (transferVuelo != null) {
			return ("Un vuelo con este ID ya existe!");
		}
		
		// Comprobamos que el origen y destino tienen el formato correcto
		if (!this.isAllUpper(origen) || !this.isAllUpper(destino)) {
			return ("Origen y Destino solo pueden tener letras mayusculas");
		}
		if (origen.length() != 3 || destino.length() != 3) {
			return ("Origen y Destino deben tener 3 letras cada uno");
		}
		
		// No pueden salir vuelos despues de que aterricen
		if (tiempoSalida.isAfter(tiempoAterrizaje) || tiempoSalida.isEqual(tiempoAterrizaje)) {
			return ("Vuelos no pueden aterrizar antes de salir!");
		}
		
		
		return null;
	}

}
