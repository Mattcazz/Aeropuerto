package negocio.paneles;

import java.util.List;

public class TransferPaneles {
	
	int id;
	int tiene_aviso;
	String aviso;
	int encendido;
	int n_lineas;
	int n_columnas;
	int terminal;
	
	public int getId() {
		return id;
	}
	public String getAviso() {
		return aviso;
	}
	public int getTieneAviso() {
		return tiene_aviso;
	}
	public int getEncendido() {
		return encendido;
	}
	public int getN_lineas() {
		return n_lineas;
	}
	public int getN_columnas() {
		return n_columnas;
	}
	public int getTerminal() {
		return terminal;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTieneAviso(int tiene_aviso) {
		this.tiene_aviso = tiene_aviso;
	}
	public void setAviso(String aviso) {
		this.aviso = aviso;
	}
	public void setEncendido(int encendido) {
		this.encendido = encendido;
	}
	public void setN_lineas(int n_lineas) {
		this.n_lineas = n_lineas;
	}
	public void setN_columnas(int n_columnas) {
		this.n_columnas = n_columnas;
	}
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

}
