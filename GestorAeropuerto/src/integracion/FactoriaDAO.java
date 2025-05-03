package integracion;

import integracion.financiera.CuentaBancariaDAO;
import integracion.financiera.EmpleadoDAO;
import integracion.financiera.FlujoCajaDAO;
import integracion.financiera.LocalDAO;
import integracion.incidencias.DAOIncidencia;
import integracion.locales.DAOLocales;
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
	
	public abstract DAOLocales crearDAOLocales();
	
	public abstract DAOIncidencia nuevoDAOIncidencia();

	
    public abstract CuentaBancariaDAO getCuentaBancariaDAO();
    public abstract FlujoCajaDAO getFlujoCajaDAO();
    public abstract EmpleadoDAO getEmpleadoDAO();
    public abstract LocalDAO getLocalDAO(); 
}
