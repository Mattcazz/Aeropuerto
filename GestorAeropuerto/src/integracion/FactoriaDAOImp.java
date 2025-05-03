package integracion;

import integracion.operaciones.DAOAsignacion;
import integracion.operaciones.DAOAsignacionImp;
import integracion.operaciones.DAOPuerta;
import integracion.operaciones.DAOPuertaImp;
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

}
