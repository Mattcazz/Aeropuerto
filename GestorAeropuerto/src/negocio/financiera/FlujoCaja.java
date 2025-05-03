package negocio.financiera;

import java.time.LocalDate;

public class FlujoCaja {

	private int id;
	private String nombreCuenta;
	private String tipo;
	private float cantidad;
	private String concepto;
	private LocalDate fecha;
	private String estado;

	public FlujoCaja(TFlujoCaja tFlujo) {
		this.id = tFlujo.getId();
		this.nombreCuenta = tFlujo.getNombreCuenta();
		this.tipo = tFlujo.getTipo();
		this.cantidad = tFlujo.getCantidad();
		this.concepto = tFlujo.getConcepto();
		this.fecha = tFlujo.getFecha();
		this.estado = tFlujo.getEstado();
	}

	public FlujoCaja(int id, String nombreCuenta, String tipo, float cantidad, String concepto, LocalDate fecha,
			String estado) {
		this.id = id;
		this.nombreCuenta = nombreCuenta;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.concepto = concepto;
		this.fecha = fecha;
		this.estado = estado;
	}

	public FlujoCaja(String nombreCuenta, String tipo, float cantidad, String concepto, LocalDate fecha,
			String estado) {
		this.nombreCuenta = nombreCuenta;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.concepto = concepto;
		this.fecha = fecha;
		this.estado = estado;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public String getTipo() {
		return tipo;
	}

	public float getCantidad() {
		return cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setId(int idGenerado) {
		this.id = idGenerado;
	}

	@Override
	public String toString() {
		return "FlujoCaja{" + "id=" + id + ", nombreCuenta='" + nombreCuenta + '\'' + ", tipo='" + tipo + '\''
				+ ", cantidad=" + cantidad + ", concepto='" + concepto + '\'' + ", fecha=" + fecha + ", estado='"
				+ estado + '\'' + '}';
	}



}
