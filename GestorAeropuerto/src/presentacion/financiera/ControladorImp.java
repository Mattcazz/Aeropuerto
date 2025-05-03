package presentacion.financiera;

import java.util.List;
import javax.swing.JOptionPane;

import negocio.financiera.*;
import presentacion.financiera.CUs.GUICuentasBancarias;
import presentacion.financiera.CUs.GUICuentasBancariasImp;
import presentacion.financiera.CUs.GUIMenuCUsImp;
import presentacion.financiera.CUs.*;

public class ControladorImp extends Controlador {

	private SAFinanzas sa_finanzas;
	private SAIntegracionSubsistemas sa_integracion;

	public ControladorImp() {
		sa_finanzas = FactoriaSA.getInstancia().createSAFinanzas();
		sa_integracion = FactoriaSA.getInstancia().createSAIntegracionSubsistemas();
	}

	@Override
	public void accion(int evento, Object datos) {
		try {
			switch (evento) {

			case Eventos.VOLVER_MENU: {
				GUIMenuCUsImp menu = (GUIMenuCUsImp) GUIMenuCUs.getInstancia();
				menu.getFrame().setVisible(true);
				break;
			}

			case Eventos.MOSTRAR_CUENTAS: {
				GUICuentasBancarias gui = GUICuentasBancarias.getInstancia();
				((GUICuentasBancariasImp) gui).mostrar();
				List<TCuentaBancaria> cuentas = sa_finanzas.listarCuentas();
				gui.actualizar(Eventos.MOSTRAR_CUENTAS, cuentas);
				break;
			}

			case Eventos.AÑADIR_CUENTA: {
				GUICrearCuenta.getInstancia();
				break;
			}

			case Eventos.CREAR_CUENTA: {
				if (datos instanceof TCuentaBancaria cuenta) {
					sa_finanzas.crearCuenta(cuenta);
				} else {
					throw new IllegalArgumentException("Datos inválidos en CREAR_CUENTA");
				}
				break;
			}

			case Eventos.MOSTRAR_FLUJOS: {
				List<TFlujoCaja> flujos = sa_finanzas.listarFlujos();
				GUIFlujosCaja.getInstancia().actualizar(Eventos.MOSTRAR_FLUJOS, flujos);
				break;
			}

			case Eventos.REGISTRAR: {
				new GUIRealizarOperacionImp();
				break;
			}

			case Eventos.REALIZAR_FLUJO: {
				if (datos instanceof TFlujoCaja flujo) {
					sa_finanzas.realizarOperacion(flujo);
					JOptionPane.showMessageDialog(null, "Operación realizada correctamente.");

				} else {
					throw new IllegalArgumentException("Datos inválidos en REALIZAR_FLUJO");
				}
				break;
			}

			case Eventos.COMPLETAR_FLUJO_PENDIENTE: {
				if (datos instanceof Integer id) {
					FactoriaSA.getInstancia().createSAFlujoCaja().completarFlujo(id);
					JOptionPane.showMessageDialog(null, "Flujo completado correctamente.");
				} else {
					throw new IllegalArgumentException("ID inválido en COMPLETAR_FLUJO_PENDIENTE");
				}
				break;
			}

			case Eventos.CREAR_FLUJO_MANUAL: {
				if (datos instanceof TFlujoCaja flujo) {
					FactoriaSA.getInstancia().createSAFlujoCaja().registrarFlujo(flujo);
				} else {
					throw new IllegalArgumentException("Datos inválidos en CREAR_FLUJO_MANUAL");
				}
				break;
			}

			case Eventos.ACEPTAR_CAMBIOS: {
				JOptionPane.showMessageDialog(null, "Cambios aceptados");
				break;
			}

			case Eventos.CANCELAR_CAMBIOS: {
				JOptionPane.showMessageDialog(null, "Cambios cancelados");
				break;
			}

			case Eventos.INTEGRAR_SUBSISTEMAS: {
				sa_integracion.integrarEmpleados();
				sa_integracion.integrarIncidencias();
				sa_integracion.integrarLocales();
				JOptionPane.showMessageDialog(null, "Flujos actualizados desde los subsistemas correctamente.");
				accion(Eventos.MOSTRAR_FLUJOS, null);
				break;
			}

			default: {
				System.out.println("Evento no reconocido: " + evento);
				break;
			}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en Controlador: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public SAFinanzas getFinanzas() {
		return sa_finanzas;
	}
}
