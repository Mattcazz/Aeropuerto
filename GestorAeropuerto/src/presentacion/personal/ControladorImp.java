package presentacion.personal;

import java.util.Collection;

import javax.swing.JOptionPane;

import negocio.personal.Codigos;
import negocio.personal.FactoriaSA;
import negocio.personal.TransferEmpleado;
import presentacion.personal.CUs.GUIAnadir;
import presentacion.personal.CUs.GUIAsignacion;
import presentacion.personal.CUs.GUICrearNomina;
import presentacion.personal.CUs.GUIEliminar;
import presentacion.personal.CUs.GUIGenerarInforme;
import presentacion.personal.CUs.GUIMenuCUs;
import presentacion.personal.CUs.GUIMostrarLista;

public class ControladorImp extends Controlador {
	public int accion(int evento, Object datos)	{
		switch (evento){
		case Eventos.MENU_EMPLEADO:
			GUIMenuCUs menu = new GUIMenuCUs();
			break;
		
		case Eventos.VISTA_ASIGNACION: 
			GUIAsignacion asignacion = new GUIAsignacion();
			break;
		case Eventos.ASIGNACION:
			return FactoriaSA.getInstancia().nuevoSAEmpleado().asignarRolTurnoFuncion((TransferEmpleado) datos);	
		
		case Eventos.VISTA_MOSTRARLISTA: {
		    Collection<TransferEmpleado> empleados = FactoriaSA.getInstancia().nuevoSAEmpleado().mostrarListaEmpleados();
		    if (empleados == null || empleados.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "No hay empleados para mostrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
		    } else {
		        new GUIMostrarLista(empleados);
		    }
		    break;
		}
		case Eventos.MOSTRARLISTA: {
		    Collection<TransferEmpleado> empleados = FactoriaSA.getInstancia().nuevoSAEmpleado().mostrarListaEmpleados();
		    return empleados != null ? Codigos.OK : Codigos.ERROR_INTERNO;
		}
			
		case Eventos.VISTA_ANADIR: 
			GUIAnadir anadir = new GUIAnadir();
			break;
		case Eventos.ANADIR:
			return FactoriaSA.getInstancia().nuevoSAEmpleado().altaEmpleado((TransferEmpleado) datos);
					
		case Eventos.VISTA_ELIMINAR: 
			GUIEliminar eliminar = new GUIEliminar();
			break;
		case Eventos.ELIMINAR:
			return FactoriaSA.getInstancia().nuevoSAEmpleado().eliminarEmpleado((int) datos);
			
		case Eventos.VISTA_CREARNOMINA:
			GUICrearNomina nomina = new GUICrearNomina();
		    break;

		case Eventos.CREARNOMINA:
		    return (int) FactoriaSA.getInstancia().nuevoSAEmpleado().generarNomina((int) datos);
		    
		 case Eventos.VISTA_GENERARINFORME:
			 GUIGenerarInforme informe = new GUIGenerarInforme();
             break;

         case Eventos.GENERARINFORME:
             TransferEmpleado emp = FactoriaSA.getInstancia().nuevoSAEmpleado().informeEmpleado((int) datos);
             if (emp == null || !emp.isActivo()) {
                 return Codigos.NO_EXISTE;
             } else {
                 new GUIGenerarInforme(emp);
                 return Codigos.OK;
             }
		}
		return Codigos.OK;
	}
}