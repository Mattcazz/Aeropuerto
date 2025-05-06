package negocio.operaciones;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.operaciones.DAOAsignacion;
import integracion.operaciones.DAOPuerta;
import negocio.vuelos.TransferVuelo;

public class SAPuertasImp implements SAPuertas{
	
	private final static double PESO_MIN = 30000;
	private final static double PESO_MAX = 300000;
	private final static double ALTURA_MIN = 2.5;
	private final static double ALTURA_MAX = 4;
	private final static double ANCHURA_MIN = 3;
	private final static double ANCHURA_MAX = 5;
	private final static double LONGITUD_MIN = 10;
	private final static double LONGITUD_MAX = 15;
	
	private DAOPuerta daoPuerta;
	private DAOAsignacion daoAsignacion;

	public SAPuertasImp() {
		daoPuerta = FactoriaDAO.getInstancia().nuevoDAOPuerta();
		daoAsignacion = FactoriaDAO.getInstancia().nuevoDAOAsignacion();
	}
	
	@Override
	public List<TransferPuerta> mostrarPuertas() {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstancia().nuevoDAOPuerta().getPuertas();
	}
	
	

	@Override
	public List<TransferPuerta> actualizarPuertasDisponibles(TransferVueloEstrategia transferVueloEstrategia) {
		// TODO Auto-generated method stub
		RegistrosDeEstrategias estrategias = new RegistrosDeEstrategias();
		
		TransferVuelo vuelo = FactoriaDAO.getInstancia().nuevoDAOVuelo().getVuelo(transferVueloEstrategia.getVueloId());


		try {
			EstrategiaPrioridadAsignacion estrategia = estrategias.getEstrategia(transferVueloEstrategia.getEstrategia());
			TemplateAsignacionPuerta templateAsignacion = FactoriaTemplateAsignacionPuerta.getTemplatePara(vuelo);
			return templateAsignacion.getPuertasDisponibles(vuelo, vuelo.getTiempoAterrizaje(), estrategia);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PeticionPuerta guardarModificacionPuerta(PeticionPuerta transfer) {
		// TODO Auto-generated method stub
		TransferPuerta puerta = transfer.getPuerta();
		
		comprobarDimensiones(transfer);
		
		if (transfer.getError() != null) { // si la comprobacion regresa errores no se modifica
			return transfer;
		}
		
		if (!daoPuerta.modificarPuerta(puerta)) {
			transfer.setError("Error en guardar en Base de Datos. Revisar informacion!");
		}
		
		return transfer;
		
		
	}
	
	@Override
	public PeticionPuerta crearPuerta(PeticionPuerta transfer) {
		// TODO Auto-generated method stub
		TransferPuerta puerta = transfer.getPuerta();
		
		comprobarDimensiones(transfer);
		
		if (transfer.getError() != null) { // si la comprobacion regresa errores no se modifica
			return transfer;
		}
		
		if (!daoPuerta.crearPuerta(puerta)) {
			transfer.setError("Error en guardar en Base de Datos. Revisar informacion!");
		}
		
		return transfer;
	}
	
	private void comprobarDimensiones(PeticionPuerta transfer) {
		
		TransferPuerta puerta = transfer.getPuerta();
		
		if (comprobarDimension("peso", puerta.getPesoMax(), PESO_MIN, PESO_MAX) != null) {
			transfer.setError(comprobarDimension("peso", puerta.getPesoMax(), PESO_MIN, PESO_MAX));
			return;
		}
		
		if (comprobarDimension("altura", puerta.getAlturaMax(), ALTURA_MIN, ALTURA_MAX) != null) {
			transfer.setError(comprobarDimension("altura", puerta.getAlturaMax(), ALTURA_MIN, ALTURA_MAX));
			return;
		}
		
		if (comprobarDimension("anchura", puerta.getPesoMax(), ANCHURA_MIN, ANCHURA_MAX) != null) {
			transfer.setError(comprobarDimension("anchura", puerta.getAnchuraMax(), ANCHURA_MIN, ANCHURA_MAX));
			return;
		}
		
		if (comprobarDimension("longitud", puerta.getLongitudMax(), LONGITUD_MIN, LONGITUD_MAX) != null) {
			transfer.setError(comprobarDimension("longitud", puerta.getLongitudMax(), LONGITUD_MIN, LONGITUD_MAX));
			return;
		}
		
	}
	

	
	
	private String comprobarDimension (String nombre,double valor, double min, double max) {
		
		if (min <= valor && max >= valor) {
			return null;	
		}
		
		return "Error en la dimension de " + nombre + ". El valor tiene que estar entre "+ min + " y " + max + ".";
		
		
	}

	@Override
	public PeticionBloqueo bloquearPuerta(PeticionBloqueo transfer) {
		// TODO Auto-generated method stub
		TransferBloqueo bloqueo = transfer.getBloqueo();
		List<TransferAsignacion> asignacionesEnFranja = daoAsignacion.getAsignacionesDePuertaEnFranja(bloqueo.getPuertaId(), bloqueo.getHoraInicio(), bloqueo.getHoraFinal());
		
		if(!asignacionesEnFranja.isEmpty()) {
			transfer.setError("Operacion invalida! El bloqueo solapa con vuelos asignados a la puerta.");
			return transfer;
		}
		
		if(daoPuerta.getBloqueoDePuertaEnHora(bloqueo.getPuertaId(), bloqueo.getHoraInicio(), bloqueo.getHoraFinal()) != null) {
			transfer.setError("Operacion invalida! Ya hay un bloqueo a esta hora.");
			return transfer;
		}
		
		if (!daoPuerta.crearBloqueoEnPuerta(bloqueo)) {
			transfer.setError("Error en guardar en Base de Datos. Revisar informacion!");
		}
		
		return transfer;
	}

	@Override
	public List<TransferBloqueo> getBloqueosEnPuerta(int puerta_id) {
		// TODO Auto-generated method stub
		return daoPuerta.getBloqueosEnPuerta(puerta_id);
	}

	@Override
	public boolean borrarPuerta(int puerta_id) {
		// TODO Auto-generated method 
		return daoAsignacion.borrarAsignacionesEnPuerta(puerta_id) && daoPuerta.borrarBloqueosEnPuerta(puerta_id) && daoPuerta.borrarPuerta(puerta_id);
	}

	@Override
	public void borrarBloqueo(TransferBloqueo bloqueo) {
		// TODO Auto-generated method stub
		daoPuerta.borrarBloqueoEnPuerta(bloqueo);
	}

	@Override
	public TransferPuerta getPuerta(int puerta_id) {
		// TODO Auto-generated method stub
		return daoPuerta.getPuerta(puerta_id);
	}

	
	

}
