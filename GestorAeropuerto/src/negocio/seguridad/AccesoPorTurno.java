package negocio.seguridad;


import integracion.personal.DAOEmpleado;
import negocio.personal.TransferEmpleado;
import integracion.FactoriaDAO;
import java.time.LocalTime;

/**
 * Solo permite accesos en el turno asignado al empleado.
 */
public class AccesoPorTurno implements EstrategiaAcceso {

    @Override
    public void gestionarAcceso(TransferAcceso datos) {
        // 1) Obtener el turno real del empleado desde el subsistema Empleados
        DAOEmpleado daoEmp = FactoriaDAO.getInstancia().nuevoDAOEmpleado();
        TransferEmpleado transferEmpleado = daoEmp.listarPorDni(String.valueOf(datos.getEmpleadoDni())); // por que hacemos String.valueOf sobre algo que ya es String???
        String turnoAsignado = transferEmpleado.getTurno();
        if (turnoAsignado == null) {
            throw new RuntimeException("No se encontró el turno para empleado ID=" + datos.getEmpleadoDni());
        }

        // 2) Determinar si la hora actual está dentro de dicho turno
        LocalTime ahora = LocalTime.now();
        boolean enTurno;
        switch (turnoAsignado) {
            case "Mañana":
                enTurno = !ahora.isBefore(LocalTime.of(6, 0))
                        && ahora.isBefore(LocalTime.of(14, 0));
                break;
            case "Tarde":
                enTurno = !ahora.isBefore(LocalTime.of(14, 0))
                        && ahora.isBefore(LocalTime.of(22, 0));
                break;
            case "Noche":
                enTurno = ahora.isAfter(LocalTime.of(22, 0))
                        || ahora.isBefore(LocalTime.of(6, 0));
                break;
            default:
                throw new RuntimeException("Turno desconocido: " + turnoAsignado);
        }

        // 3) Aplicar la política
        if (!enTurno) {
            throw new RuntimeException(
                "Acceso fuera de su turno asignado (" + turnoAsignado + "). Hora actual: " + ahora
            );
        }

        // 4) Si todo es correcto
        System.out.println("[PorTurno] Acceso permitido en turno " + turnoAsignado
            + " para zona " + datos.getZona());
    }
}
