package negocio.operaciones;

public class TransferVueloEstrategia {
	private String vueloId;
	private String estrategia;
	
	public TransferVueloEstrategia(String vuelo_id, String estrategia){
		this.vueloId = vuelo_id;
		this.estrategia = estrategia;
	}
	
	public String getVueloId() {
		return vueloId;
	}
	public void setVueloId(String vueloId) {
		this.vueloId = vueloId;
	}
	public String getEstrategia() {
		return estrategia;
	}
	public void setEstrategia(String estrategia) {
		this.estrategia = estrategia;
	}
}
