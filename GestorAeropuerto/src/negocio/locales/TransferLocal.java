package negocio.locales;

import java.time.LocalDate;

public class TransferLocal {
    private int id;
    private String empresa;
    private double ingresos;
    private double gastos;
    private LocalDate fechaContrato;

    public TransferLocal(int id, String empresa, double ingresos, double gastos, LocalDate fechaContrato) {
        this.id = id;
        this.empresa = empresa;
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.fechaContrato = fechaContrato;
    }

    public int getId() { return id; }
    public String getEmpresa() { return empresa; }
    public double getIngresos() { return ingresos; }
    public double getGastos() { return gastos; }
    public LocalDate getFechaContrato() { return fechaContrato; }

    public void setId(int id) { this.id = id; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public void setIngresos(double ingresos) { this.ingresos = ingresos; }
    public void setGastos(double gastos) { this.gastos = gastos; }
    public void setFechaContrato(LocalDate fechaContrato) { this.fechaContrato = fechaContrato; }
}
