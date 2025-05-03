package negocio.financiera;

import integracion.FactoriaDAO;
import integracion.financiera.FlujoCajaDAO;
import java.util.ArrayList;
import java.util.List;

public class SAFlujoCajaImp implements SAFlujoCaja {

	private FlujoCajaDAO flujoDAO = FactoriaDAO.getInstancia().getFlujoCajaDAO();
	private SACuentaBancaria saCuenta = FactoriaSA.getInstancia().createSACuentaBancaria();

	@Override
	public void registrarFlujo(TFlujoCaja flujo) throws Exception {
		FlujoCaja f = new FlujoCaja(flujo);
		flujoDAO.guardar(f);
	}

	@Override
	public void completarFlujo(int id) throws Exception {
		FlujoCaja flujo = flujoDAO.buscarPorId(id);
		if (flujo == null)
			throw new Exception("Flujo no encontrado");

		TCuentaBancaria cuenta = saCuenta.buscarPorNombre(flujo.getNombreCuenta());
		if (cuenta == null)
			throw new Exception("Cuenta no encontrada");

		if (flujo.getTipo().equalsIgnoreCase("ingreso"))
			saCuenta.actualizarSaldo(cuenta.getNombre(), cuenta.getSaldo() + flujo.getCantidad());
		else if (flujo.getTipo().equalsIgnoreCase("gasto"))
			saCuenta.actualizarSaldo(cuenta.getNombre(), cuenta.getSaldo() - flujo.getCantidad());

		flujoDAO.actualizarEstado(id, "completado");
	}

	@Override
	public List<TFlujoCaja> listarFlujos() throws Exception {
		List<FlujoCaja> lista = flujoDAO.obtenerTodos();
		List<TFlujoCaja> resultado = new ArrayList<>();
		for (FlujoCaja f : lista) {
			resultado.add(new TFlujoCaja(f.getId(), f.getNombreCuenta(), f.getTipo(), f.getCantidad(), f.getConcepto(),
					f.getFecha(), f.getEstado()));
		}
		return resultado;
	}

	@Override
	public List<TFlujoCaja> listarPendientes() {
		List<FlujoCaja> lista = flujoDAO.obtenerPorEstado("pendiente");
		List<TFlujoCaja> resultado = new ArrayList<>();
		for (FlujoCaja f : lista) {
			resultado.add(new TFlujoCaja(f.getId(), f.getNombreCuenta(), f.getTipo(), f.getCantidad(), f.getConcepto(),
					f.getFecha(), f.getEstado()));
		}
		return resultado;
	}

	@Override
	public boolean existeFlujoSimilar(TFlujoCaja flujo) {
		FlujoCaja f = new FlujoCaja(flujo);
		return flujoDAO.existeFlujoSimilar(f);
	}

}
