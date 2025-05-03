package negocio.operaciones;

import java.util.List;

import negocio.vuelos.TransferVuelo;

public class TransferActualizarGUIGestionAsignaciones{
	
	private List <TransferAsignacion> asignaciones;
	private List<TransferVuelo> vuelosPendientes; 
	private List<TransferPuerta> puertasDisponiblesParaVuelo;
	private boolean respuesta; 
	
	public List<TransferAsignacion> getAsignaciones() {
		return asignaciones;
	}
	public void setAsignaciones(List<TransferAsignacion> asignaciones) {
		this.asignaciones = asignaciones;
	}
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
	public boolean isRespuesta() {
		return respuesta;
	}
	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	} 
	
}
