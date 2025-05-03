package negocio.paneles;

import java.util.List;

import integracion.paneles.DAOPaneles;
import integracion.FactoriaDAO;

public class SAPanelesImp implements SAPaneles{
	
	DAOPaneles daoPaneles = FactoriaDAO.getInstancia().nuevoDAOPaneles();
	
	public List<TransferInfoVuelos> mostrar_datos() {
		
		return daoPaneles.buscaVuelos();
	}
	
	public List<TransferPaneles> mostrar_paneles() {
		
		return daoPaneles.buscaPaneles();
	}
	
	public void añadir_panel(TransferPaneles transfer) {
		daoPaneles.añadir_panel(transfer);
	}
	
	public void eliminar_panel(int id_panel) {
		daoPaneles.eliminar_panel(id_panel);
	}
	
	public void encender_apagar_panel(int id_panel, Boolean encender) {
		daoPaneles.encender_apagar_panel(id_panel, encender);
	}
	
	public void eliminar_aviso(int id_panel) {
		daoPaneles.eliminar_aviso(id_panel);
	}
	public void añadir_aviso(int id_panel, String aviso) {
		daoPaneles.añadir_aviso(id_panel, aviso);
	}
	
}
