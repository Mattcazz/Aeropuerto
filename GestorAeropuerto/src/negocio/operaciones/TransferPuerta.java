package negocio.operaciones;

public class TransferPuerta {

	private int puertaID;
    private int terminal;
    private String ubicacion;
    private int nivel;
    private float pesoMax;
    private float anchuraMax;
    private float alturaMax;
    private float longitudMax;
    private String tipoPuerta;
    private boolean vip;
    private String estado;
    private String aerolineaPreferida;
    
	public int getPuertaID() {
		return puertaID;
	}
	public void setPuertaID(int puertaID) {
		this.puertaID = puertaID;
	}
	public int getTerminal() {
		return terminal;
	}
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public float getPesoMax() {
		return pesoMax;
	}
	public void setPesoMax(float pesoMax) {
		this.pesoMax = pesoMax;
	}
	public float getAnchuraMax() {
		return anchuraMax;
	}
	public void setAnchuraMax(float anchuraMax) {
		this.anchuraMax = anchuraMax;
	}
	public float getAlturaMax() {
		return alturaMax;
	}
	public void setAlturaMax(float alturaMax) {
		this.alturaMax = alturaMax;
	}
	public float getLongitudMax() {
		return longitudMax;
	}
	public void setLongitudMax(float longitudMax) {
		this.longitudMax = longitudMax;
	}
	public String getTipoPuerta() {
		return tipoPuerta;
	}
	public void setTipoPuerta(String tipoPuerta) {
		this.tipoPuerta = tipoPuerta;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAerolineaPreferida() {
		return aerolineaPreferida;
	}
	public void setAerolineaPreferida(String aerolineaPreferida) {
		this.aerolineaPreferida = aerolineaPreferida;
	}
    
    
	
}
