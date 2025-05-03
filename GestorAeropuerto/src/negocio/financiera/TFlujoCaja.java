package negocio.financiera;

import java.time.LocalDate;

public class TFlujoCaja {

    private int id;
    private String nombreCuenta;
    private String tipo;
    private float cantidad;
    private String concepto;
    private LocalDate fecha;
    private String estado;

    public TFlujoCaja() {}

    public TFlujoCaja(int id, String nombreCuenta, String tipo, float cantidad, String concepto, LocalDate fecha, String estado) {
        this.id = id;
        this.nombreCuenta = nombreCuenta;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    //Constructor sin id
    public TFlujoCaja(String nombreCuenta, String tipo, float cantidad, String concepto, LocalDate fecha, String estado) {
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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nombreCuenta + " | " + tipo + " | " + cantidad + "€";
    }

}
