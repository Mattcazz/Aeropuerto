package negocio.incidencias;

import java.util.List;

import integracion.incidencias.DAOIncidencia;
import integracion.FactoriaDAO;

public class SAGestionIncidenciasImp implements SAGestionIncidencias {

	private DAOIncidencia daoIncidencia = FactoriaDAO.getInstancia().nuevoDAOIncidencia();

	@Override
	public void crearIncidencia(TransferIncidencia incidencia) throws Exception {
		daoIncidencia.guardar(incidencia);
	}

	@Override
	public boolean eliminarIncidencia(String id) {
		return daoIncidencia.eliminar(id);
	}

	@Override
	public TransferIncidencia consultarIncidencia(String id) throws Exception {
		return daoIncidencia.buscar(id);
	}

	@Override
	public List<TransferIncidencia> listarIncidenciasPorTipo(String tipo) throws Exception {
		if (!"vuelo".equalsIgnoreCase(tipo) && !"equipaje".equalsIgnoreCase(tipo))
			return List.of(); 
		return daoIncidencia.listarPorTipo(tipo.toLowerCase());
	}

	@Override
	public List<TransferIncidencia> listarPorEstado(String estado) {
		if (!estado.equalsIgnoreCase("resuelta") && !estado.equalsIgnoreCase("no resuelta")) {
	        return List.of(); 
	    }
	    return daoIncidencia.listarPorEstado(estado.toLowerCase());
	}
	
	public boolean seleccionarSolucion(String id, String solucion, float compensacion) throws Exception {
	    TransferIncidencia incidencia = daoIncidencia.buscar(id);
	    if (incidencia == null) throw new Exception("Incidencia no encontrada");

	    SolucionStrategy estrategia = StrategyFactory.getStrategy(incidencia.getTipoIncidencia());
	    estrategia.aplicarSolucion(incidencia, solucion, compensacion);

	    return daoIncidencia.actualizar(incidencia);
	}
	
	
}


