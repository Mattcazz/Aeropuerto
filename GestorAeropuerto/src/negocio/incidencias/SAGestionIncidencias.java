package negocio.incidencias;

import java.util.List;

public interface SAGestionIncidencias {
	
	public void crearIncidencia(TransferIncidencia incidencia) throws Exception;  

    public boolean eliminarIncidencia(String id)  throws Exception;

    public TransferIncidencia consultarIncidencia(String id)  throws Exception;

  	public List<TransferIncidencia> listarIncidenciasPorTipo(String tipo)  throws Exception;  // "vuelo" o "equipaje"

	public List<TransferIncidencia> listarPorEstado(String estado);
	
	public boolean seleccionarSolucion(String id, String solucion, float compensacion) throws Exception;
}
