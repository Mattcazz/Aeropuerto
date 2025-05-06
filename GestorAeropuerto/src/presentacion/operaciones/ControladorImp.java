package presentacion.operaciones;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import negocio.operaciones.FactoriaSA;
import negocio.operaciones.PeticionBloqueo;
import negocio.operaciones.PeticionPuerta;
import negocio.operaciones.SAAsignacion;
import negocio.operaciones.SAPuertas;
import negocio.operaciones.TransferActualizarGUIGestionAsignaciones;
import negocio.operaciones.TransferAsignacion;
import negocio.operaciones.TransferBloqueo;
import negocio.operaciones.TransferCrearAsignacion;
import negocio.operaciones.TransferPuerta;
import negocio.vuelos.TransferVuelo;
import negocio.operaciones.TransferVueloEstrategia;
import presentacion.operaciones.Controlador;
import presentacion.operaciones.Eventos;

public class ControladorImp extends Controlador {
	
	private SAPuertas saPuertas;
	private SAAsignacion saAsignacion;
	private GUIPuertas guiPuertas;
	private GUIInspeccionarPuerta guiModPuerta; 
	private GUIGestionAsignaciones guiGestionAsignaciones;
	private GUIVerPuerta guiVerPuerta;
	private GUICrearPuerta guiCrearPuerta;
	private GUICrearBloqueo guiCrearBloqueo;
	private GUIVerBloqueo guiVerBloqueo;
	private PeticionBloqueo bloqueo; 
	private TransferBloqueo bloq;
	private TransferPuerta puerta;
	private TransferAsignacion asignacion;
	private TransferVueloEstrategia vueloEstrategia;
	private PeticionPuerta peticionPuerta;
	private TransferActualizarGUIGestionAsignaciones  respuestaActualizarGUI;
	
	private Stack<JFrame> stackDeVentanas;
	
	public ControladorImp() {
		saPuertas = FactoriaSA.getInstancia().nuevoSAPuertas();
		saAsignacion = FactoriaSA.getInstancia().nuevoSAAsignacion();
		stackDeVentanas = new Stack<>();
	}
	
	public void accion(int evento, Object datos, JFrame marco)	{
		
		if (marco != null) stackDeVentanas.push(marco);
		
		
		switch (evento){
		
		/************************ Asignaciones ************************/
		
		case Eventos.GESTION_MANUAL_ASIGNACIONES: 
			guiGestionAsignaciones = GUIGestionAsignaciones.getInstancia();
			guiGestionAsignaciones.InitGUI(saAsignacion.prepareGestionarAsignaciones());
			break; 

		case Eventos.ACTUALIZAR_PUERTAS_DISPONIBLES:
			vueloEstrategia = (TransferVueloEstrategia) datos;
			guiGestionAsignaciones.actualizar(Eventos.ACTUALIZAR_PUERTAS_DISPONIBLES_RESP, saPuertas.actualizarPuertasDisponibles(vueloEstrategia));
			break;
		case Eventos.BORRAR_ASIGNACION:
			asignacion = (TransferAsignacion) datos;
			
			respuestaActualizarGUI = saAsignacion.borrarAsignacion(asignacion);
			if (respuestaActualizarGUI.isRespuesta()) {
				guiGestionAsignaciones.actualizar(Eventos.BORRAR_ASIGNACION_RESP_OK, respuestaActualizarGUI);
			}
			
			else {
				guiGestionAsignaciones.actualizar(Eventos.BORRAR_ASIGNACION_RESP_ERROR, null);
			}
			break;
		case Eventos.CREAR_ASIGNACION:
			TransferCrearAsignacion request = (TransferCrearAsignacion) datos;
			
			respuestaActualizarGUI = saAsignacion.crearAsignacion(request);
			if (respuestaActualizarGUI.isRespuesta()) {
				guiGestionAsignaciones.actualizar(Eventos.CREAR_ASIGNACION_RESP_OK, respuestaActualizarGUI);
			}
			
			else {
				guiGestionAsignaciones.actualizar(Eventos.CREAR_ASIGNACION_RESP_ERROR, null);
			}
			
			
			break;
			
			/************************ Puertas ************************/
		
		case Eventos.CONSULTAR_PUERTAS:
			guiPuertas = GUIPuertas.getInstancia();
			guiPuertas.InitGUI(saPuertas.mostrarPuertas());
			break;
		case Eventos.INSPECCIONAR_PUERTA:
			puerta = (TransferPuerta) datos;
			guiModPuerta = GUIInspeccionarPuerta.getInstancia();
			guiModPuerta.InitGUI(saAsignacion.getAsignacionesInPuerta(puerta.getPuertaID()), saPuertas.getBloqueosEnPuerta(puerta.getPuertaID()),puerta);
			break;
		case Eventos.VER_PUERTA:
			puerta = (TransferPuerta) datos;
			guiVerPuerta = GUIVerPuerta.getInstancia();
			guiVerPuerta.InitGUI(puerta);
			break;
		case Eventos.GUARDAR_MODIFICACION_PUERTA:
			peticionPuerta = (PeticionPuerta) datos;
			saPuertas.guardarModificacionPuerta(peticionPuerta);
			
			if (peticionPuerta.getError() != null) {
				guiVerPuerta.actualizar(Eventos.GUARDAR_MODIFICACION_PUERTA_RESP_ERROR, peticionPuerta);
			}else {
				guiVerPuerta.actualizar(Eventos.GUARDAR_MODIFICACION_PUERTA_RESP_OK, peticionPuerta);				
			}
			break;
		case Eventos.CREAR_PUERTA:
			guiCrearPuerta = GUICrearPuerta.getInstancia();
			guiCrearPuerta.InitGUI();
			break;
		case Eventos.GUARDAR_CREAR_PUERTA:
			peticionPuerta = (PeticionPuerta) datos;
			saPuertas.crearPuerta(peticionPuerta);
			
			if (peticionPuerta.getError() != null) {
				guiCrearPuerta.actualizar(Eventos.CREAR_PUERTA_RESP_ERROR, peticionPuerta.getError());
			}else {
				guiCrearPuerta.actualizar(Eventos.CREAR_PUERTA_RESP_OK, null);
				stackDeVentanas.pop();
				
				guiPuertas.actualizar(Eventos.CREAR_PUERTA_RESP_OK, saPuertas.mostrarPuertas());				
			}
			break;
		case Eventos.BORRAR_PUERTA:
			puerta = (TransferPuerta) datos;
			
			if(saPuertas.borrarPuerta(puerta.getPuertaID())) {
				guiModPuerta.actualizar(Eventos.BORRAR_PUERTA_OK, null);
				stackDeVentanas.pop();
				guiPuertas.InitGUI(saPuertas.mostrarPuertas());

			}else {
				guiModPuerta.actualizar(Eventos.BORRAR_PUERTA_ERROR, null);
			}
			
			break;
		case Eventos.CREAR_BLOQUEO:
			puerta = (TransferPuerta) datos;
			guiCrearBloqueo = GUICrearBloqueo.getInstancia();
			guiCrearBloqueo.InitGUI(puerta);
			break;
			
		case Eventos.GUARDAR_BLOQUEO: 
			bloqueo = (PeticionBloqueo) datos;
			saPuertas.bloquearPuerta(bloqueo);
			if (bloqueo.getError() != null) {
				guiCrearBloqueo.actualizar(Eventos.CREAR_BLOQUEO_ERROR, bloqueo.getError());
			}else {
				stackDeVentanas.pop();
				guiCrearBloqueo.actualizar(Eventos.CREAR_BLOQUEO_OK, saPuertas.mostrarPuertas());				
			}
			break;
		case Eventos.VER_BLOQUEO:
			bloq = (TransferBloqueo) datos;
			guiVerBloqueo = GUIVerBloqueo.getInstancia();
			guiVerBloqueo.InitGUI(bloq);
			break;
		case Eventos.BORRAR_BLOQUEO:
			bloq = (TransferBloqueo) datos;
			saPuertas.borrarBloqueo(bloq);
			stackDeVentanas.pop();
			guiModPuerta.InitGUI(saAsignacion.getAsignacionesInPuerta(bloq.getPuertaId()), saPuertas.getBloqueosEnPuerta(bloq.getPuertaId()),
					saPuertas.getPuerta(bloq.getPuertaId()));
			break;
			/************************ Generales ************************/	
			
		case Eventos.VOLVER_ATRAS:
			JFrame marcoAnterior = stackDeVentanas.pop();
			marcoAnterior.setVisible(true);		
			break;
		}
		
		
	}
	

}
