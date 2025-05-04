package integracion.vuelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import integracion.DbConnection;
import negocio.vuelos.TransferVuelo;

public class DAOVueloImp implements DAOVuelo {
	private TransferVuelo getVueloFromRow(ResultSet rs) throws SQLException {
		TransferVuelo vuelo = new TransferVuelo();
		vuelo.setVueloId(rs.getString("vueloId"));
		vuelo.setAvionId(rs.getString("avionId"));
		vuelo.setDestino(rs.getString("destino"));
		vuelo.setOrigen(rs.getString("origen"));
		vuelo.setTiempoAterrizaje(rs.getTimestamp("tiempo_aterrizaje").toLocalDateTime());
		vuelo.setTiempoSalida(rs.getTimestamp("tiempo_salida").toLocalDateTime());
		vuelo.setTipoVuelo(rs.getString("tipo_vuelo"));
		vuelo.setVip(rs.getBoolean("vip"));
	
		return vuelo;
	}
	
	@Override
	public TransferVuelo getVuelo(String vueloId) {
		TransferVuelo avion = null;
		String query = "SELECT * FROM VUELO WHERE vueloId = ?";
		
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, vueloId);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					avion = getVueloFromRow(rs);
				}
			}
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return (null);
		}

		return (avion);
	}
	
	@Override
	public List<TransferVuelo> getAllVuelos() {
		String query = "SELECT * FROM VUELO";
		
		List<TransferVuelo> vuelos = new LinkedList<TransferVuelo>();
		
		try (Connection conn = DbConnection.getConnection()) {
			try (ResultSet rs = conn.createStatement().executeQuery(query)){
				while (rs.next()) {
					vuelos.add(getVueloFromRow(rs));
				}
			}	
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return (null);
		}
		return (vuelos);
	}

	@Override
	public List<TransferVuelo> getVuelosPendientes() {
		String query = "SELECT * FROM VUELO WHERE vueloId NOT IN (SELECT vueloid FROM ASIGNACION)";
		
		List<TransferVuelo> vuelos = new LinkedList<TransferVuelo>();
		
		try (Connection conn = DbConnection.getConnection()) {
			try (ResultSet rs = conn.createStatement().executeQuery(query)){
				while (rs.next()) {
					vuelos.add(getVueloFromRow(rs));
				}
			}	
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return (null);
		}
		return (vuelos);
	}

	@Override
	public boolean insertarVuelo(TransferVuelo vuelo) {
		String query = 
			    "INSERT INTO VUELO (vueloId, avionId, origen, destino, tiempo_aterrizaje, tiempo_salida, tipo_vuelo, vip) " +
			    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query)) {
		    ps.setString(1, vuelo.getVueloId());
		    ps.setString(2, vuelo.getAvionId());
		    ps.setString(3, vuelo.getOrigen());
		    ps.setString(4, vuelo.getDestino());
		    ps.setTimestamp(5, Timestamp.valueOf(vuelo.getTiempoAterrizaje()));
		    ps.setTimestamp(6, Timestamp.valueOf(vuelo.getTiempoSalida()));
		    ps.setString(7, vuelo.getTipoVuelo().toLowerCase());
		    ps.setBoolean(8, vuelo.isVip());
		    
		    ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return (false);
		}
		
		return true;
	}

	@Override
	public boolean actualizarVuelo(TransferVuelo vuelo) {
		String query =
			    "UPDATE VUELO SET "
			  + "avionId = ?, "
			  + "origen = ?, "
			  + "destino = ?, "
			  + "tiempo_aterrizaje = ?, "
			  + "tiempo_salida = ?, "
			  + "tipo_vuelo = ?, "
			  + "vip = ? "
			  + "WHERE vueloId = ?";

		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query)) {
		    ps.setString(1, vuelo.getAvionId());
		    ps.setString(2, vuelo.getOrigen());
		    ps.setString(3, vuelo.getDestino());
		    ps.setTimestamp(4, Timestamp.valueOf(vuelo.getTiempoAterrizaje()));
		    ps.setTimestamp(5, Timestamp.valueOf(vuelo.getTiempoSalida()));
		    ps.setString(6, vuelo.getTipoVuelo().toLowerCase());
		    ps.setBoolean(7, vuelo.isVip());
		    ps.setString(8, vuelo.getVueloId());

		    int actualizada = ps.executeUpdate();
		    if (actualizada == 0) {
		    	System.out.println("No hay un vuelo con ese ID");
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
	public boolean eliminarVuelo(String vueloId) {
		String query = "DELETE FROM VUELO WHERE vueloId = ?";

		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(query)) {
		    ps.setString(1, vueloId);
		    
		    int filasEliminadas = ps.executeUpdate();
		    if (filasEliminadas == 0) {
		    	System.out.println("No hay un vuelo con ese ID");
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
	public List<TransferVuelo> buscarVuelo(LocalDateTime tiempo, boolean antes) {
		String selectVuelosSql =
		    "SELECT vueloId, avionId, origen, destino, tiempo_salida, tiempo_aterrizaje, tipo_vuelo, vip " +
		    "FROM VUELO " +
		    "WHERE tiempo_salida " + (antes ? "<" : ">") + " ? " +
		    "ORDER BY tiempo_salida ASC";

		try (Connection conn = DbConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(selectVuelosSql)) {
		    // bind the cutoff LocalDateTime
			LocalTime hora = tiempo.toLocalTime();
		    ps.setTime(1, Time.valueOf(hora));

		    try (ResultSet rs = ps.executeQuery()) {
		        List<TransferVuelo> resultados = new ArrayList<>();
		        while (rs.next()) {
		            resultados.add(getVueloFromRow(rs));
		        }
		        return resultados;
		    }
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return (null);
		}
	}
}