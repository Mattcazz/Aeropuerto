package negocio.vuelos;

import java.time.LocalDateTime;
import java.util.List;

public interface SAVuelos {
	// BBDD
	public boolean crearVuelo(String vueloId, String avionId, String origen, String destino,
			LocalDateTime tiempoSalida, LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip);
	public boolean actualizarVuelo(String vueloId, String avionId, String origen, String destino,
			LocalDateTime tiempoSalida, LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip);
	public boolean eliminarVuelo(String vueloId);
	public TransferVuelo getVuelo(String vueloId);
	public List<TransferVuelo> getAllVuelos();
	public List<TransferVuelo> buscarVuelo(LocalDateTime tiempo, boolean antes);
	
	// Logica
	public String checkVuelo(String vueloId, String avionId, String origen, String destino,
			LocalDateTime tiempoSalida, LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip);
}
