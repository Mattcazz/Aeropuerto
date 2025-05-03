package integracion.operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import integracion.DbConnection;
import negocio.operaciones.TransferAsignacion;
import negocio.vuelos.TransferVuelo;

public class DAOAsignacionImp implements DAOAsignacion{

	@Override
	public boolean crearAsignacion(TransferVuelo vuelo, int puerta_id) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO asignacion (vueloId, puertaId, hora_llegada, hora_salida) VALUES (?, ?, ?, ?)";
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
				stmt.setString(1, vuelo.getVueloId());
				stmt.setInt(2, puerta_id);
				stmt.setTimestamp(3, Timestamp.valueOf(vuelo.getTiempoAterrizaje()));
				stmt.setTimestamp(4, Timestamp.valueOf(vuelo.getTiempoSalida()));
				stmt.executeUpdate();
				return true;
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
	}

	@Override
	public boolean borrarAsignacion(int asignacion_id){
		// TODO Auto-generated method stub
		String query = "DELETE FROM asignacion WHERE asignacionId = ?";
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
				stmt.setInt(1, asignacion_id);
				stmt.executeUpdate();
				return true;
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}

	}

	@Override
	public boolean cambiarEstadoAsignacion(int asignacion_id, String nuevo_estado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferAsignacion getAsignacion(int asignacion_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransferAsignacion> getAsignaciones() {
		// TODO Auto-generated method stub
		List<TransferAsignacion> asignaciones = new LinkedList<TransferAsignacion>();
		String query = "SELECT * FROM ASIGNACION ORDER BY hora_llegada ASC";
		
		try(Connection conn = DbConnection.getConnection()){
			
			try (ResultSet rs= conn.createStatement().executeQuery(query)){
				while (rs.next()) {
					asignaciones.add(getAsignacionFromRow(rs));
				}	
			}
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		
		return asignaciones;
	}

	@Override
	public List<TransferAsignacion> getAsignacionesInPuerta(int puerta_id) {
		// TODO Auto-generated method stub
		List<TransferAsignacion> asignaciones = new LinkedList<TransferAsignacion>();
		String query = "SELECT * FROM ASIGNACION WHERE puertaId = ? "
				+ "ORDER BY hora_llegada ASC";
		
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
			
			stmt.setInt(1, puerta_id);
			
			try (ResultSet rs= stmt.executeQuery()){
				while (rs.next()) {
					asignaciones.add(getAsignacionFromRow(rs));
				}	
			}
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		
		return asignaciones;
	}
	
	private TransferAsignacion getAsignacionFromRow(ResultSet rs) throws SQLException  {
		
		TransferAsignacion asignacion = new TransferAsignacion();
		
		try {
			asignacion.setAsiganacionId(rs.getInt("asignacionId"));
			asignacion.setPuertaId(rs.getInt("puertaId"));
			asignacion.setVueloId(rs.getString("vueloId"));
			asignacion.setHora_llegada(rs.getTimestamp("hora_llegada").toLocalDateTime());
			asignacion.setHora_salida(rs.getTimestamp("hora_salida").toLocalDateTime());
			
		}catch (SQLException e) {
			throw e;
		}
		
		return asignacion;
		
		
	}

	@Override
	public List<TransferAsignacion> getAsignacionesDePuertaEnFranja(int puerta_id, LocalDateTime hora_inicio,
			LocalDateTime hora_fin) {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM asignacion WHERE puertaid = ? AND hora_llegada >= ? AND hora_llegada <= ?";
		List<TransferAsignacion> asignaciones = new LinkedList<TransferAsignacion>();
		
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
			
			stmt.setInt(1, puerta_id);
			stmt.setTimestamp(2, Timestamp.valueOf(hora_inicio));
			stmt.setTimestamp(3, Timestamp.valueOf(hora_fin));
			
			try (ResultSet rs= stmt.executeQuery()){
				while (rs.next()) {
					asignaciones.add(getAsignacionFromRow(rs));
				}	
			}
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		
		return asignaciones;

	}

	@Override
	public boolean borrarAsignacionesEnPuerta(int puerta_id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM asignacion WHERE puertaid = ?";
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
				stmt.setInt(1, puerta_id);
				stmt.executeUpdate();
				return true;
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
	}
}
