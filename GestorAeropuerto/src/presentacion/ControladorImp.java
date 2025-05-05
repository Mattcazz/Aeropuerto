package presentacion;

import javax.swing.JFrame;

import negocio.operaciones.FactoriaSA;
import presentacion.Controlador;
import presentacion.Eventos;

public class ControladorImp extends Controlador {
	public void accion(Eventos evento, Object datos)	{
		switch (evento) {
			case SUB_PANELES: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
				presentacion.paneles.CUs.GUIMenuCUsImp menu = (presentacion.paneles.CUs.GUIMenuCUsImp ) presentacion.paneles.CUs.GUIMenuCUsImp.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_PERSONAL: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
	            presentacion.personal.CUs.GUIMenuCUs menu = (presentacion.personal.CUs.GUIMenuCUs) new presentacion.personal.CUs.GUIMenuCUs();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_EQUIPAJE: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
	            presentacion.equipajes.CUs.GUIMenuCUsImp menu = (presentacion.equipajes.CUs.GUIMenuCUsImp) presentacion.equipajes.CUs.GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_OPERACIONES: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
				presentacion.operaciones.GUIMenuCUsImp menu = (presentacion.operaciones.GUIMenuCUsImp) presentacion.operaciones.GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_FINANCIERA: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
	            presentacion.financiera.CUs.GUIMenuCUsImp menu = (presentacion.financiera.CUs.GUIMenuCUsImp) presentacion.financiera.CUs.GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_INCIDENCIAS: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
	            presentacion.incidencias.CUs.GUIMenuCUsImp menu = (presentacion.incidencias.CUs.GUIMenuCUsImp) presentacion.incidencias.CUs.GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_LOCALES: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
	            presentacion.locales.CUs.GUIMenuCUsImp menu = (presentacion.locales.CUs.GUIMenuCUsImp) presentacion.locales.CUs.GUIMenuCUsImp.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_ESTACIONAMIENTO: {
				JFrame marco = (JFrame) datos;
				
				marco.setVisible(false);
	            presentacion.estacionamiento.CUs.GUIMenuCUsImp menu = (presentacion.estacionamiento.CUs.GUIMenuCUsImp) presentacion.estacionamiento.CUs.GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_VUELOS: {
				JFrame marco = (JFrame) datos;
				negocio.operaciones.FactoriaSA.getInstancia().nuevoSAAsignacion(); // Para que se suscriba a los eventos. 
				marco.setVisible(false);
	            presentacion.vuelos.GUIMenuCUsImp menu = (presentacion.vuelos.GUIMenuCUsImp) presentacion.vuelos.GUIMenuCUs.getInstancia();
				JFrame menuFrame = menu.getFrame();
				menuFrame.setVisible(true);
				break;
			}
	
			case SUB_SEGURIDAD: {
				JFrame marco = (JFrame) datos;
				
//				marco.setVisible(false);
//	            seguridad.presentacion.CUs.GUIMenuCUsImp menu = (seguridad.presentacion.CUs.GUIMenuCUsImp) seguridad.presentacion.CUs.GUIMenuCUs.getInstancia();
//				JFrame menuFrame = menu.getFrame();
//				menuFrame.setVisible(true);
				break;
			}
			
			case SALIR: 
				GUIMenuSubsistemas menu = GUIMenuSubsistemas.getInstancia();
				menu.getFrame().setVisible(true);
				break;
				
			case ABANDONAR:
				JFrame marco = (JFrame) datos;
				marco.dispose();
				System.exit(0);
		}
	}
	

}
