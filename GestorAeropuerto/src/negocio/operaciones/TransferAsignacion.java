package negocio.operaciones;

import java.time.LocalDateTime;

public class TransferAsignacion {

	private int asiganacionId;
	private String vueloId;
	private int puertaId;
	private LocalDateTime hora_llegada; 
	private LocalDateTime hora_salida; 
	private String estado;
	
	public int getAsiganacionId() {
		return asiganacionId;
	}
	public void setAsiganacionId(int asiganacionId) {
		this.asiganacionId = asiganacionId;
	}
	public String getVueloId() {
		return vueloId;
	}
	public void setVueloId(String vueloId) {
		this.vueloId = vueloId;
	}
	public int getPuertaId() {
		return puertaId;
	}
	public void setPuertaId(int puertaId) {
		this.puertaId = puertaId;
	}
	public LocalDateTime getHora_llegada() {
		return hora_llegada;
	}
	public void setHora_llegada(LocalDateTime hora_llegada) {
		this.hora_llegada = hora_llegada;
	}
	public LocalDateTime getHora_salida() {
		return hora_salida;
	}
	public void setHora_salida(LocalDateTime hora_salida) {
		this.hora_salida = hora_salida;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	} 
}
