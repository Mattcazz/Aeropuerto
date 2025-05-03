package integracion.personal;

public class Consultas {
	public static final String listarEmpleadoPorId = "SELECT * FROM empleado WHERE id = ?";
	public static final String listarEmpleadoPorDni = "SELECT * FROM empleado WHERE dni = ?";
	public static final String altaEmpleado = "INSERT INTO Empleado (activo,dni,nombre,rol,turno,funcion,nomina) VALUES (?,?,?,?,?,?,?)";
	public static final String reactivarEmpleado = "UPDATE Empleado SET activo = true";
	public static final String modificarEmpleado = "UPDATE Empleado SET activo = ?, dni = ?, nombre = ?, rol = ?, turno = ?, funcion = ?, nomina = ? WHERE dni = ?";
	public static final String listarTodos = "SELECT * FROM Empleado";
	public static final String eliminarEmpleado = "UPDATE Empleado SET activo = false WHERE id = ?";
}
