package negocio.personal;

public class TransferEmpleado {
	int id;
	boolean activo;
	String dni;
	String nombre;
	String rol;
	String turno;
	String funcion;
	double nomina;
	
	public TransferEmpleado(int id, boolean activo, String dni, String nombre, String rol, String turno, String funcion,double nomina) {
		this.id = id;
		this.activo = activo;
		this.dni = dni;
		this.nombre = nombre;
		this.rol = rol;
		this.turno = turno;
		this.funcion = funcion;
		this.nomina = nomina;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public double getNomina() {
		return nomina;
	}
	public void setNomina(double nomina) {
		this.nomina = nomina;
	}
	
	
}
