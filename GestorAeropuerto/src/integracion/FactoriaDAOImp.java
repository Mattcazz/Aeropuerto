package integracion;

import integracion.equipajes.DAOEquipajes;
import integracion.equipajes.DAOEquipajesImp;
import integracion.estacionamiento.DAOPlaza;
import integracion.estacionamiento.DAOPlazaProxy;
import integracion.financiera.CuentaBancariaDAO;
import integracion.financiera.CuentaBancariaDAOImp;
import integracion.financiera.EmpleadoDAO;
import integracion.financiera.EmpleadoDAOImp;
import integracion.financiera.FlujoCajaDAO;
import integracion.financiera.FlujoCajaDAOImp;
import integracion.financiera.LocalDAO;
import integracion.financiera.LocalDAOImp;
import integracion.incidencias.DAOIncidencia;
import integracion.incidencias.DAOIncidenciaImp;
import integracion.locales.DAOLocales;
import integracion.locales.DAOLocalesImp;
import integracion.operaciones.DAOAsignacion;
import integracion.operaciones.DAOAsignacionImp;
import integracion.operaciones.DAOPuerta;
import integracion.operaciones.DAOPuertaImp;
import integracion.paneles.DAOPaneles;
import integracion.paneles.DAOPanelesImp;
import integracion.personal.DAOEmpleado;
import integracion.personal.DAOEmpleadoImp;
import integracion.seguridad.DAOAcceso;
import integracion.seguridad.DAOAccesoImp;
import integracion.seguridad.DAODispositivo;
import integracion.seguridad.DAODispositivoImp;
import integracion.vuelos.DAOAvionImp;
import integracion.vuelos.DAOVueloImp;
import integracion.vuelos.DAOAvion;
import integracion.vuelos.DAOVuelo;

public class FactoriaDAOImp extends FactoriaDAO {

	@Override
	public DAOPuerta nuevoDAOPuerta() {
		// TODO Auto-generated method stub
		return new DAOPuertaImp();
	}
	
	@Override
	public DAOAsignacion nuevoDAOAsignacion() {
		// TODO Auto-generated method stub
		return new DAOAsignacionImp();
	}
	
	@Override
	public DAOVuelo nuevoDAOVuelo() {
		// TODO Auto-generated method stub
		return new DAOVueloImp();
	}
	
	@Override
	public DAOAvion nuevoDAOAvion() {
		// TODO Auto-generated method stub
		return new DAOAvionImp();
	}
	
	public DAOPaneles nuevoDAOPaneles()	{
		return new DAOPanelesImp();
	}
	
	public DAOEmpleado nuevoDAOEmpleado()	{
		return new DAOEmpleadoImp();
	}
	
	
	public DAOLocales crearDAOLocales() {
	    return new DAOLocalesImp();
	}
	
	public DAOIncidencia nuevoDAOIncidencia() {
		return new DAOIncidenciaImp();
	}
	
	
	public CuentaBancariaDAO getCuentaBancariaDAO() {
	    return new CuentaBancariaDAOImp();
	}
	
	public FlujoCajaDAO getFlujoCajaDAO() {
	    return new FlujoCajaDAOImp();
	}
	
	@Override
	public EmpleadoDAO getEmpleadoDAO() {
	    return new EmpleadoDAOImp();
	}
	
	@Override
	public LocalDAO getLocalDAO() {
	    return new LocalDAOImp();
	}
	
	@Override
	public DAOEquipajes nuevoDAOEquipajes() {
		return new DAOEquipajesImp();
	}
	
	@Override
	public DAOPlaza nuevoDAOPlaza() {
		return new DAOPlazaProxy();
	}
	
	@Override
	public DAOAcceso nuevoDAOAcceso() {
		return new DAOAccesoImp();
	}
	
	@Override
	public DAODispositivo nuevoDAODispositivo() {
		return new DAODispositivoImp();
	}
}
