package negocio.financiera;

import java.util.List;

public class SAFinanzasImp implements SAFinanzas {

	private SACuentaBancaria saCuentaBancaria;
	private SAFlujoCaja saFlujoCaja;

	public SAFinanzasImp() {
		this.saCuentaBancaria = FactoriaSA.getInstancia().createSACuentaBancaria();
		this.saFlujoCaja = FactoriaSA.getInstancia().createSAFlujoCaja();
	}

	@Override
	public void crearCuenta(TCuentaBancaria cuenta) throws Exception {
		saCuentaBancaria.crearCuenta(cuenta);
	}

	@Override
	public void realizarOperacion(TFlujoCaja flujo) throws Exception {
	    OperacionCommand operacion = new OperacionFinanciera(
	        flujo.getNombreCuenta(),
	        flujo.getTipo(),
	        flujo.getCantidad(),
	        flujo.getConcepto()
	    );
	    operacion.ejecutar();

	    TFlujoCaja flujoCompletado = new TFlujoCaja(
	        0, flujo.getNombreCuenta(),
	        flujo.getTipo(),
	        flujo.getCantidad(),
	        flujo.getConcepto(),
	        flujo.getFecha(),
	        "completado"
	    );

	    saFlujoCaja.registrarFlujo(flujoCompletado);
	}

	@Override
	public void completarFlujo(int id) throws Exception {
		saFlujoCaja.completarFlujo(id);
	}

	@Override
	public List<TCuentaBancaria> listarCuentas() throws Exception {
		return saCuentaBancaria.listarCuentas();
	}

	@Override
	public List<TFlujoCaja> listarFlujos() throws Exception {
		return saFlujoCaja.listarFlujos();
	}



	
}

