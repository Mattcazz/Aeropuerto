package negocio.personal;

import java.util.Collection;

import integracion.personal.DAOEmpleado;
import integracion.FactoriaDAO;

public class SAEmpleadoImp implements SAEmpleado{

	public int altaEmpleado(TransferEmpleado t) {
		
		DAOEmpleado daoE = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
		TransferEmpleado tE = daoE.listarPorDni(t.getDni());
		
		int id;
		
		if(tE == null) { //Si no existe el emp lo añadimos
			id = daoE.altaEmpleado(t);
		}
		else if(tE.isActivo()) { //Si existe y esta activo 
			id = Codigos.YA_EXISTE;
		}
		else { //Si existe y !activo
			if(daoE.reactivarEmpleado(tE.getId())) {
				id = tE.getId();
			}
			else {
				id = Codigos.ERROR_INTERNO;
			}
		}
		return id;
	}


	public int asignarRolTurnoFuncion(TransferEmpleado t) {
		DAOEmpleado daoE = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
		TransferEmpleado tE = daoE.listarPorDni(t.getDni());
		
		int codigo;
		if(tE == null || !tE.isActivo()) {
			return Codigos.NO_EXISTE;
		}
		if(!t.getRol().isEmpty()) tE.setRol(t.getRol());	
		if(!t.getTurno().isEmpty()) tE.setTurno(t.getTurno());
		if(!t.getFuncion().isEmpty()) tE.setFuncion(t.getFuncion());
		
		if(daoE.modificarEmpleado(tE)) {
			codigo = Codigos.OK;
		}
		else {
			codigo = Codigos.ERROR_INTERNO;
		}
		return codigo;
	}

	public Collection<TransferEmpleado> mostrarListaEmpleados() {
		DAOEmpleado daoE = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
		
		return daoE.listarTodos();
	}

	public double generarNomina(int id) {
		DAOEmpleado daoE = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
		TransferEmpleado tE = daoE.listarPorID(id);
		
		double codigo;
		if(tE == null || !tE.isActivo()) {
			codigo = Codigos.NO_EXISTE;
		}
		else {
			codigo = tE.getNomina();
		}
		return codigo;
	}

	public int eliminarEmpleado(int id) {
	    DAOEmpleado daoE = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
	    TransferEmpleado tE = daoE.listarPorID(id);

	    int codigo;
	    if (tE == null || !tE.isActivo()) {
	        codigo = Codigos.NO_EXISTE;
	    } else if (daoE.eliminarEmpleado(id)) {
	        codigo = Codigos.OK;
	    } else {
	        codigo = Codigos.ERROR_INTERNO;
	    }

	    return codigo;
	}


	public TransferEmpleado informeEmpleado(int id) {
		DAOEmpleado daoE = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
		TransferEmpleado tE = daoE.listarPorID(id);
		
		return tE; //Si no existe el emp -> null
	}
	
}
