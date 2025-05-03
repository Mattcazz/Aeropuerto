package integracion;

import integracion.operaciones.DAOAsignacion;
import integracion.operaciones.DAOPuerta;
import integracion.paneles.DAOPaneles;
import integracion.personal.DAOEmpleado;
import integracion.vuelos.DAOAvion;
import integracion.vuelos.DAOVuelo;

public abstract class FactoriaDAO {
	
	static FactoriaDAO instancia= null;
	
	static public FactoriaDAO getInstancia()
	{
		if (instancia == null) instancia= new FactoriaDAOImp();
		
		return instancia;
	}
	
	public abstract DAOPuerta nuevoDAOPuerta();
	public abstract DAOAsignacion nuevoDAOAsignacion();
	public abstract DAOVuelo nuevoDAOVuelo();
	public abstract DAOAvion nuevoDAOAvion();
	public abstract DAOPaneles nuevoDAOPaneles();
	public abstract DAOEmpleado nuevoDAOEmpleado();
	

}
