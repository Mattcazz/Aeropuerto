package negocio.operaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegistrosDeEstrategias {
	
	private final Map<String, EstrategiaPrioridadAsignacion> mapa_estrategias = new HashMap<>();
	private final static String estrategiaDefault = "Default";
	private final static String estrategiaAerolineaPreferente = "Aerolinea preferente";
	private final static String estrategiaPuertaMenosUsada = "Puerta menos usada";
	private final static String estrategiaPuertaMasPesada = "Puerta mas pesada";
	
	public RegistrosDeEstrategias() {
		mapa_estrategias.put(estrategiaDefault, new EstrategiaDefault());
		mapa_estrategias.put(estrategiaAerolineaPreferente , new EstrategiaPreferenciaAerolinea());
		mapa_estrategias.put(estrategiaPuertaMenosUsada, new EstrategiaMenosUsadas());
		mapa_estrategias.put(estrategiaPuertaMasPesada, new EstrategiaMasPesada());
		
	}
	
	public Set<String> getSetNombreEstrategias() {
        return mapa_estrategias.keySet();
    }
	
	public EstrategiaPrioridadAsignacion getEstrategia(String nombre_estrategia) throws Exception {
		if (mapa_estrategias.containsKey(nombre_estrategia)) return mapa_estrategias.get(nombre_estrategia);
		else {
			throw new Exception("Estrategia no existe");
		}
	}

}
