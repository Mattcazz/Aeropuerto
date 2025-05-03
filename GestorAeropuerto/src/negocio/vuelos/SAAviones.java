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
	
	
	//
	public List<String> getAllAvionIds();

}
