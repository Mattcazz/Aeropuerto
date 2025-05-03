package integracion.paneles;

import java.util.List;

import negocio.paneles.TransferInfoVuelos;
import negocio.paneles.TransferPaneles;

public interface DAOPaneles {
	
	public List<TransferInfoVuelos> buscaVuelos();
	public List<TransferPaneles> buscaPaneles();
	public void añadir_panel(TransferPaneles transfer);
	public void eliminar_panel(int id_panel);
	public void encender_apagar_panel(int id_panel, Boolean encender);
	public void eliminar_aviso(int id_panel);
	public void añadir_aviso(int id_panel, String aviso);

}
