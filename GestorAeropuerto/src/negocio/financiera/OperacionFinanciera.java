package negocio.financiera;

public class OperacionFinanciera implements OperacionCommand {

    private String nombreCuenta;
    private String tipoOperacion;
    private float monto;
    private String concepto;

    private SACuentaBancaria saCuentaBancaria;

    public OperacionFinanciera(String nombreCuenta, String tipoOperacion, float monto, String concepto) {
        this.nombreCuenta = nombreCuenta;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.concepto = concepto;
        this.saCuentaBancaria = FactoriaSA.getInstancia().createSACuentaBancaria();
    }

    @Override
    public void ejecutar() throws Exception {
        try {
            saCuentaBancaria.realizarOperacion(nombreCuenta, tipoOperacion, monto, concepto);
        } catch (IllegalArgumentException e) {
            throw new Exception("No se pudo completar la operación: " + e.getMessage());
        }
    }

}
