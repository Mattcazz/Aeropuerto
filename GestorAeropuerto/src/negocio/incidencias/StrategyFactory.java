package negocio.incidencias;

public class StrategyFactory {

	public static SolucionStrategy getStrategy(TipoIncidencia tipo) {
		return switch (tipo) {
		case VUELO -> new SolucionVueloStrategy();
		case EQUIPAJE -> new SolucionEquipajeStrategy();
		};
	}
}
