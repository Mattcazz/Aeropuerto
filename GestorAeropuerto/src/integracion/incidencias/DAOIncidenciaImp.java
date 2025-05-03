package integracion.incidencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;
import negocio.incidencias.EstadoIncidencia;
import negocio.incidencias.IncidenciaFactory;
import negocio.incidencias.TipoIncidencia;
import negocio.incidencias.TransferIncidencia;
import negocio.incidencias.TransferIncidenciaEquipaje;

public class DAOIncidenciaImp implements DAOIncidencia {

	private Connection conexion;

	public DAOIncidenciaImp() {
		try {
			this.conexion = DbConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardar(TransferIncidencia i) {
		String sql = "INSERT INTO Incidencia (id, tipo, fecha, descripcion, estado, solucion, compensacion_economica) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, i.getId());
			stmt.setString(2, i.getTipoString());
			stmt.setDate(3, new java.sql.Date(i.getFecha().getTime()));
			stmt.setString(4, i.getDescripcion());
			stmt.setString(5, i.getEstado());
			stmt.setString(6, null); // En principio cuando se añede una nueva incidencia al sistema no tiene solucion
			stmt.setNull(7, Types.DECIMAL);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al crear incidencia: " + e.getMessage());
		}
	}

	@Override
	public boolean actualizar(TransferIncidencia incidencia) {
		String sql = "UPDATE Incidencia SET fecha = ?, descripcion = ?, estado = ?, solucion = ?, compensacion_economica = ? WHERE id = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setDate(1, incidencia.getFecha());
			stmt.setString(2, incidencia.getDescripcion());
			stmt.setString(3, incidencia.getEstado().toString().toLowerCase());
			stmt.setString(4, incidencia.getSolucion());
			stmt.setFloat(5, incidencia.getCompensacion());
			stmt.setString(6, incidencia.getId());

			int filasActualizadas = stmt.executeUpdate();
			return filasActualizadas > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar la incidencia: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(String id) {
		String sql = "DELETE FROM Incidencia WHERE id = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, id);
			return stmt.executeUpdate() == 1;
		} catch (SQLException e) {
			System.err.println("Error al eliminar incidencia: " + e.getMessage());
			return false;
		}
	}

	@Override
	public TransferIncidencia buscar(String id) {
		String sql = "SELECT * FROM Incidencia WHERE id = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return construirTransfer(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al buscar incidencia: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<TransferIncidencia> listarPorTipo(String tipo) {
		List<TransferIncidencia> lista = new ArrayList<>();
		String sql = "SELECT * FROM Incidencia WHERE tipo = ? AND estado = 'no resuelta'";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, tipo);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					lista.add(construirTransfer(rs));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al listar incidencias por tipo: " + e.getMessage());
		}
		return lista;
	}

	private TransferIncidencia construirTransfer(ResultSet rs) {
		TransferIncidencia t = null;

		try {
			if (rs.getString("tipo").equalsIgnoreCase("vuelo")) {
				t = IncidenciaFactory.crearIncidencia(TipoIncidencia.VUELO, rs.getDate("fecha"),
						rs.getString("descripcion"),
						rs.getString("estado").equalsIgnoreCase("resuelta") ? EstadoIncidencia.RESUELTA
								: EstadoIncidencia.NO_RESUELTA,
						rs.getString("solucion"), rs.getFloat("compensacion_economica"), rs.getString("id"));
			}

			else {
				t = IncidenciaFactory.crearIncidencia(TipoIncidencia.EQUIPAJE, rs.getDate("fecha"),
						rs.getString("descripcion"),
						rs.getString("estado").equalsIgnoreCase("resuelta") ? EstadoIncidencia.RESUELTA
								: EstadoIncidencia.NO_RESUELTA,
						rs.getString("solucion"), rs.getFloat("compensacion_economica"), rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public List<TransferIncidencia> listarPorEstado(String estado) {
		List<TransferIncidencia> lista = new ArrayList<>();
		String sql = "SELECT * FROM Incidencia WHERE estado = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, estado);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					lista.add(construirTransfer(rs));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al listar incidencias por estado: " + e.getMessage());
		}
		return lista;
	}
	
	 @Override
	    public List<TransferIncidencia> obtener() {
	        List<TransferIncidencia> lista = new ArrayList<>();
	        String sql = "SELECT * FROM Incidencia WHERE estado = 'resuelta'";

	        try (PreparedStatement stmt = conexion.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	TransferIncidenciaEquipaje incidencia = new TransferIncidenciaEquipaje();
	            	
	            	incidencia.setCompensacion(rs.getFloat("compensacion_economica"));
	            	incidencia.setDescripcion(rs.getString("descripcion"));
	            	
	                lista.add(incidencia);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return lista;
	    }

}
