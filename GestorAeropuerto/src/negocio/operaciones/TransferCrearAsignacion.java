package negocio.operaciones;

public class TransferCrearAsignacion {
	private String vueloId;
	private int puertaId;
	
	public TransferCrearAsignacion(String vueloId, int puertaId) {
		this.vueloId = vueloId;
		this.puertaId = puertaId;
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
}
