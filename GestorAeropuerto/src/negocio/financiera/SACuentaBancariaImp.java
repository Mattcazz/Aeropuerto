package negocio.financiera;

import integracion.financiera.CuentaBancariaDAO;
import integracion.FactoriaDAO;
import java.util.ArrayList;
import java.util.List;

public class SACuentaBancariaImp implements SACuentaBancaria {

	private CuentaBancariaDAO cuentaDAO = FactoriaDAO.getInstancia().getCuentaBancariaDAO();

	@Override
	public void crearCuenta(TCuentaBancaria cuenta) throws Exception {
		CuentaBancaria nueva = new CuentaBancaria(cuenta);
		cuentaDAO.guardar(nueva);
	}

	@Override
	public TCuentaBancaria buscarPorNombre(String nombre) throws Exception {
		CuentaBancaria cb = cuentaDAO.buscarPorNombre(nombre);
		if (cb == null)
			throw new Exception("Cuenta no encontrada");
		return new TCuentaBancaria(cb.getIban(), cb.getNombre(), cb.getSaldo(), cb.getBanco());
	}

	@Override
	public List<TCuentaBancaria> listarCuentas() throws Exception {
		List<CuentaBancaria> cuentas = cuentaDAO.obtenerTodas();
		List<TCuentaBancaria> transfer = new ArrayList<>();
		for (CuentaBancaria c : cuentas) {
			transfer.add(new TCuentaBancaria(c.getIban(), c.getNombre(), c.getSaldo(), c.getBanco()));
		}
		return transfer;
	}

	@Override
	public void actualizarSaldo(String nombreCuenta, float nuevoSaldo) throws Exception {
		CuentaBancaria cuenta = cuentaDAO.buscarPorNombre(nombreCuenta);
		if (cuenta == null)
			throw new Exception("Cuenta no encontrada");

		cuenta.setSaldo(nuevoSaldo);
		cuentaDAO.actualizarSaldo(nombreCuenta, nuevoSaldo);
	}

	@Override
	public void realizarOperacion(String nombreCuenta, String tipo, float monto, String concepto) throws Exception {
		CuentaBancaria cuenta = cuentaDAO.buscarPorNombre(nombreCuenta);
		if (cuenta == null) {
			throw new Exception("Cuenta no encontrada: " + nombreCuenta);
		}

		if (tipo.equalsIgnoreCase("ingreso")) {
			cuenta.abonar(monto);
		} else if (tipo.equalsIgnoreCase("gasto")) {
			if (cuenta.getSaldo() < monto) {
				throw new Exception("Saldo insuficiente en la cuenta: " + cuenta.getNombre());
			}
			cuenta.retirar(monto);
		} else {
			throw new IllegalArgumentException("Tipo de operación inválido.");
		}

		// Actualizar saldo en la base de datos
		cuentaDAO.actualizarSaldo(cuenta.getNombre(), cuenta.getSaldo());
	}

}
