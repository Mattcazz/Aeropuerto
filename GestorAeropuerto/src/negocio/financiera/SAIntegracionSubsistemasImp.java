package negocio.financiera;

import java.time.LocalDate;
import java.util.List;

import integracion.financiera.EmpleadoDAO;
import integracion.FactoriaDAO;
import integracion.incidencias.DAOIncidencia;
import negocio.incidencias.TransferIncidencia;
import integracion.financiera.LocalDAO;

public class SAIntegracionSubsistemasImp implements SAIntegracionSubsistemas {

	private EmpleadoDAO empleadoDAO = FactoriaDAO.getInstancia().getEmpleadoDAO();
	private LocalDAO localDAO = FactoriaDAO.getInstancia().getLocalDAO();
	private DAOIncidencia incidenciaDAO = FactoriaDAO.getInstancia().nuevoDAOIncidencia();
	private SAFlujoCaja saFlujo = FactoriaSA.getInstancia().createSAFlujoCaja();

	@Override
	public void integrarEmpleados() throws Exception {
		List<TEmpleado> empleados = empleadoDAO.obtener();

		for (TEmpleado e : empleados) {
			if (Boolean.TRUE.equals(e.isActivo())) {
				TFlujoCaja flujo = new TFlujoCaja(
						"Nomina empleados",
						"gasto",
						e.getNomina(),
						"Pago nómina a empleado: " + e.getNombre(),
						LocalDate.now(),
						"pendiente");

				if (!saFlujo.existeFlujoSimilar(flujo)) {
					saFlujo.registrarFlujo(flujo);
				}
			}
		}
	}

	@Override
	public void integrarLocales() throws Exception {
		List<TLocal> locales = localDAO.obtener();

		for (TLocal l : locales) {
			if (l.getIngresos() > 0) {
				TFlujoCaja ingreso = new TFlujoCaja(
						"Locales",
						"ingreso",
						l.getIngresos(),
						"Ingreso de empresa: " + l.getEmpresa(),
						LocalDate.now(),
						"pendiente");

				if (!saFlujo.existeFlujoSimilar(ingreso)) {
					saFlujo.registrarFlujo(ingreso);
				}
			}

			if (l.getGastos() > 0) {
				TFlujoCaja gasto = new TFlujoCaja(
						"Locales",
						"gasto",
						l.getGastos(),
						"Gasto operativo de empresa: " + l.getEmpresa(),
						LocalDate.now(),
						"pendiente");

				if (!saFlujo.existeFlujoSimilar(gasto)) {
					saFlujo.registrarFlujo(gasto);
				}
			}
		}
	}

	@Override
	public void integrarIncidencias() throws Exception {
		List<TransferIncidencia> incidencias = incidenciaDAO.obtener();

		for (TransferIncidencia i : incidencias) {
			if (i.getCompensacion() > 0) {
				TFlujoCaja flujo = new TFlujoCaja(
						"Incidencias generales",
						"gasto",
						i.getCompensacion(),
						"Pago compensación por incidencia: " + i.getDescripcion(),
						LocalDate.now(),
						"pendiente");

				if (!saFlujo.existeFlujoSimilar(flujo)) {
					saFlujo.registrarFlujo(flujo);
				}
			}
		}
	}
	
}