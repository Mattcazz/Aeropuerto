package presentacion.vuelos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.vuelos.*;
import presentacion.vuelos.CUs.*;



public class ControladorImp extends Controlador {
	FactoriaSA factoriaSA = FactoriaSA.getInstancia();
	
	public void accion(Eventos evento, Object datos) {
		switch (evento) {
			case VOLVER_MENU: {
				GUICU menu = (GUICU) datos;
				
				menu.getFrame().dispose();
				break;
			}
			case ABRIR_MENU_CREAR_VUELO: {
				SAAviones saAviones = this.factoriaSA.nuevoSAAviones();

				GUICrearVueloImp menu = (GUICrearVueloImp) GUICrearVuelo.getInstancia();
				List<TransferAvion> aviones = saAviones.getAllAviones();
				List<String> avionIds = new ArrayList<String>();
				for (TransferAvion avion : aviones) {
					avionIds.add(avion.getAvionId());
				}
				// List<String> test = new ArrayList<String>(Arrays.asList("1", "2", "3"));
				
				menu.init(avionIds);
				break;
			}
			case ABRIR_MENU_MODIFICAR_VUELO: {
				SAVuelos saVuelos = this.factoriaSA.nuevoSAVuelos();

				GUIModificarVueloImp menu = (GUIModificarVueloImp) GUIModificarVuelo.getInstancia();
				List<TransferVuelo> vuelos = saVuelos.getAllVuelos();
				// List<TransferVuelo> test = new ArrayList<>();
				// test.add(new TransferVuelo("A9X7B2", "1", "JFK", "LAX", LocalDateTime.of(2025, 5, 1, 10, 30), LocalDateTime.of(2025, 5, 1, 13, 45), "Domestico", true));
				// test.add(new TransferVuelo("Z3M5K8", "2", "ORD", "ATL", LocalDateTime.of(2025, 5, 1, 8, 0), LocalDateTime.of(2025, 5, 1, 11, 15), "Domestico", false));
				// test.add(new TransferVuelo("P1L4Q9", "3", "DFW", "CDG", LocalDateTime.of(2025, 5, 1, 17, 20), LocalDateTime.of(2025, 5, 2, 7, 0), "Internacional", true));
				// test.add(new TransferVuelo("K7T2V6", "2", "SFO", "SEA", LocalDateTime.of(2025, 5, 1, 14, 10), LocalDateTime.of(2025, 5, 1, 15, 45), "Domestico", false));
				// test.add(new TransferVuelo("M4J9D3", "1", "MIA", "BOG", LocalDateTime.of(2025, 5, 1, 22, 0), LocalDateTime.of(2025, 5, 2, 1, 30), "Internacional", true));

				menu.init(vuelos);
				break;
			}
			case ABRIR_MENU_BUSCAR_VUELO: {
				GUIBuscarVueloImp menu = (GUIBuscarVueloImp) GUIBuscarVuelo.getInstancia();
				menu.init();
				break;
			}
			case MOSTRAR_MENSAJE: {
				JOptionPane.showMessageDialog(null, (String) datos);
				break;
			}
			case CREAR_VUELO: {
				SAVuelos saVuelos = this.factoriaSA.nuevoSAVuelos();
				
				GUICrearVueloImp menu = (GUICrearVueloImp) datos;
				
				String error_message = saVuelos.checkVuelo(
						menu.getVueloId(),
						menu.getAvionId(),
						menu.getOrigen(),
						menu.getDestino(),
						menu.getSalida(),
						menu.getAterrizaje(),
						menu.getTipo(),
						menu.getVip()
				);
				if (error_message != null) {
					this.accion(Eventos.MOSTRAR_MENSAJE, error_message);
					break;
				}
				
				if (!saVuelos.crearVuelo(
						menu.getVueloId(),
						menu.getAvionId(),
						menu.getOrigen(),
						menu.getDestino(),
						menu.getSalida(),
						menu.getAterrizaje(),
						menu.getTipo(),
						menu.getVip()
				)) {
					this.accion(Eventos.MOSTRAR_MENSAJE, "Error al crear vuelo");
					break;
				}
				
				// Cerrar la ventana
				menu.getFrame().dispose();
				break;
			}
			case ABRIR_SUBMENU_ACTUALIZAR_VUELO: {
				SAAviones saAviones = this.factoriaSA.nuevoSAAviones();

				GUIModificarVueloImp daddy = (GUIModificarVueloImp) datos;
				GUIActualizarVueloImp menu = (GUIActualizarVueloImp) GUIActualizarVuelo.getInstancia();
				
				List<TransferAvion> aviones = saAviones.getAllAviones();
				List<String> avionIds = new ArrayList<String>();
				for (TransferAvion avion : aviones) {
					avionIds.add(avion.getAvionId());
				}
				// List<String> test = new ArrayList<String>(Arrays.asList("1", "2", "3"));
				
				TransferVuelo transferVuelo = daddy.getSelectedVuelo();
				menu.init(transferVuelo, avionIds, daddy);
				break;
			}
			case ACTUALIZAR_VUELO: {
				SAVuelos saVuelos = this.factoriaSA.nuevoSAVuelos();
				GUIActualizarVueloImp menu = (GUIActualizarVueloImp) datos;
				
				String error_message = saVuelos.checkVuelo(
						menu.getVueloId(),
						menu.getAvionId(),
						menu.getOrigen(),
						menu.getDestino(),
						menu.getSalida(),
						menu.getAterrizaje(),
						menu.getTipo(),
						menu.getVip()
				);
				if (error_message != null) {
					this.accion(Eventos.MOSTRAR_MENSAJE, error_message);
					break;
				}
				
				if (!saVuelos.actualizarVuelo(
						menu.getVueloId(),
						menu.getAvionId(),
						menu.getOrigen(),
						menu.getDestino(),
						menu.getSalida(),
						menu.getAterrizaje(),
						menu.getTipo(),
						menu.getVip()
				)) {
					this.accion(Eventos.MOSTRAR_MENSAJE, "Error al actualizar vuelo");
					break;
				}
				
				TransferVuelo transferVuelo = new TransferVuelo(
						menu.getVueloId(),
						menu.getAvionId(),
						menu.getOrigen(),
						menu.getDestino(),
						menu.getSalida(),
						menu.getAterrizaje(),
						menu.getTipo(),
						menu.getVip()
				);
				GUIModificarVueloImp parent = menu.getParent();
				parent.actualizar(Eventos.ACTUALIZAR_VUELO, transferVuelo);
				menu.getFrame().dispose();
				break;
			}
			case ELIMINAR_VUELO: {
				SAVuelos saVuelos = this.factoriaSA.nuevoSAVuelos();
				GUIModificarVueloImp menu = (GUIModificarVueloImp) datos;

				TransferVuelo transferVuelo = menu.getSelectedVuelo();
				if (!saVuelos.eliminarVuelo(transferVuelo.getVueloId())) {
					this.accion(Eventos.MOSTRAR_MENSAJE, "Error al eliminar vuelo");
					break;
				}
				
				menu.actualizar(Eventos.ELIMINAR_VUELO, transferVuelo.getVueloId());
				break;
			}
			case BUSCAR_VUELO: {
				SAVuelos saVuelos = this.factoriaSA.nuevoSAVuelos();
				GUIBuscarVueloImp menu = (GUIBuscarVueloImp) datos;
				
				LocalTime hora = menu.getHoraSeleccionada();
				String condicion = menu.getCondicion();
				
				LocalDateTime tiempo = LocalDateTime.of(LocalDateTime.now().toLocalDate(), hora);
				boolean antes = condicion.equals("Antes");
						
				List<TransferVuelo> vuelos = saVuelos.buscarVuelo(tiempo, antes);
				// List<TransferVuelo> test = new ArrayList<>();
				// test.add(new TransferVuelo("A9X7B2", "1", "JFK", "LAX", LocalDateTime.of(2025, 5, 1, 10, 30), LocalDateTime.of(2025, 5, 1, 13, 45), "Domestico", true));
				// test.add(new TransferVuelo("Z3M5K8", "2", "ORD", "ATL", LocalDateTime.of(2025, 5, 1, 8, 0), LocalDateTime.of(2025, 5, 1, 11, 15), "Domestico", false));
				// test.add(new TransferVuelo("P1L4Q9", "3", "DFW", "CDG", LocalDateTime.of(2025, 5, 1, 17, 20), LocalDateTime.of(2025, 5, 2, 7, 0), "Internacional", true));
				// test.add(new TransferVuelo("K7T2V6", "2", "SFO", "SEA", LocalDateTime.of(2025, 5, 1, 14, 10), LocalDateTime.of(2025, 5, 1, 15, 45), "Domestico", false));
				// test.add(new TransferVuelo("M4J9D3", "1", "MIA", "BOG", LocalDateTime.of(2025, 5, 1, 22, 0), LocalDateTime.of(2025, 5, 2, 1, 30), "Internacional", true));
				
				GUIMostrarVueloImp menu_mostrar = (GUIMostrarVueloImp) GUIMostrarVuelo.getInstancia();
				menu_mostrar.init(vuelos);
				break;
			}
			case ABRIR_MENU_CREAR_AVION: {
				GUICrearAvionImp menu = (GUICrearAvionImp) GUICrearAvion.getInstancia();
				
				menu.init();
				break;
			}
			case ABRIR_MENU_MODIFICAR_AVION: {
				SAAviones saAviones = this.factoriaSA.nuevoSAAviones();

				GUIModificarAvionImp menu = (GUIModificarAvionImp) GUIModificarAvion.getInstancia();
				List<TransferAvion> aviones = saAviones.getAllAviones();
				// List<TransferVuelo> test = new ArrayList<>();
				// test.add(new TransferVuelo("A9X7B2", "1", "JFK", "LAX", LocalDateTime.of(2025, 5, 1, 10, 30), LocalDateTime.of(2025, 5, 1, 13, 45), "Domestico", true));
				// test.add(new TransferVuelo("Z3M5K8", "2", "ORD", "ATL", LocalDateTime.of(2025, 5, 1, 8, 0), LocalDateTime.of(2025, 5, 1, 11, 15), "Domestico", false));
				// test.add(new TransferVuelo("P1L4Q9", "3", "DFW", "CDG", LocalDateTime.of(2025, 5, 1, 17, 20), LocalDateTime.of(2025, 5, 2, 7, 0), "Internacional", true));
				// test.add(new TransferVuelo("K7T2V6", "2", "SFO", "SEA", LocalDateTime.of(2025, 5, 1, 14, 10), LocalDateTime.of(2025, 5, 1, 15, 45), "Domestico", false));
				// test.add(new TransferVuelo("M4J9D3", "1", "MIA", "BOG", LocalDateTime.of(2025, 5, 1, 22, 0), LocalDateTime.of(2025, 5, 2, 1, 30), "Internacional", true));

				menu.init(aviones);
				break;
			}
			case CREAR_AVION: {
				SAAviones saAviones = this.factoriaSA.nuevoSAAviones();
				
				GUICrearAvionImp menu = (GUICrearAvionImp) datos;
				
				String error_message = saAviones.checkAvion(
						menu.getAvionId(),
						menu.getAltura(),
						menu.getAnchura(),
						menu.getLongitud(),
						menu.getMaxPasajeros(),
						menu.getPeso(),
						menu.getAerolinea()
			);
				if (error_message != null) {
					this.accion(Eventos.MOSTRAR_MENSAJE, error_message);
					break;
				}
				
				if (!saAviones.crearAvion(
						menu.getAvionId(),
						Double.parseDouble(menu.getAltura()),
						Double.parseDouble(menu.getAnchura()),
						Double.parseDouble(menu.getLongitud()),
						Integer.parseInt(menu.getMaxPasajeros()),
						Double.parseDouble(menu.getPeso()),
						menu.getAerolinea()
				)) {
					this.accion(Eventos.MOSTRAR_MENSAJE, "Error al crear avion");
					break;
				}
				
				// Cerrar la ventana
				menu.getFrame().dispose();
				break;
			}
			case ABRIR_SUBMENU_ACTUALIZAR_AVION: {
				GUIModificarAvionImp daddy = (GUIModificarAvionImp) datos;
				GUIActualizarAvionImp menu = (GUIActualizarAvionImp) GUIActualizarAvion.getInstancia();
				
				TransferAvion transferAvion = daddy.getSelectedAvion();
				menu.init(transferAvion, daddy);
				break;
			}
			case ACTUALIZAR_AVION: {
				SAAviones saAviones = this.factoriaSA.nuevoSAAviones();
				GUIActualizarAvionImp menu = (GUIActualizarAvionImp) datos;
				
				String error_message = saAviones.checkAvion(
						menu.getAvionId(),
						menu.getAltura(),
						menu.getAnchura(),
						menu.getLongitud(),
						menu.getMaxPasajeros(),
						menu.getPeso(),
						menu.getAerolinea()
				);
				if (error_message != null) {
					this.accion(Eventos.MOSTRAR_MENSAJE, error_message);
					break;
				}
				
				if (!saAviones.actualizarAvion(
						menu.getAvionId(),
						Double.parseDouble(menu.getAltura()),
						Double.parseDouble(menu.getAnchura()),
						Double.parseDouble(menu.getLongitud()),
						Integer.parseInt(menu.getMaxPasajeros()),
						Double.parseDouble(menu.getPeso()),
						menu.getAerolinea()
				)) {
					this.accion(Eventos.MOSTRAR_MENSAJE, "Error al actualizar avion");
					break;
				}
				
				TransferAvion transferAvion = new TransferAvion(
						menu.getAvionId(),
						Double.parseDouble(menu.getAltura()),
						Double.parseDouble(menu.getAnchura()),
						Double.parseDouble(menu.getLongitud()),
						Integer.parseInt(menu.getMaxPasajeros()),
						Double.parseDouble(menu.getPeso()),
						menu.getAerolinea()
				);
				GUIModificarAvionImp parent = menu.getParent();
				parent.actualizar(Eventos.ACTUALIZAR_AVION, transferAvion);
				menu.getFrame().dispose();
				break;
			}
			case ELIMINAR_AVION: {
				SAAviones saAviones = this.factoriaSA.nuevoSAAviones();
				GUIModificarAvionImp menu = (GUIModificarAvionImp) datos;

				TransferAvion transferAvion = menu.getSelectedAvion();
				if (!saAviones.eliminarAvion(transferAvion.getAvionId())) {
					this.accion(Eventos.MOSTRAR_MENSAJE, "Error al eliminar avion");
					break;
				}
				
				menu.actualizar(Eventos.ELIMINAR_AVION, transferAvion.getAvionId());
				break;
			}
			default: {
			}
		}
	}
}
