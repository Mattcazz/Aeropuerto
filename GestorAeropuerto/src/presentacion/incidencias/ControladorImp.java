package presentacion.incidencias;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import negocio.incidencias.FactoriaSA;
import negocio.incidencias.SAGestionIncidencias;
import negocio.incidencias.TransferIncidencia;
import presentacion.incidencias.CUs.GUIMenuCUs;
import presentacion.incidencias.CUs.GUIMenuCUsImp;
import presentacion.incidencias.CUs.GUISeleccionarSolucionEquipaje;
import presentacion.incidencias.CUs.GUISeleccionarSolucionVuelo;
import presentacion.incidencias.CUs.GUIVisualizarIncidenciasEquipajes;
import presentacion.incidencias.CUs.GUIVisualizarIncidenciasEquipajesImp;
import presentacion.incidencias.CUs.GUIVisualizarIncidenciasVuelos;
import presentacion.incidencias.CUs.GUIVisualizarIncidenciasVuelosImp;
import presentacion.incidencias.CUs.GUIVisualizarRegistro;
import presentacion.incidencias.CUs.GUIVisualizarRegistroImp;

public class ControladorImp extends Controlador {

	private SAGestionIncidencias saIncidencias;

	public ControladorImp() {
		this.saIncidencias = FactoriaSA.getInstancia().nuevoSAGestionIncidencias();
	}

	public void accion(int evento, Object datos) {
		switch (evento) {
		case (Eventos.VOLVER_MENU): {
			GUIMenuCUsImp menu = (GUIMenuCUsImp) GUIMenuCUs.getInstancia();
			JFrame menuFrame = menu.getFrame();
			menuFrame.setVisible(true);
		}
			break;
		case (Eventos.CREAR_INCIDENCIA_EQUIPAJE): {
			if (datos instanceof TransferIncidencia incidencia) {
				try {
					saIncidencias.crearIncidencia(incidencia);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				throw new IllegalArgumentException("Incidencia no válida");
			}
			break;
		}

		case (Eventos.CREAR_INCIDENCIA_VUELO): {
			if (datos instanceof TransferIncidencia incidencia) {
				try {
					saIncidencias.crearIncidencia(incidencia);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				throw new IllegalArgumentException("Incidencia no válida");
			}
			break;
		}
		case (Eventos.VISUALIZAR_INCIDENCIAS_EQUIPAJE): {
			try {
				var lista = saIncidencias.listarIncidenciasPorTipo("equipaje");
				var gui = (GUIVisualizarIncidenciasEquipajesImp) GUIVisualizarIncidenciasEquipajes.getInstancia();
				gui.actualizar(Eventos.MOSTRAR_INCIDENCIAS_EQUIPAJE_OK, lista);
				gui.getFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al visualizar incidencias de equipaje: " + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		case (Eventos.VISUALIZAR_INCIDENCIAS_VUELO): {
			try {
				var lista = saIncidencias.listarIncidenciasPorTipo("vuelo");
				var gui = (GUIVisualizarIncidenciasVuelosImp) GUIVisualizarIncidenciasVuelos.getInstancia();
				gui.actualizar(Eventos.MOSTRAR_INCIDENCIAS_VUELO_OK, lista);
				gui.getFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al visualizar incidencias de vuelo: " + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		case (Eventos.BORRAR_INCIDENCIA): {
			if (datos instanceof String[] info && info.length == 2) {
				String tipo = info[0].toLowerCase();
				String id = info[1];

				try {
					TransferIncidencia incidencia = saIncidencias.consultarIncidencia(id);

					if (incidencia == null || !incidencia.getTipo().equalsIgnoreCase(tipo)) {
						JOptionPane.showMessageDialog(null,
								"No se encontró una incidencia de tipo '" + tipo + "' con ese identificador.",
								"No encontrada", JOptionPane.WARNING_MESSAGE);
						break;
					}

					boolean exito = saIncidencias.eliminarIncidencia(id);
					if (exito) {
						JOptionPane.showMessageDialog(null, "Incidencia borrada correctamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo borrar la incidencia.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al borrar la incidencia: " + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Parámetros de entrada incorrectos.", "Error de datos",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		case (Eventos.VISUALIZAR_REGISTRO): {
			try {
				List<TransferIncidencia> resueltas = saIncidencias.listarPorEstado("resuelta");
				var gui = (GUIVisualizarRegistroImp) GUIVisualizarRegistro.getInstancia();
				gui.actualizar(Eventos.MOSTRAR_REGISTRO, resueltas);
				gui.getFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Error al visualizar el registro de incidencias resueltas: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		case Eventos.SELECCIONAR_SOLUCION_VUELO: {
			if (datos instanceof Object[] info && info.length == 3) {
				String id = (String) info[0];
				String solucion = (String) info[1];
				float compensacion = (float) info[2];

				try {
					boolean ok = saIncidencias.seleccionarSolucion(id, solucion, compensacion);

					if (ok) {
						GUISeleccionarSolucionVuelo.getInstancia().actualizar(Eventos.SOLUCION_APLICADA_OK, null);
					} else {
						GUISeleccionarSolucionVuelo.getInstancia().actualizar(Eventos.SOLUCION_APLICADA_NO,
								"No se pudo actualizar la incidencia.");
					}

				} catch (Exception e) {
					GUISeleccionarSolucionVuelo.getInstancia().actualizar(Eventos.SOLUCION_APLICADA_NO, e.getMessage());
				}

			} else {
				JOptionPane.showMessageDialog(null, "Datos inválidos para aplicar solución", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		case Eventos.SELECCIONAR_SOLUCION_EQUIPAJE: {
			if (datos instanceof Object[] info && info.length == 3) {
				try {
					String id = (String) info[0];
					String solucion = (String) info[1];
					float compensacion = (float) info[2];

					boolean exito = saIncidencias.seleccionarSolucion(id, solucion, compensacion);

					if (exito) {
						GUISeleccionarSolucionEquipaje.getInstancia().actualizar(Eventos.SOLUCION_APLICADA_OK, null);
					} else {
						GUISeleccionarSolucionEquipaje.getInstancia().actualizar(Eventos.SOLUCION_APLICADA_NO,
								"No se pudo actualizar la incidencia.");
					}

				} catch (Exception e) {
					GUISeleccionarSolucionEquipaje.getInstancia().actualizar(Eventos.SOLUCION_APLICADA_NO,
							e.getMessage());
				}

			} else {
				JOptionPane.showMessageDialog(null, "Datos inválidos para aplicar solución a equipaje", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		}

	}
}
