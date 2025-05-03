package integracion.vuelos;

import java.time.LocalDateTime;
import java.util.List;

import negocio.vuelos.TransferVuelo;

public interface DAOVuelo {
	public TransferVuelo getVuelo(String vueloId);
	public List<TransferVuelo> getAllVuelos();
	public List<TransferVuelo> getVuelosPendientes();
	public boolean insertarVuelo(TransferVuelo vuelo);
	public boolean actualizarVuelo(TransferVuelo vuelo);
	public boolean eliminarVuelo(String vueloId);
	public List<TransferVuelo> buscarVuelo(LocalDateTime tiempo, boolean antes);
}