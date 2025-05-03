package negocio.incidencias;

import java.sql.Date;

public class TransferIncidenciaEquipaje extends TransferIncidencia {

	
	private String[] idPasajero;
	private SolucionEquipaje solucion;

	
	public TransferIncidenciaEquipaje(TipoIncidencia tipo, Date fecha, String descripcion, EstadoIncidencia estado,
			String solucion, float compensacion, String nombreCompleto) {
		super(tipo, fecha, descripcion, estado, compensacion);
		if (solucion != null) {
			switch (solucion.toLowerCase()) {
			case "enviar a domicilio":
				this.solucion = SolucionEquipaje.ENVIAR_A_DOMICILIO;
				break;
			case "enviar a otro vuelo":
				this.solucion = SolucionEquipaje.ENVIAR_A_OTRO_VUELO;
				break;
			case "retirar manualmente":
				this.solucion = SolucionEquipaje.RETIRAR_MANUALMENTE;
				break;
			case "almacenar temporalmente":
				this.solucion = SolucionEquipaje.ALMACENAR_TEMPORALMENTE;
				break;
			case "compensacion economica":
				this.solucion = SolucionEquipaje.COMPENSACION_ECONOMICA;
				break;
			default:
				this.solucion = null;
				break;
			}
		}

		// Se inicializa el array con nombre y apellido
		// Dividir el id en nombre y apellido utilizando un espacio
		String[] partes = nombreCompleto.split(" ");
		// Verificar que haya al menos dos elementos
		if (partes.length == 2) {
			this.idPasajero = new String[] { partes[0], partes[1] }; // Asignar nombre y apellido
		} else {
			throw new IllegalArgumentException("El ID debe contener un nombre y un apellido separados por un espacio.");
		}
	}

	public TransferIncidenciaEquipaje() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getId() {
		return String.join(" ", this.idPasajero);
	}

	public void setIdEquipaje(String nombre, String apellido) {
		this.idPasajero = new String[] { nombre, apellido };
	}

	public String getNombre() {
		return idPasajero[0];
	}

	public String getApellido() {
		return idPasajero[1];
	}

	@Override
	public void setId(String id) {
		
		String[] partes = id.split(" ");
		if (partes.length == 2) {
			this.idPasajero = new String[] { partes[0], partes[1] }; // Asignar nombre y apellido
		} else {
			throw new IllegalArgumentException("El ID debe contener un nombre y un apellido separados por un espacio.");
		}
	}

	public void setSolucion(String sol) {
		switch (sol.toLowerCase()) {
		case "enviar a domicilio":
			this.solucion = SolucionEquipaje.ENVIAR_A_DOMICILIO;
			break;
		case "enviar a otro vuelo":
			this.solucion = SolucionEquipaje.ENVIAR_A_OTRO_VUELO;
			break;
		case "retirar manualmente":
			this.solucion = SolucionEquipaje.RETIRAR_MANUALMENTE;
			break;
		case "almacenar temporalmente":
			this.solucion = SolucionEquipaje.ALMACENAR_TEMPORALMENTE;
			break;
		default:
			this.solucion = null;
			break;
		}
	}

	public String getSolucion() {
		String res = "";
		if (this.solucion == SolucionEquipaje.ALMACENAR_TEMPORALMENTE) {
			res = "almacenar temporalmente";
		} else if (this.solucion == SolucionEquipaje.COMPENSACION_ECONOMICA) {
			res = "compensacion economica";
		} else if (this.solucion == SolucionEquipaje.ENVIAR_A_DOMICILIO) {
			res = "enviar a domicilio";
		} else if (this.solucion == SolucionEquipaje.ENVIAR_A_OTRO_VUELO) {
			res = "enviar a otro vuelo";
		} else if (this.solucion == SolucionEquipaje.RETIRAR_MANUALMENTE) {
			res = "retirar manualmente";
		}

		return res;
	}

}
