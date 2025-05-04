package negocio.equipajes;

import java.util.ArrayList;
import java.util.List;

import integracion.equipajes.DAOEquipajes;
import integracion.vuelos.DAOVuelo;
import negocio.vuelos.TransferVuelo;
import integracion.FactoriaDAO;

public class SAEquipajesImp implements SAEquipajes{
	
	DAOEquipajes daoEquipaje = FactoriaDAO.getInstancia().nuevoDAOEquipajes();
	DAOVuelo daoVuelos=FactoriaDAO.getInstancia().nuevoDAOVuelo();

	@Override
	public List<TransferEquipaje> equipajesPorVuelo(String id_vuelo) {
		// TODO Auto-generated method stub
		
		List<TransferEquipaje> lista = daoEquipaje.obtenerPorIdVuelo(id_vuelo);
		
		return lista;
	}

	@Override
	public List<String> listaVuelos() {
		// TODO Auto-generated method stub
		
		List<TransferVuelo> listaTransferVuelos = daoVuelos.getAllVuelos();
		List<String> lista = new ArrayList<String>();
		for (TransferVuelo transferVuelo : listaTransferVuelos) {
			lista.add(transferVuelo.getVueloId());
		}
		
		return lista;
	}

	@Override
	public void modificarEquipaje(TransferEquipaje equ) {
		// TODO Auto-generated method stub
		daoEquipaje.modificarEquipaje(equ);
	}

	@Override
	public void eliminarEquipaje(TransferEquipaje equ) {
		// TODO Auto-generated method stub
		daoEquipaje.eliminarEquipaje(equ);
	}

	@Override
	public void registrarEquipaje(TransferEquipaje te) {
		// TODO Auto-generated method stub
		daoEquipaje.anadirEquipaje(te);
	}
	
}
