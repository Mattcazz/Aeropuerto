package integracion.seguridad;


import negocio.seguridad.TransferDispositivo;
import negocio.seguridad.TransferEventoDispositivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;


public class DAODispositivoImp implements DAODispositivo {

    @Override
    public boolean activar(TransferDispositivo t) {
        String sql = "UPDATE Dispositivo SET activo = TRUE WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getId());
            if (stmt.executeUpdate() > 0) {
                t.setEstado(true);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("SQLException activar: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desactivar(TransferDispositivo t) {
        String sql = "UPDATE Dispositivo SET activo = FALSE WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getId());
            if (stmt.executeUpdate() > 0) {
                t.setEstado(false);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("SQLException desactivar: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean configurar(TransferDispositivo t) {
        String sql = "UPDATE Dispositivo SET nivel_sensibilidad = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getNivelSensibilidad());
            stmt.setInt(2, t.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("SQLException configurar: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean registrarEvento(TransferDispositivo t, String descripcion) {
        String sql = "INSERT INTO HistorialDispositivo (dispositivo_id, descripcion) VALUES (?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getId());
            stmt.setString(2, descripcion);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException registrarEvento: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<TransferDispositivo> consultarHistorico(int dispositivoId) {
        // Este método devuelve repetidamente el estado actual del dispositivo.
        // Para un historial detallado, usa getEventosHistorico.
        String sql =
          "SELECT d.id, d.tipo, d.activo, d.nivel_sensibilidad " +
          "FROM Dispositivo d " +
          "JOIN HistorialDispositivo hd ON d.id = hd.dispositivo_id " +
          "WHERE d.id = ? " +
          "ORDER BY hd.timestamp";
        List<TransferDispositivo> lista = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dispositivoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TransferDispositivo t = new TransferDispositivo();
                    t.setId(rs.getInt("id"));
                    t.setTipo(rs.getString("tipo"));
                    t.setEstado(rs.getBoolean("activo"));
                    t.setNivelSensibilidad(rs.getInt("nivel_sensibilidad"));
                    lista.add(t);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException consultarHistorico: " + ex.getMessage());
            return null;
        }
        return lista;
    }

    @Override
    public List<TransferEventoDispositivo> getEventosHistorico(int dispositivoId) {
        String sql =
            "SELECT timestamp, descripcion " +
            "FROM HistorialDispositivo " +
            "WHERE dispositivo_id = ? " +
            "ORDER BY timestamp";

        List<TransferEventoDispositivo> eventos = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dispositivoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eventos.add(new TransferEventoDispositivo(
                        rs.getTimestamp("timestamp").toLocalDateTime(),
                        rs.getString("descripcion")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException getEventosHistorico: " + ex.getMessage());
        }
        return eventos;
    }
    
    @Override
    public TransferDispositivo getDispositivo(int id) throws SQLException {
        String sql = 
          "SELECT id, tipo, activo, nivel_sensibilidad " +
          "FROM Dispositivo " +
          "WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TransferDispositivo t = new TransferDispositivo();
                    t.setId(rs.getInt("id"));
                    t.setTipo(rs.getString("tipo"));
                    t.setEstado(rs.getBoolean("activo"));
                    t.setNivelSensibilidad(rs.getInt("nivel_sensibilidad"));
                    return t;
                }
                return null;  // no existe
            }
        }
    }
}

