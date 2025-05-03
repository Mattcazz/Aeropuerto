package integracion.personal;

import java.util.Collection;

import negocio.personal.TransferEmpleado;

public interface DAOEmpleado {
	TransferEmpleado listarPorDni(String dni);
	TransferEmpleado listarPorID(int id);
	int altaEmpleado(TransferEmpleado t);
	boolean reactivarEmpleado(int id);
	boolean modificarEmpleado(TransferEmpleado t);
	Collection<TransferEmpleado> listarTodos();
	boolean eliminarEmpleado(int id); //Activo = false;
}
