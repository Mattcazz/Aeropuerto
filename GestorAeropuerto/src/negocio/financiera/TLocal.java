package negocio.financiera;

import java.time.LocalDate;

public class TLocal {

	private int idLocal;
	private String empresa;
	private float ingresos;
	private float gastos;
	private LocalDate fechaContrato;

	public TLocal(int idLocal, String empresa, float ingresos, float gastos, LocalDate fechaContrato) {
		this.idLocal = idLocal;
		this.empresa = empresa;
		this.ingresos = ingresos;
		this.gastos = gastos;
		this.fechaContrato = fechaContrato;
	}

	// Constructor sin idLocal (para inserciones)
	public TLocal(String empresa, float ingresos, float gastos, LocalDate fechaContrato) {
		this(0, empresa, ingresos, gastos, fechaContrato);
	}

	// Getters
	public int getIdLocal() {
		return idLocal;
	}

	public String getEmpresa() {
		return empresa;
	}

	public float getIngresos() {
		return ingresos;
	}

	public float getGastos() {
		return gastos;
	}

	public LocalDate getFechaContrato() {
		return fechaContrato;
	}

}
