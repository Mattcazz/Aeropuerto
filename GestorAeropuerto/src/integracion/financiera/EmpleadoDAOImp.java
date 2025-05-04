package integracion.financiera;

import negocio.financiera.TEmpleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;

public class EmpleadoDAOImp implements EmpleadoDAO {
    public EmpleadoDAOImp() { }

    @Override
    public List<TEmpleado> obtener() {
        List<TEmpleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleado WHERE activo = TRUE";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TEmpleado empleado = new TEmpleado(
                    rs.getInt("id"),
                    rs.getBoolean("activo"),
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("rol"),
                    rs.getString("turno"),
                    rs.getString("funcion"),
                    rs.getFloat("nomina")
                );
                lista.add(empleado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
