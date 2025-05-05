package negocio.seguridad;

public class TransferAcceso {
    private int accesoId;          // el PK de la tabla Acceso
    private String empleadoDni;    // el DNI del empleado
    private String zona;
    private int duracion;

    public TransferAcceso() {}

 // Constructor para creación:
    public TransferAcceso(String empleadoDni, String zona, int duracion) {
        this.empleadoDni = empleadoDni;
        this.zona         = zona;
        this.duracion     = duracion;
    }

    public int getAccesoId() { return accesoId; }
    public void setAccesoId(int accesoId) { this.accesoId = accesoId; }
    
    public String getEmpleadoDni() { return empleadoDni; }
    public void setEmpleadoDni(String empleadoDni) { this.empleadoDni = empleadoDni; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
}
