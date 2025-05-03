package negocio.personal;

import java.util.Collection;

public interface SAEmpleado {
	int altaEmpleado(TransferEmpleado t);
	int asignarRolTurnoFuncion(TransferEmpleado t);
	Collection<TransferEmpleado> mostrarListaEmpleados();
	double generarNomina(int id);
	int eliminarEmpleado(int id);
	TransferEmpleado informeEmpleado(int id);
}
