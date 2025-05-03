package negocio.operaciones;

import java.util.ArrayList;
import java.util.List;

public class VIPTemplate extends TemplateAsignacionPuerta{

	@Override
	public List<TransferPuerta> filtrarPuertasDisponibles(List<TransferPuerta> puertasDisponibles) {
	// TODO Auto-generated method stub
		List<TransferPuerta> puertasFiltradas = new ArrayList<>();
		
		for (TransferPuerta puerta : puertasDisponibles) {
			if(puerta.isVip()) {
				puertasFiltradas.add(puerta);
			}
		}
		
		return puertasFiltradas;
	}
	

}
