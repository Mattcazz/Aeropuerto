
package negocio.vuelos;

import java.time.LocalDateTime;

public class TransferVuelo {
	
	private String vueloId;
	private String avionId;
	private String origen;
	private String destino;
	private LocalDateTime tiempoSalida;
	private LocalDateTime tiempoAterrizaje;
	private String tipoVuelo;
	private boolean vip;
	
	public TransferVuelo() { }
	public TransferVuelo(String vueloId, String avionId, String origen, String destino, LocalDateTime tiempoSalida,
			LocalDateTime tiempoAterrizaje, String tipoVuelo, boolean vip) {
		this.vueloId = vueloId;
		this.avionId = avionId;
		this.origen = origen;
		this.destino = destino;
		this.tiempoSalida = tiempoSalida;
		this.tiempoAterrizaje = tiempoAterrizaje;
		this.tipoVuelo = tipoVuelo;
		this.vip = vip;
	}

	public String getVueloId() {
		return vueloId;
	}

	public String getAvionId() {
		return avionId;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getTiempoSalida() {
		return tiempoSalida;
	}

	public LocalDateTime getTiempoAterrizaje() {
		return tiempoAterrizaje;
	}

	public String getTipoVuelo() {
		return tipoVuelo;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVueloId(String vueloId) {
		this.vueloId = vueloId;
	}

	public void setAvionId(String avionId) {
		this.avionId = avionId;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setTiempoSalida(LocalDateTime tiempoSalida) {
		this.tiempoSalida = tiempoSalida;
	}

	public void setTiempoAterrizaje(LocalDateTime tiempoAterrizaje) {
		this.tiempoAterrizaje = tiempoAterrizaje;
	}

	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
}
