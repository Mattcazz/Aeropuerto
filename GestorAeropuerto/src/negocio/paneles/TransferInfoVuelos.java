package negocio.paneles;

import java.time.LocalTime;

public class TransferInfoVuelos {
	
	int panel;
	
	String avion; 
	String aerolinea; 
	String origen; 
	String destino;
	String vuelo;
	
	LocalTime hora_llegada; 
	LocalTime hora_salida; 
	int puerta; 
	int terminal;
	
	public int getPanel() {
		return panel;
	}

	public String getAvion() {
		return avion;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public String getVuelo() {
		return vuelo;
	}

	public LocalTime getHora_llegada() {
		return hora_llegada;
	}

	public LocalTime getHora_salida() {
		return hora_salida;
	}

	public int getPuerta() {
		return puerta;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setPanel(int panel) {
		this.panel = panel;
	}

	public void setAvion(String avion) {
		this.avion = avion;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setVuelo(String vuelo) {
		this.vuelo = vuelo;
	}

	public void setHora_llegada(LocalTime hora_llegada) {
		this.hora_llegada = hora_llegada;
	}

	public void setHora_salida(LocalTime hora_salida) {
		this.hora_salida = hora_salida;
	}

	public void setPuerta(int puerta) {
		this.puerta = puerta;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

}
