package integracion.vuelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;
import negocio.vuelos.TransferAvion;

public class DAOAvionImp implements DAOAvion {
	private TransferAvion getAvionFromRow(ResultSet rs) throws SQLException {	
		TransferAvion avion = new TransferAvion();
		avion.setAvionId(rs.getString("avionId"));
		avion.setAltura(rs.getDouble("altura"));
		avion.setAnchura(rs.getDouble("anchura"));
		avion.setLongitud(rs.getDouble("longitud"));
		avion.setMaxPasajeros(rs.getInt("max_pasajeros"));
		avion.setPeso(rs.getDouble("peso"));
		avion.setAerolinea(rs.getString("aerolinea"));
		
		return (avion);
	}
	
	@Override
	public TransferAvion getAvion(String avion_id) {
		TransferAvion avion = new TransferAvion();
		String query = "SELECT * FROM AVION WHERE avionId = ?"; // based
		
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, avion_id);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) avion = getAvionFromRow(rs);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return (null);
		}
		
		return (avion);
	}

	@Override
	public boolean insertarAvion(TransferAvion avion) {
		String query = 
			    "INSERT INTO AVION (avionId, altura, anchura, longitud, max_pasajeros, peso, aerolinea) " +
			    "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query)) {
		    ps.setString(1, avion.getAvionId());
		    ps.setDouble(2, avion.getAltura());
		    ps.setDouble(3, avion.getAnchura());
		    ps.setDouble(4, avion.getLongitud());
		    ps.setInt(5, avion.getMaxPasajeros());
		    ps.setDouble(6, avion.getPeso());
		    ps.setString(7, avion.getAerolinea());
		    
		    ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return (false);
		}
		
		return true;
	}

	@Override
	public boolean actualizarAvion(TransferAvion avion) {
		String query =
			    "UPDATE AVION SET "
			  + "altura = ?, "
			  + "anchura = ?, "
			  + "longitud = ?, "
			  + "max_pasajeros = ?, "
			  + "peso = ?, "
			  + "aerolinea = ? "
			  + "WHERE avionId = ?";

		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query)) {
		    ps.setDouble(1, avion.getAltura());
		    ps.setDouble(2, avion.getAnchura());
		    ps.setDouble(3, avion.getLongitud());
		    ps.setInt(4, avion.getMaxPasajeros());
		    ps.setDouble(5, avion.getPeso());
		    ps.setString(6, avion.getAerolinea());
		    ps.setString(7, avion.getAvionId());

		    int actualizada = ps.executeUpdate();
		    if (actualizada == 0) {
		    	System.out.println("No hay un avion con ese ID");
		    	return (false);
		    }
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return (false);
		}

		return (true);
	}

	@Override
	public boolean eliminarAvion(String avionId) {
		String query = "DELETE FROM AVION WHERE avionId = ?";

		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query)) {
		    ps.setString(1, avionId);
		    
		    int filasEliminadas = ps.executeUpdate();
		    if (filasEliminadas == 0) {
		    	System.out.println("No hay un avion con ese ID");
		    	return (false);
		    }
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return (false);
		}
		
		return (true);
	}

	@Override
	public List<String> getAllAvionIds() {
		String query = "SELECT DISTINCT avionId from AVION";
		
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery()) {
			List<String> avionIds = new ArrayList<>();
		    while (rs.next()) {
		        avionIds.add(rs.getString("avionId"));
		    }
		    return (avionIds);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return (null);
		}
	}
}