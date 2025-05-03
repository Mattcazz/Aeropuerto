package presentacion.paneles;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import negocio.paneles.FactoriaSA;
import negocio.paneles.SAPaneles;
import negocio.InfoAeropuerto;
import negocio.paneles.TransferInfoVuelos;
import negocio.paneles.TransferPaneles;
import presentacion.paneles.Controlador;
import presentacion.paneles.Eventos;
import presentacion.paneles.CUs.GUIAñadirDatos;
import presentacion.paneles.CUs.GUIAñadirDatosImp;
import presentacion.paneles.CUs.GUIAñadirPanel;
import presentacion.paneles.CUs.GUIAñadirPanelForm;
import presentacion.paneles.CUs.GUIAñadirPanelFormImp;
import presentacion.paneles.CUs.GUIAñadirPanelImp;
import presentacion.paneles.CUs.GUIEliminarDatos;
import presentacion.paneles.CUs.GUIEliminarDatosImp;
import presentacion.paneles.CUs.GUIEliminarPanel;
import presentacion.paneles.CUs.GUIEliminarPanelImp;
import presentacion.paneles.CUs.GUIFiltrarDatos;
import presentacion.paneles.CUs.GUIFiltrarDatosImp;
import presentacion.paneles.CUs.GUIMenuCUs;
import presentacion.paneles.CUs.GUIMenuCUsImp;
import presentacion.paneles.CUs.GUIModificarDatos;
import presentacion.paneles.CUs.GUIModificarDatosImp;
import presentacion.paneles.CUs.GUIMostrarDatos;
import presentacion.paneles.CUs.GUIMostrarDatosImp;
import presentacion.paneles.CUs.GUIOperarPaneles;
import presentacion.paneles.CUs.GUIOperarPanelesImp;
import presentacion.paneles.CUs.GUIModificarDatosForm;
import presentacion.paneles.CUs.GUIModificarDatosFormImp;
import presentacion.paneles.CUs.GUISeleccionarDatos;
import presentacion.paneles.CUs.GUISeleccionarDatosImp;

public class ControladorImp extends Controlador {
	
	public void accion(int evento, Object datos) {
		
		switch (evento) {

		case (Eventos.ACEPTAR_CAMBIOS): {
			JOptionPane.showMessageDialog(null, "Se aceptarían los cambios");
			break;
		}
		
		case (Eventos.CANCELAR_CAMBIOS): {
			JOptionPane.showMessageDialog(null, "Se cancelarían los cambios");
			break;
		}
		
		case (Eventos.AÑADIR_DATOS): {
			GUISeleccionarDatos gui = GUISeleccionarDatosImp.getInstancia();
			SAPaneles saPaneles =  FactoriaSA.getInstancia().nuevoSAPaneles();
			gui.actualizar(Eventos.FUNCIONALIDAD, saPaneles.mostrar_datos());
			
			break;
		}
		
		case (Eventos.ELIMINAR_DATOS): {
			GUIEliminarDatos gui = GUIEliminarDatosImp.getInstancia();
			gui.actualizar(Eventos.FUNCIONALIDAD, null);
			
			break;
		}
		
		case (Eventos.MODIFICAR_DATOS): {
			GUIModificarDatos gui = GUIModificarDatosImp.getInstancia();
			gui.actualizar(Eventos.FUNCIONALIDAD, null);
			
			break;
		}
		
		case (Eventos.AÑADIR_PANEL): {
			GUIAñadirPanelForm gui = GUIAñadirPanelFormImp.getInstancia();
			SAPaneles saPaneles = FactoriaSA.getInstancia().nuevoSAPaneles();
			saPaneles.añadir_panel((TransferPaneles) datos);
			gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			break;
		}
		
		case (Eventos.ELIMINAR_PANEL): {
			GUIEliminarPanel gui = GUIEliminarPanelImp.getInstancia();
			SAPaneles saPaneles =  FactoriaSA.getInstancia().nuevoSAPaneles();
			saPaneles.eliminar_panel((int) datos);
			gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			break;
		}
		
		case (Eventos.ENCENDER_APAGAR_PANEL): {
			GUIOperarPaneles gui = GUIOperarPanelesImp.getInstancia();
			SAPaneles saPaneles =  FactoriaSA.getInstancia().nuevoSAPaneles();
			Object[] datosArray = (Object[]) datos;
			saPaneles.encender_apagar_panel((int) datosArray[0], (Boolean) datosArray[1]);
			gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			break;
		}
		
		case (Eventos.AÑADIR_AVISO): {
			GUIOperarPaneles gui = GUIOperarPanelesImp.getInstancia();
			SAPaneles saPaneles =  FactoriaSA.getInstancia().nuevoSAPaneles();
			Object[] datosArray = (Object[]) datos;
			saPaneles.añadir_aviso((int) datosArray[0], (String) datosArray[1]);
			gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			break;
		}
		
		case (Eventos.ELIMINAR_AVISO): {
			GUIOperarPaneles gui = GUIOperarPanelesImp.getInstancia();
			SAPaneles saPaneles =  FactoriaSA.getInstancia().nuevoSAPaneles();
			saPaneles.eliminar_aviso((int) datos);
			gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			break;
		}
		
		case (Eventos.ABRIR_MENU): {
			SAPaneles saPaneles =  FactoriaSA.getInstancia().nuevoSAPaneles();
			
			if (datos instanceof GUIAñadirPanelImp) {
				GUIAñadirPanel gui = GUIAñadirPanelImp.getInstancia();	
				gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			}
			else if (datos instanceof GUIEliminarPanelImp) {
				GUIEliminarPanel gui = GUIEliminarPanelImp.getInstancia();
				gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			}
			else if (datos instanceof GUIOperarPanelesImp) {
				GUIOperarPaneles gui = GUIOperarPanelesImp.getInstancia();
				gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			}
			else if (datos instanceof GUIMostrarDatos){
				GUIMostrarDatos gui = GUIMostrarDatosImp.getInstancia();
				gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			}
			else if (datos instanceof GUIFiltrarDatos) {
				GUIFiltrarDatos gui = GUIFiltrarDatosImp.getInstancia();
				gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			}
			else if (datos instanceof GUIAñadirDatos) {
				GUIAñadirDatos gui = GUIAñadirDatosImp.getInstancia();
				gui.actualizar(Eventos.ABRIR_MENU, saPaneles.mostrar_paneles());
			}
			break;
		}
		
		case (Eventos.VOLVER_MENU): {
			
			if (datos instanceof GUIAñadirDatosImp) {
				JFrame menuFrame = ((GUIAñadirDatosImp) datos).getFrame();
				menuFrame.setVisible(true);
			}
			else if (datos instanceof GUIModificarDatosImp) {
				JFrame menuFrame = ((GUIModificarDatosImp) datos).getFrame();
				menuFrame.setVisible(true);
				break;
			}
			else if (datos instanceof GUIAñadirPanelImp) {
				JFrame menuFrame = ((GUIAñadirPanelImp) datos).getFrame();
				menuFrame.setVisible(true);
				break;
			}
			else if (datos instanceof GUIOperarPanelesImp) {
				JFrame menuFrame = ((GUIOperarPanelesImp) datos).getFrame();
				menuFrame.setVisible(true);
				break;
			}
			else {
				GUIMenuCUsImp menu = (GUIMenuCUsImp) GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
			}

			break;
		}
		
		case (Eventos.SIN_DATOS): {
			JOptionPane.showMessageDialog(null, "No se han seleccionado datos");
			break;
		}

		case (Eventos.CAMPOS_VACIOS): {
			JOptionPane.showMessageDialog(null, "Rellena todos los campos");
			break;
		}
		
		case (Eventos.SIN_DATOS_EN_PANEL): {
			JOptionPane.showMessageDialog(null, "No se han añadido datos");
			break;
		}
		
		case (Eventos.TIPO_INCORRECTO_AÑADIR_PANEL): {
			JOptionPane.showMessageDialog(null, "Todos los valores tienen que ser números enteros");
			break;
		}
		
		case (Eventos.DATOS_YA_EXISTENTES): {
			JOptionPane.showMessageDialog(null, "Ya se han añadido estos datos");
			break;
		}
		
		case (Eventos.TERMINAL_INEXISTENTE): {
			
			Integer[] terminales = InfoAeropuerto.terminales;
			String line = "";
			for (int i = 0; i < terminales.length; i++) {
				line = line.concat(" " + String.valueOf(terminales[i]));
			}
			JOptionPane.showMessageDialog(null, "Las terminales disponibles son: " + line);
			break;
		}
		
		case (Eventos.AEROLINEA_INEXISTENTE): {
			
			String[] aerolineas = InfoAeropuerto.aerolineas;
			String line = "";
			for (int i = 0; i < aerolineas.length; i++) {
				line = line.concat(" " + aerolineas[i]);
			}
			JOptionPane.showMessageDialog(null, "Las aerolineas disponibles son: " + line);
			break;
		}
		
		case (Eventos.DATOS_NEGATIVOS): {
			JOptionPane.showMessageDialog(null, "Todos los números deben ser positivos");
			break;
		}
		
		case (Eventos.ID_INEXISTENTE): {
			JOptionPane.showMessageDialog(null, "No existe un panel con ese ID");
			break;
		}
		
		case (Eventos.FORMATO_ORIGEN_DESTINO_INCORRECTO): {
			JOptionPane.showMessageDialog(null, "Origen y Destino tienen un rango de valores desde AAA hasta ZZZ");
			break;
		}
		
		case (Eventos.FORMATO_HORA_INCORRECTO): {
			JOptionPane.showMessageDialog(null, "Llegada y Salida tienen un rango de valores de 00:00 a 23:59");
			break;
		}
		
		

		default: { GUIAñadirDatos.getInstancia().actualizar(Eventos.CLIENTES_LIMPIAR, null); break; }
		}
	}
}
