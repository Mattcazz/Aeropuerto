package integracion.seguridad;

import negocio.seguridad.TransferAcceso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;

public class DAOAccesoImp implements DAOAcceso {

    @Override
    public boolean crearAcceso(TransferAcceso t) {
        String sql = "INSERT INTO Acceso (empleado_dni, zona, duracion) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Usar DNI en lugar de ID para empleado
            stmt.setString(1, t.getEmpleadoDni());
            stmt.setString(2, t.getZona());
            stmt.setInt(3, t.getDuracion());

            int filas = stmt.executeUpdate();
            if (filas == 0) return false;

            // Obtener clave generada para accesoId
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    t.setAccesoId(rs.getInt(1));
                }
            }
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException crearAcceso: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAcceso(int accesoId) {
        String sql = "DELETE FROM Acceso WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accesoId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("SQLException eliminarAcceso: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificarAcceso(TransferAcceso t) {
        String sql = "UPDATE Acceso SET zona = ?, duracion = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getZona());
            stmt.setInt(2, t.getDuracion());
            stmt.setInt(3, t.getAccesoId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("SQLException modificarAcceso: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean verificarAcceso(int accesoId) {
        String sql = "SELECT 1 FROM Acceso WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accesoId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            System.out.println("SQLException verificarAcceso: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<TransferAcceso> listarHistorial() {
        String sql = "SELECT id, empleado_dni, zona, duracion, creado_en " +
                     "FROM Acceso ORDER BY creado_en";
        List<TransferAcceso> lista = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TransferAcceso t = new TransferAcceso();
                t.setAccesoId(rs.getInt("id"));
                t.setEmpleadoDni(rs.getString("empleado_dni"));
                t.setZona(rs.getString("zona"));
                t.setDuracion(rs.getInt("duracion"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException listarHistorial: " + ex.getMessage());
            return null;
        }
        return lista;
    }

    @Override
    public boolean guardarHistoricoAcceso(int accesoId, String descripcion) {
        String sql = "INSERT INTO HistorialAcceso (acceso_id, descripcion) VALUES (?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accesoId);
            stmt.setString(2, descripcion);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException guardarHistoricoAcceso: " + ex.getMessage());
            return false;
        }
    }
}