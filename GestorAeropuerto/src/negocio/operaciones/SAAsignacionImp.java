package negocio.operaciones;

import java.util.LinkedList;
import java.util.List;

import integracion.operaciones.DAOAsignacion;
import integracion.vuelos.DAOAvion;
import integracion.operaciones.DAOPuerta;
import integracion.vuelos.DAOVuelo;
import negocio.vuelos.TransferAvion;
import negocio.vuelos.TransferVuelo;
import integracion.FactoriaDAO;

public class SAAsignacionImp implements SAAsignacion{
	
	DAOAsignacion daoAsignacion;
	DAOVuelo daoVuelo ;
	DAOAvion daoAvion;
	RegistrosDeEstrategias estrategias;
	
	public SAAsignacionImp () {
		daoAsignacion = FactoriaDAO.getInstancia().nuevoDAOAsignacion();
		daoVuelo = FactoriaDAO.getInstancia().nuevoDAOVuelo();
		daoAvion = FactoriaDAO.getInstancia().nuevoDAOAvion();
		estrategias = new RegistrosDeEstrategias();
	}

	@Override
	public List<TransferAsignacion> getAsignacionesInPuerta(int puerta_id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstancia().nuevoDAOAsignacion().getAsignacionesInPuerta(puerta_id);
	}

	@Override
	public TransferPrepararGestionAsignacion prepareGestionarAsignaciones() {
		// TODO Auto-generated method stub
		
		TransferPrepararGestionAsignacion transfer = new TransferPrepararGestionAsignacion();
		
		List<TransferVuelo> vuelosPendientes = daoVuelo.getVuelosPendientes();
		
		RegistrosDeEstrategias estrategias = new RegistrosDeEstrategias();
		transfer.setEstrategias(estrategias.getSetNombreEstrategias());
		
		transfer.setVuelosPendientes(vuelosPendientes);

		if(!vuelosPendientes.isEmpty()) {
			TransferVuelo primerVuelo = vuelosPendientes.get(0);
			TransferAvion avion = daoAvion.getAvion(primerVuelo.getAvionId());
			
			try {
				EstrategiaPrioridadAsignacion estrategia = estrategias.getEstrategia("Default"); // Cogemos la Default para empezar 
				TemplateAsignacionPuerta templateAsignacion = FactoriaTemplateAsignacionPuerta.getTemplatePara(primerVuelo);
				transfer.setPuertasDisponiblesParaVuelo(templateAsignacion.getPuertasDisponibles(primerVuelo, primerVuelo.getTiempoAterrizaje(), estrategia));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
		}else {
			transfer.setPuertasDisponiblesParaVuelo(new LinkedList<TransferPuerta>());
		}
		
		transfer.setAsignaciones(daoAsignacion.getAsignaciones());
		
		return transfer;
	}

	@Override
	public TransferActualizarGUIGestionAsignaciones borrarAsignacion(TransferAsignacion asignacion) {
		// TODO Auto-generated method stub
		return actualizarGUIGestionAsignaciones(FactoriaDAO.getInstancia().nuevoDAOAsignacion().borrarAsignacion(asignacion.getAsiganacionId()));
	}
	
	@Override
	public TransferActualizarGUIGestionAsignaciones crearAsignacion(TransferCrearAsignacion request) {
		
		// TODO Auto-generated method stub
		TransferVuelo vuelo = daoVuelo.getVuelo(request.getVueloId());
		
		return actualizarGUIGestionAsignaciones(FactoriaDAO.getInstancia().nuevoDAOAsignacion().crearAsignacion(vuelo, request.getPuertaId()));
	}

	@Override
	public List<TransferAsignacion> getAsignaciones() {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstancia().nuevoDAOAsignacion().getAsignaciones();
	}
	
	private TransferActualizarGUIGestionAsignaciones actualizarGUIGestionAsignaciones(boolean respuesta) {
		
		
		
		TransferActualizarGUIGestionAsignaciones transfer = new TransferActualizarGUIGestionAsignaciones();
		
		if (respuesta) {
			transfer.setRespuesta(true);
			List<TransferVuelo> vuelosPendientes = daoVuelo.getVuelosPendientes();
			transfer.setVuelosPendientes(vuelosPendientes);
			
			if(!vuelosPendientes.isEmpty()) {
				TransferVuelo primerVuelo = vuelosPendientes.get(0);
				TransferAvion avion = daoAvion.getAvion(primerVuelo.getAvionId());
				
				try {
					EstrategiaPrioridadAsignacion estrategia = estrategias.getEstrategia("Default");
					TemplateAsignacionPuerta templateAsignacion = FactoriaTemplateAsignacionPuerta.getTemplatePara(primerVuelo);
					transfer.setPuertasDisponiblesParaVuelo(templateAsignacion.getPuertasDisponibles(primerVuelo, primerVuelo.getTiempoAterrizaje(), estrategia));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
			}else {
				transfer.setPuertasDisponiblesParaVuelo(new LinkedList<TransferPuerta>());
			}
			
			transfer.setAsignaciones(daoAsignacion.getAsignaciones());
			
			
		}else {
			transfer.setRespuesta(false);
		}
		
		return transfer;
	}
	
	
}
