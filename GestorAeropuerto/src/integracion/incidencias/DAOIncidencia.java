package integracion.incidencias;

import java.util.List;

import negocio.incidencias.TransferIncidencia;

public interface DAOIncidencia {
	
	void guardar(TransferIncidencia incidencia);  // Inserta una nueva incidencia en la BD

    boolean actualizar(TransferIncidencia incidencia);  // modifica una incidencia existente en la BD

    boolean eliminar(String id); // Elimina la incidencia con el 'id' dado

    TransferIncidencia buscar(String id);  //  Devuelve una incidencia específica según su identificador

    List<TransferIncidencia> listarPorTipo(String tipo);  // "vuelo" o "equipaje"

	List<TransferIncidencia> listarPorEstado(String estado);  // Usado para listar las incidencias que ya han sido resueltas
}
