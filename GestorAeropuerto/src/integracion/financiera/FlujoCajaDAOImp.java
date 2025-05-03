package integracion.financiera;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;
import negocio.financiera.FlujoCaja;

public class FlujoCajaDAOImp implements FlujoCajaDAO {

	private Connection conexion;

	public FlujoCajaDAOImp() {
		try {
			this.conexion = DbConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void guardar(FlujoCaja flujo) {
		String sql = "INSERT INTO flujo_caja (nombre_cuenta, tipo, cantidad, concepto, fecha, estado) "
				+ "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, flujo.getNombreCuenta());
			stmt.setString(2, flujo.getTipo());
			stmt.setFloat(3, flujo.getCantidad());
			stmt.setString(4, flujo.getConcepto());
			stmt.setDate(5, Date.valueOf(flujo.getFecha()));
			stmt.setString(6, flujo.getEstado());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int idGenerado = rs.getInt("id");
				flujo.setId(idGenerado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FlujoCaja> obtenerTodos() {
		List<FlujoCaja> lista = new ArrayList<>();
		String sql = "SELECT * FROM flujo_caja ORDER BY fecha DESC";

		try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				FlujoCaja flujo = new FlujoCaja(rs.getInt("id"), rs.getString("nombre_cuenta"), rs.getString("tipo"),
						rs.getFloat("cantidad"), rs.getString("concepto"), rs.getDate("fecha").toLocalDate(),
						rs.getString("estado"));
				lista.add(flujo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<FlujoCaja> obtenerPorEstado(String estado) {
		List<FlujoCaja> lista = new ArrayList<>();
		String sql = "SELECT * FROM flujo_caja WHERE estado = ? ORDER BY fecha DESC";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, estado);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				FlujoCaja flujo = new FlujoCaja(rs.getInt("id"), rs.getString("nombre_cuenta"), rs.getString("tipo"),
						rs.getFloat("cantidad"), rs.getString("concepto"), rs.getDate("fecha").toLocalDate(),
						rs.getString("estado"));
				lista.add(flujo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public FlujoCaja buscarPorId(int id) {
		String sql = "SELECT * FROM flujo_caja WHERE id = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new FlujoCaja(rs.getInt("id"), rs.getString("nombre_cuenta"), rs.getString("tipo"),
						rs.getFloat("cantidad"), rs.getString("concepto"), rs.getDate("fecha").toLocalDate(),
						rs.getString("estado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void actualizarEstado(int id, String nuevoEstado) {
		String sql = "UPDATE flujo_caja SET estado = ? WHERE id = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nuevoEstado);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean existeFlujoSimilar(FlujoCaja flujo) {
		String sql = "SELECT COUNT(*) FROM flujo_caja WHERE nombre_cuenta = ? AND tipo = ? AND concepto = ? AND fecha = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, flujo.getNombreCuenta());
			stmt.setString(2, flujo.getTipo());
			stmt.setString(3, flujo.getConcepto());
			stmt.setDate(4, Date.valueOf(flujo.getFecha()));
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt(1) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
