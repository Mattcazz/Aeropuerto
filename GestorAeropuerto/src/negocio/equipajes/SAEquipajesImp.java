package negocio.equipajes;

import java.util.ArrayList;
import java.util.List;

import integracion.equipajes.DAOEquipajes;
import integracion.vuelos.DAOVuelo;
import negocio.vuelos.TransferVuelo;
import negocio.vuelos.event.ObserverVuelos;
import integracion.FactoriaDAO;

import negocio.vuelos.event.VueloEliminado;

public class SAEquipajesImp implements SAEquipajes, ObserverVuelos {
	
	DAOEquipajes daoEquipaje = FactoriaDAO.getInstancia().nuevoDAOEquipajes();
	DAOVuelo daoVuelos=FactoriaDAO.getInstancia().nuevoDAOVuelo();
	
	public SAEquipajesImp() {
		VueloEliminado.subscribe(this);
	}

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

	@Override
	public void event(String id_vuelo) {
		List<TransferEquipaje> te_list = daoEquipaje.obtenerPorIdVuelo(id_vuelo);
		for (TransferEquipaje te : te_list) {
			daoEquipaje.eliminarEquipaje(te);
		}
		
	}
	
}
