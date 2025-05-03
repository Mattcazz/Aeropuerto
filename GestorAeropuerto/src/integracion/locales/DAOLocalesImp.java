package integracion.locales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;
import negocio.locales.FiltroLocal;
import negocio.locales.TransferLocal;

public class DAOLocalesImp implements DAOLocales {

    @Override
    public int insertar(TransferLocal l) {
        int idGenerado = -1;
        String sql = "INSERT INTO Locales (empresa, ingresos, gastos, fechacontrato) VALUES (?, ?, ?, ?) RETURNING idlocal";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, l.getEmpresa());
            stmt.setDouble(2, l.getIngresos());
            stmt.setDouble(3, l.getGastos());
            stmt.setDate(4, java.sql.Date.valueOf(l.getFechaContrato()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) idGenerado = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGenerado;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Locales WHERE idlocal = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(TransferLocal l) {
        String sql = "UPDATE Locales SET empresa = ?, ingresos = ?, gastos = ?, fechacontrato = ? WHERE idlocal = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, l.getEmpresa());
            stmt.setDouble(2, l.getIngresos());
            stmt.setDouble(3, l.getGastos());
            stmt.setDate(4, java.sql.Date.valueOf(l.getFechaContrato()));
            stmt.setInt(5, l.getId());

            System.out.println("MODIFICANDO LOCAL:");
            System.out.println("ID: " + l.getId());
            System.out.println("Empresa: " + l.getEmpresa());
            System.out.println("Ingresos: " + l.getIngresos());
            System.out.println("Gastos: " + l.getGastos());
            System.out.println("Fecha: " + l.getFechaContrato());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TransferLocal> mostrarTodos() {
        List<TransferLocal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Locales";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TransferLocal l = new TransferLocal(
                    rs.getInt("idlocal"),
                    rs.getString("empresa"),
                    rs.getDouble("ingresos"),
                    rs.getDouble("gastos"),
                    rs.getDate("fechacontrato").toLocalDate()
                );
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<TransferLocal> filtrarPorNombre(String subcadena) {
        List<TransferLocal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Locales WHERE LOWER(empresa) LIKE ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + subcadena.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TransferLocal l = new TransferLocal(
                    rs.getInt("idlocal"),
                    rs.getString("empresa"),
                    rs.getDouble("ingresos"),
                    rs.getDouble("gastos"),
                    rs.getDate("fechacontrato").toLocalDate()
                );
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    @Override
    public List<TransferLocal> filtrarAvanzado(FiltroLocal f) {
        List<TransferLocal> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Locales WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (f.getNombreParcial() != null && !f.getNombreParcial().isEmpty()) {
            sql.append(" AND LOWER(empresa) LIKE ?");
            parametros.add("%" + f.getNombreParcial().toLowerCase() + "%");
        }
        if (f.getIngresosMin() != null) {
            sql.append(" AND ingresos >= ?");
            parametros.add(f.getIngresosMin());
        }
        if (f.getIngresosMax() != null) {
            sql.append(" AND ingresos <= ?");
            parametros.add(f.getIngresosMax());
        }
        if (f.getGastosMin() != null) {
            sql.append(" AND gastos >= ?");
            parametros.add(f.getGastosMin());
        }
        if (f.getGastosMax() != null) {
            sql.append(" AND gastos <= ?");
            parametros.add(f.getGastosMax());
        }
        if (f.getFechaDesde() != null) {
            sql.append(" AND fechacontrato >= ?");
            parametros.add(java.sql.Date.valueOf(f.getFechaDesde()));
        }
        if (f.getFechaHasta() != null) {
            sql.append(" AND fechacontrato <= ?");
            parametros.add(java.sql.Date.valueOf(f.getFechaHasta()));
        }

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TransferLocal l = new TransferLocal(
                    rs.getInt("idlocal"),
                    rs.getString("empresa"),
                    rs.getDouble("ingresos"),
                    rs.getDouble("gastos"),
                    rs.getDate("fechacontrato").toLocalDate()
                );
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
