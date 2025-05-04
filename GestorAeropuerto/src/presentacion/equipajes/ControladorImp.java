package presentacion.equipajes;

import java.awt.event.ActionListener;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import negocio.equipajes.FactoriaSA;
import negocio.equipajes.SAEquipajes;
import negocio.equipajes.TransferEquipaje;
import presentacion.equipajes.Controlador;
import presentacion.equipajes.Eventos;
import presentacion.equipajes.CUs.GUIAnadirEquipaje;
import presentacion.equipajes.CUs.GUIAnadirEquipajeImp;
import presentacion.equipajes.CUs.GUIEquipajesVuelo;
import presentacion.equipajes.CUs.GUIEquipajesVueloImp;
import presentacion.equipajes.CUs.GUIModificacionEquipaje;
import presentacion.equipajes.CUs.GUIModificacionEquipajeImp;
import presentacion.equipajes.CUs.GUISeleccionVuelo;
import presentacion.equipajes.CUs.GUISeleccionVueloImp;


public class ControladorImp extends Controlador{
	
	private SAEquipajes sa_equipajes;
	
	public ControladorImp() {
		sa_equipajes = FactoriaSA.getInstancia().nuevoSAEquipajes();
	}
	
	@Override
	public void accion(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch(evento) {
		
		case(Eventos.ACCESO):{
			GUISeleccionVuelo gui = GUISeleccionVuelo.getInstancia();
			((GUISeleccionVueloImp)gui).mostrar();
			List<String> id_vuelos = this.sa_equipajes.listaVuelos();
			gui.actualizar(Eventos.ACCESO, id_vuelos);
			break;
			
		}
			
		case(Eventos.ACCEDER_VUELO):{
			String id_vuelo = (String) datos;
			GUIEquipajesVuelo gui = GUIEquipajesVuelo.getInstancia();
			((GUIEquipajesVueloImp)gui).mostrar(id_vuelo);
			((GUIEquipajesVueloImp)gui).setIdVuelo(id_vuelo);
			List<TransferEquipaje> equipajes_vuelo = this.sa_equipajes.equipajesPorVuelo(id_vuelo);
			gui.actualizar(Eventos.ACCEDER_VUELO, equipajes_vuelo);
			
			
			break;
			
		}
		
		case Eventos.MODIFICAR_EQUIPAJE:{
			GUIModificacionEquipaje gui = GUIModificacionEquipaje.getInstancia();
			((GUIModificacionEquipajeImp)gui).mostrar();
			TransferEquipaje te = (TransferEquipaje)datos;
			gui.actualizar(Eventos.MODIFICAR_EQUIPAJE, te);
			
			break;
		}
		
		case Eventos.EJECUTAR_MODIFICACION:{
			TransferEquipaje equ = (TransferEquipaje) datos;
			this.sa_equipajes.modificarEquipaje(equ);
			List<TransferEquipaje> equipajes_vuelo = this.sa_equipajes.equipajesPorVuelo(equ.getIdVuelo());
		    GUIEquipajesVuelo gui = GUIEquipajesVuelo.getInstancia();
		    ((GUIEquipajesVueloImp)gui).setIdVuelo(equ.getIdVuelo());
		    gui.actualizar(Eventos.ACCEDER_VUELO, equipajes_vuelo); 
			break;
		}
		
		
		case Eventos.ELIMINAR_EQUIPAJE:{
			TransferEquipaje equ = (TransferEquipaje) datos;
			this.sa_equipajes.eliminarEquipaje(equ);
			List<TransferEquipaje> equipajes_vuelo = this.sa_equipajes.equipajesPorVuelo(equ.getIdVuelo());
		    GUIEquipajesVuelo gui = GUIEquipajesVuelo.getInstancia();
		    ((GUIEquipajesVueloImp)gui).setIdVuelo(equ.getIdVuelo());
		    gui.actualizar(Eventos.ACCEDER_VUELO, equipajes_vuelo); 
			break;
		}
		
		case Eventos.ANADIR_EQUIPAJE:{
			String id_vuelo=(String) datos;
			GUIAnadirEquipaje gui = GUIAnadirEquipaje.getInstancia();
			((GUIAnadirEquipajeImp)gui).mostrar();
			gui.actualizar(Eventos.ANADIR_EQUIPAJE, id_vuelo); 
			break;
		}
		
		case (Eventos.REGISTRAR_EQUIPAJE):{
			TransferEquipaje te = (TransferEquipaje) datos;
			this.sa_equipajes.registrarEquipaje(te);
			List<TransferEquipaje> equipajes_vuelo = this.sa_equipajes.equipajesPorVuelo(te.getIdVuelo());
		    GUIEquipajesVuelo gui = GUIEquipajesVuelo.getInstancia();
		    ((GUIEquipajesVueloImp)gui).setIdVuelo(te.getIdVuelo());
		    
		    gui.actualizar(Eventos.ACCEDER_VUELO, equipajes_vuelo);
			
			break;
		}
		
		default:{
			System.out.println("Evento no reconocido: " + evento);
			break;
		}
		}
	}

}
