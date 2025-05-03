package negocio.paneles;

import java.util.List;

public interface SAPaneles {
	
	public List<TransferInfoVuelos> mostrar_datos();
	public List<TransferPaneles> mostrar_paneles();
	public void añadir_panel(TransferPaneles transfer);
	public void eliminar_panel(int id_panel);
	public void encender_apagar_panel(int id_panel, Boolean encender);
	public void eliminar_aviso(int id_panel);
	public void añadir_aviso(int id_panel, String aviso);

}
