package negocio.vuelos;

import java.time.LocalDateTime;
import java.util.List;

public interface SAAviones {
	// BBDD
	public boolean crearAvion(String avionId, double altura, double anchura, double longitud,
			int maxPasajeros, double peso, String aerolinea);
	public boolean actualizarAvion(String avionId, double altura, double anchura, double longitud,
			int maxPasajeros, double peso, String aerolinea);
	public boolean eliminarAvion(String avionId);
	public TransferAvion getAvion(String avionId);
	public List<TransferAvion> getAllAviones();
	
	
	//
	public String checkAvion(String avionId, String altura, String anchura, String longitud,
			String maxPasajeros, String peso, String aerolinea);
}
