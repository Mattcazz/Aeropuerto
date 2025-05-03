package negocio.operaciones;

import java.util.ArrayList;
import java.util.List;

public class InternacionalTemplate extends TemplateAsignacionPuerta{
	
	final static private String tipo = "internacional";

	@Override
	public List<TransferPuerta> filtrarPuertasDisponibles(List<TransferPuerta> puertasDisponibles) {
		// TODO Auto-generated method stub
		List<TransferPuerta> puertasFiltradas = new ArrayList<>();
		
		
		for (TransferPuerta puerta : puertasDisponibles) {
			if(tipo.equals(puerta.getTipoPuerta())) {
				puertasFiltradas.add(puerta);
			}
		}
		
		return puertasFiltradas;
	}
	
}
