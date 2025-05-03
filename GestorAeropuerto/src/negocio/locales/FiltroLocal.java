package negocio.locales;

import java.time.LocalDate;

public class FiltroLocal {

    private String nombreParcial;
    private Double ingresosMin;
    private Double ingresosMax;
    private Double gastosMin;
    private Double gastosMax;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    // Getters y setters

    public String getNombreParcial() { return nombreParcial; }
    public void setNombreParcial(String nombreParcial) { this.nombreParcial = nombreParcial; }

    public Double getIngresosMin() { return ingresosMin; }
    public void setIngresosMin(Double ingresosMin) { this.ingresosMin = ingresosMin; }

    public Double getIngresosMax() { return ingresosMax; }
    public void setIngresosMax(Double ingresosMax) { this.ingresosMax = ingresosMax; }

    public Double getGastosMin() { return gastosMin; }
    public void setGastosMin(Double gastosMin) { this.gastosMin = gastosMin; }

    public Double getGastosMax() { return gastosMax; }
    public void setGastosMax(Double gastosMax) { this.gastosMax = gastosMax; }

    public LocalDate getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(LocalDate fechaDesde) { this.fechaDesde = fechaDesde; }

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }
}
