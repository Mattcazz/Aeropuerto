package integracion.financiera;

import negocio.financiera.TLocal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;

public class LocalDAOImp implements LocalDAO {
    public LocalDAOImp() { }

    @Override
    public List<TLocal> obtener() {
        List<TLocal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Locales";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TLocal local = new TLocal(
                    rs.getInt("IDLocal"),
                    rs.getString("Empresa"),
                    rs.getFloat("Ingresos"),
                    rs.getFloat("Gastos"),
                    rs.getDate("FechaContrato").toLocalDate()
                );
                lista.add(local);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
