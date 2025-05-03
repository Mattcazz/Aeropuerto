package negocio.operaciones;

import java.util.List;
import java.util.Map;
import java.util.Set;

import negocio.vuelos.TransferVuelo;

public class TransferPrepararGestionAsignacion {

	private List<TransferVuelo> vuelosPendientes; 
	private List<TransferPuerta> puertasDisponiblesParaVuelo; 
	private Set <String> set_nombre_estrategias;
	private List <TransferAsignacion> asignaciones;
	
	public List<TransferVuelo> getVuelosPendientes() {
		return vuelosPendientes;
	}
	public void setVuelosPendientes(List<TransferVuelo> vuelosPendientes) {
		this.vuelosPendientes = vuelosPendientes;
	}
	public List<TransferPuerta> getPuertasDisponiblesParaVuelo() {
		return puertasDisponiblesParaVuelo;
	}
	public void setPuertasDisponiblesParaVuelo(List<TransferPuerta> puertasDisponiblesParaVuelo) {
		this.puertasDisponiblesParaVuelo = puertasDisponiblesParaVuelo;
	}
	public Set <String> getEstrategias() {
		return set_nombre_estrategias;
	}
	public void setEstrategias(Set <String> set_nombre_estrategias) {
		this.set_nombre_estrategias = set_nombre_estrategias;
	}
	public List<TransferAsignacion> getAsignaciones() {
		return asignaciones;
	}
	public void setAsignaciones(List<TransferAsignacion> asignaciones) {
		this.asignaciones = asignaciones;
	}
	
	
}
