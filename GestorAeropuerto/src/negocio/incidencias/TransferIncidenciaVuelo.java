package negocio.incidencias;

import java.sql.Date;

public class TransferIncidenciaVuelo extends TransferIncidencia {

	private String identificadorVuelo;
	private SolucionVuelo solucion;

	public TransferIncidenciaVuelo(TipoIncidencia tipo, Date fecha, String descripcion, EstadoIncidencia estado,
			String solucion, float compensacion, String numeroVuelo) {
		super(tipo, fecha, descripcion, estado, compensacion);
		if (solucion != null) {
			switch (solucion.toLowerCase()) {
			case "cambiar puerta embarque":
				this.solucion = SolucionVuelo.CAMBIAR_PUERTA_EMBARQUE;
				break;
			case "contactar tripulacion":
				this.solucion = SolucionVuelo.CONTACTAR_TRIPULACION;
				break;
			case "redirigir pasajeros":
				this.solucion = SolucionVuelo.REDIRIGIR_PASAJEROS;
				break;
			case "reprogramar vuelo":
				this.solucion = SolucionVuelo.REPROGRAMAR_VUELO;
				break;
			case "compensacion economica":
				this.solucion = SolucionVuelo.COMPENSACION_ECONOMICA;
				break;
			default:
				this.solucion = null;
				break;
			}
		}
		this.identificadorVuelo = numeroVuelo;
	}

	public String getId() {
		return this.identificadorVuelo;
	}

	@Override
	public void setId(String id) {
		this.identificadorVuelo = id;
	}

	public void setSolucion(String sol) {
		switch (sol.toLowerCase()) {
		case "cambiar puerta embarque":
			this.solucion = SolucionVuelo.CAMBIAR_PUERTA_EMBARQUE;
			break;
		case "contactar tripulacion":
			this.solucion = SolucionVuelo.CONTACTAR_TRIPULACION;
			break;
		case "redirigir pasajeros":
			this.solucion = SolucionVuelo.REDIRIGIR_PASAJEROS;
			break;
		case "reprogramar vuelo":
			this.solucion = SolucionVuelo.REPROGRAMAR_VUELO;
			break;
		case "compensacion economica":
			this.solucion = SolucionVuelo.COMPENSACION_ECONOMICA;
			break;
		default:
			this.solucion = null;
			break;
		}
	}

	public String getSolucion() {
		String res = "";
		if (this.solucion == SolucionVuelo.CAMBIAR_PUERTA_EMBARQUE) {
			res = "cambiar puerta de embarque";
		} else if (this.solucion == SolucionVuelo.COMPENSACION_ECONOMICA) {
			res = "compensacion economica";
		} else if (this.solucion == SolucionVuelo.CONTACTAR_TRIPULACION) {
			res = "contactar tripulacion";
		} else if (this.solucion == SolucionVuelo.REDIRIGIR_PASAJEROS) {
			res = "redirigir pasajeros";
		} else if (this.solucion == SolucionVuelo.REPROGRAMAR_VUELO) {
			res = "reprogramar vuelo";
		}
		return res;
	}
}
