package integracion.financiera;

import java.util.List;

import negocio.financiera.FlujoCaja;

public interface FlujoCajaDAO {
    void guardar(FlujoCaja flujo);
    List<FlujoCaja> obtenerTodos();
    List<FlujoCaja> obtenerPorEstado(String estado);
	FlujoCaja buscarPorId(int id);
	void actualizarEstado(int id, String estado);
	boolean existeFlujoSimilar(FlujoCaja flujo);

}
