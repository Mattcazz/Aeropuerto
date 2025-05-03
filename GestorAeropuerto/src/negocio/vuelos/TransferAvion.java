
package negocio.vuelos;

public class TransferAvion {
	private String avionId;
	private double altura;
	private double anchura;
	private double longitud;
	private int maxPasajeros;
	private double peso;
	private String aerolinea;
	
	public TransferAvion() { }
	public TransferAvion(String avionId, double altura, double anchura, double longitud,
			int maxPasajeros, double peso, String aerolinea) {
		this.avionId = avionId;
		this.altura = altura;
		this.anchura = anchura;
		this.longitud = longitud;
		this.maxPasajeros = maxPasajeros;
		this.peso = peso;
		this.aerolinea = aerolinea;
	}

	public String getAvionId() {
		return avionId;
	}

	public double getAltura() {
		return altura;
	}

	public double getAnchura() {
		return anchura;
	}

	public double getLongitud() {
		return longitud;
	}

	public int getMaxPasajeros() {
		return maxPasajeros;
	}

	public double getPeso() {
		return peso;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setAvionId(String avionId) {
		this.avionId = avionId;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public void setAnchura(double anchura) {
		this.anchura = anchura;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public void setMaxPasajeros(int maxPasajeros) {
		this.maxPasajeros = maxPasajeros;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

}
