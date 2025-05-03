package negocio.financiera;

import java.time.LocalDate;

public class TFlujoCajaBuilder {

	private int id = 0;
	private String nombreCuenta;
	private String tipo = "gasto";
	private float cantidad = 0;
	private String concepto = "Sin concepto";
	private LocalDate fecha = LocalDate.now();
	private String estado = "pendiente";

	public TFlujoCajaBuilder conId(int id) {
		this.id = id;
		return this;
	}

	public TFlujoCajaBuilder conNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
		return this;
	}

	public TFlujoCajaBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public TFlujoCajaBuilder conCantidad(float cantidad) {
		this.cantidad = cantidad;
		return this;
	}

	public TFlujoCajaBuilder conConcepto(String concepto) {
		this.concepto = concepto;
		return this;
	}

	public TFlujoCajaBuilder conFecha(LocalDate fecha) {
		this.fecha = fecha;
		return this;
	}

	public TFlujoCajaBuilder conEstado(String estado) {
		this.estado = estado;
		return this;
	}

	public TFlujoCaja build() {
		return new TFlujoCaja(id, nombreCuenta, tipo, cantidad, concepto, fecha, estado);
	}
}
