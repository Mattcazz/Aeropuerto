package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import negocio.TransferPlaza;
import negocio.EstadoPlaza;

/**
 * Implementación de DAOPlaza basada en PostgreSQL,
 * adaptada para la tabla 'plaza' con cuatro columnas: id, estado, matricula y llegada.
 */
public class DAOPlazaImp implements DAOPlaza {

    @Override
    public List<TransferPlaza> obtenerPlazas() throws SQLException {
        List<TransferPlaza> lista = new ArrayList<>();
        String sql = "SELECT id, estado, matricula, llegada FROM plaza";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                EstadoPlaza estado = EstadoPlaza.valueOf(rs.getString("estado"));
                String matricula = rs.getString("matricula");
                TransferPlaza tp = new TransferPlaza(id, estado, matricula);
                Timestamp ts = rs.getTimestamp("llegada");
                if (ts != null) {
                    tp.setLlegada(ts.toLocalDateTime());
                }
                lista.add(tp);
            }
        }
        return lista;
    }

    @Override
    public TransferPlaza obtenerPlaza(int numero) throws SQLException {
        String sql = "SELECT id, estado, matricula, llegada FROM plaza WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EstadoPlaza estado = EstadoPlaza.valueOf(rs.getString("estado"));
                    String matricula = rs.getString("matricula");
                    TransferPlaza tp = new TransferPlaza(numero, estado, matricula);
                    Timestamp ts = rs.getTimestamp("llegada");
                    if (ts != null) {
                        tp.setLlegada(ts.toLocalDateTime());
                    }
                    return tp;
                }
            }
        }
        return null;
    }

    @Override
    public boolean llegaVehiculo(int numero, String matricula) throws SQLException {
        String sql = "UPDATE plaza SET estado = ?, matricula = ?, llegada = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, EstadoPlaza.OCUPADA.name());
            ps.setString(2, matricula);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, numero);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean abandonaVehiculo(int numero) throws SQLException {
        String sql = "UPDATE plaza SET estado = ?, matricula = ?, llegada = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, EstadoPlaza.DISPONIBLE.name());
            ps.setString(2, "");
            ps.setNull(3, Types.TIMESTAMP);
            ps.setInt(4, numero);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean iniciarMantenimiento(int numero) throws SQLException {
        String sql = "UPDATE plaza SET estado = ?, matricula = ?, llegada = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, EstadoPlaza.MANTENIMIENTO.name());
            ps.setString(2, "");
            ps.setNull(3, Types.TIMESTAMP);
            ps.setInt(4, numero);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean finalizarMantenimiento(int numero) throws SQLException {
        // Igual que abandonaVehiculo: deja disponible sin matricula ni llegada
        return abandonaVehiculo(numero);
    }

    @Override
    public boolean mantenimientoPlaza(int numero) throws SQLException {
        // Alias a iniciarMantenimiento
        return iniciarMantenimiento(numero);
    }

    @Override
    public boolean crearPlaza(TransferPlaza p) throws SQLException {
        String sql = "INSERT INTO plaza (id, estado, matricula, llegada) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getNumero());
            ps.setString(2, p.getEstado().name());
            ps.setString(3, p.getMatricula());
            if (p.getLlegada() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(p.getLlegada()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminarPlaza(int numero) throws SQLException {
        String sql = "DELETE FROM plaza WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean modificarPlaza(int numero, EstadoPlaza nuevoEstado) throws SQLException {
        String sql = "UPDATE plaza SET estado = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado.name());
            ps.setInt(2, numero);
            return ps.executeUpdate() > 0;
        }
    }
}



