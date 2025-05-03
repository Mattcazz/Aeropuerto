package negocio.financiera;

public class TEmpleado {

    private int id;
    private boolean activo;
    private String dni;
    private String nombre;
    private String rol;
    private String turno;
    private String funcion;
    private float nomina;

    public TEmpleado(int id, boolean activo, String dni, String nombre, String rol, String turno, String funcion, float nomina) {
        this.id = id;
        this.activo = activo;
        this.dni = dni;
        this.nombre = nombre;
        this.rol = rol;
        this.turno = turno;
        this.funcion = funcion;
        this.nomina = nomina;
    }

    // Constructor sin ID para futuros registros nuevos
    public TEmpleado(boolean activo, String dni, String nombre, String rol, String turno, String funcion, float nomina) {
        this(0, activo, dni, nombre, rol, turno, funcion, nomina);
    }

    // Getters
    public int getId() { return id; }
    public boolean isActivo() { return activo; }
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
    public String getTurno() { return turno; }
    public String getFuncion() { return funcion; }
    public float getNomina() { return nomina; }

}
