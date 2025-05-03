package integracion.financiera;

import java.util.List;

import negocio.financiera.CuentaBancaria;

public interface CuentaBancariaDAO {
	void guardar(CuentaBancaria cuenta);

	CuentaBancaria buscarPorNombre(String nombre);

	List<CuentaBancaria> obtenerTodas();

	void actualizarSaldo(String nombreCuenta, float nuevoSaldo);

}
