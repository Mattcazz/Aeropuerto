package negocio.operaciones;

import negocio.vuelos.TransferVuelo;

public class FactoriaTemplateAsignacionPuerta {
	
	static final private String internacional = "internacional";
	static final private String domestico = "domestico";

	public static TemplateAsignacionPuerta getTemplatePara(TransferVuelo vuelo) {
	        
		if (vuelo.isVip()) return new VIPTemplate();
		
		switch (vuelo.getTipoVuelo()) {
	            case internacional:
	                return new InternacionalTemplate();
	            case domestico:
	            default:
	                return new DomesticoTemplate();
	        }
	    }
}
