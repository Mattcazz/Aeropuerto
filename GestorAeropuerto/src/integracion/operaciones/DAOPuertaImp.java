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
import negocio.vuelos.TransferAvion;
import negocio.operaciones.TransferBloqueo;
import negocio.operaciones.TransferPuerta;
import negocio.vuelos.TransferVuelo;

public class DAOPuertaImp implements DAOPuerta{

	@Override
	public TransferPuerta getPuerta(int puerta_id) {
		// TODO Auto-generated method stub
		TransferPuerta puerta = new TransferPuerta();
		String query = "SELECT * FROM PUERTA WHERE puertaId = ?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement  stmt = conn.prepareStatement(query)){
			
			stmt.setInt(1, puerta_id);
			
			try(ResultSet rs= stmt.executeQuery()){
				if(rs.next()) puerta = getPuertaFromRow(rs);	
			}
			
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		return puerta;
		
	}

	@Override
	public List<TransferPuerta> getPuertas() {
		// TODO Auto-generated method stub
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();
		
		try(Connection conn = DbConnection.getConnection()){
			
			String query = "SELECT * FROM PUERTA "
						 + "ORDER BY terminal ASC, puertaId ASC";
			
			try (ResultSet rs= conn.createStatement().executeQuery(query)){
				while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}	
			}

		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		return puertas;
	}

	@Override
	public List<TransferPuerta> getPuertasInTerminal(int terminal) {
		// TODO Auto-generated method stub
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();

		String query = "SELECT * FROM PUERTA WHERE terminal = ?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setInt(1, terminal);
			
			try (ResultSet rs= stmt.executeQuery()){
				while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}
			}
			

		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		return puertas;
	}

	
	public List<TransferPuerta> getPuertasDisponibles() {
		// TODO Auto-generated method stub
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();

		String query = "SELECT * FROM PUERTA WHERE estado = 'disponible'";
		
		
		try(Connection conn = DbConnection.getConnection()){
			
			try (ResultSet rs= conn.createStatement().executeQuery(query)){
				while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}	
			}
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
		
		return puertas;
	}

	private TransferPuerta getPuertaFromRow(ResultSet rs) throws SQLException {
		
		TransferPuerta puerta = new TransferPuerta();
		
		try {
				puerta.setPuertaID(rs.getInt("puertaId"));
				puerta.setAlturaMax(rs.getFloat("altura_max"));
				puerta.setPesoMax(rs.getFloat("peso_max"));
				puerta.setAnchuraMax(rs.getFloat("anchura_max"));
				puerta.setLongitudMax(rs.getFloat("longitud_max"));
				puerta.setVip(rs.getBoolean("vip"));
				puerta.setTerminal(rs.getInt("terminal"));
				puerta.setUbicacion(rs.getString("ubicacion"));
				puerta.setNivel(rs.getInt("nivel"));
				puerta.setTipoPuerta(rs.getString("tipo_puerta"));
				puerta.setEstado(rs.getString("estado"));
				puerta.setAerolineaPreferida(rs.getString("aerolinea_preferida"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return puerta;
	}
	
	private TransferBloqueo getBloqueoFromRow (ResultSet rs) throws SQLException{
		TransferBloqueo bloqueo = new TransferBloqueo();
		
		try {
			bloqueo.setPuertaId(rs.getInt("puertaid"));
			bloqueo.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
			bloqueo.setHoraFinal(rs.getTimestamp("hora_final").toLocalDateTime());
			bloqueo.setMotivo(rs.getString("motivo"));
			bloqueo.setComentario(rs.getString("mensaje"));
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return bloqueo;
	}

	@Override
	public TransferPuerta getPuertaMasCercana() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransferPuerta> getPuertasDisponiblesEnHoraDeLlegada(TransferAvion avion, LocalDateTime hora_llegada) {
		
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();
		// TODO Auto-generated method stub
		
		String query = "SELECT * FROM PUERTA p WHERE p.peso_max >= ? AND p.longitud_max >= ? AND p.anchura_max >= ? AND p.altura_max >= ?"
				+ " AND NOT EXISTS (SELECT 1 FROM ASIGNACION a WHERE a.puertaId = p.puertaId AND a.hora_llegada = ?) "
				+ " AND NOT EXISTS (SELECT 1 FROM BLOQUEO b WHERE b.puertaid = p.puertaid AND b.hora_inicio = ?)";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
				
			stmt.setDouble(1, avion.getPeso());
			stmt.setDouble(2, avion.getLongitud());
			stmt.setDouble(3, avion.getAnchura());
			stmt.setDouble(4, avion.getAltura());
			stmt.setTimestamp(5, Timestamp.valueOf(hora_llegada));
			stmt.setTimestamp(6, Timestamp.valueOf(hora_llegada));
				
		    try (ResultSet rs= stmt.executeQuery()){
			
		    	while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}
			}
				

			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage()); 
				return null;
			}
			
			return puertas;

	}

	@Override
	public List<TransferPuerta> getPuertasDisponiblesEnHoraDeLlegadaAerolineaPriorizada(TransferAvion avion, LocalDateTime hora_llegada) {
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();
		// TODO Auto-generated method stub
		
		String query = "SELECT * FROM PUERTA p WHERE p.peso_max >= ? AND p.longitud_max >= ? AND p.anchura_max >= ? AND p.altura_max >= ?"
				+ " AND aerolinea_preferida = ?"
				+ " AND NOT EXISTS (SELECT 1 FROM ASIGNACION a WHERE a.puertaId = p.puertaId AND a.hora_llegada = ?)"
				+ " AND NOT EXISTS (SELECT 1 FROM BLOQUEO b WHERE b.puertaid = p.puertaid AND b.hora_inicio = ?)";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
				
			stmt.setDouble(1, avion.getPeso());
			stmt.setDouble(2, avion.getLongitud());
			stmt.setDouble(3, avion.getAnchura());
			stmt.setDouble(4, avion.getAltura());
			stmt.setString(5, avion.getAerolinea());
			stmt.setTimestamp(6, Timestamp.valueOf(hora_llegada));
			stmt.setTimestamp(7, Timestamp.valueOf(hora_llegada));
				
		    try (ResultSet rs= stmt.executeQuery()){
			
		    	while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}
			}
				

			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage()); 
				return null;
			}
			
			return puertas;
	}

	@Override
	public List<TransferPuerta> getPuertasDisponiblesEnHoraDeLlegadaMenosUsadas(TransferAvion avion,
			LocalDateTime hora_llegada) {
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();
		// TODO Auto-generated method stub
		
		String query = "SELECT p.*, COALESCE(ac.asignaciones_count, 0) AS asignaciones_count "
				+ "FROM PUERTA p LEFT JOIN (SELECT puertaId, COUNT(*) AS asignaciones_count "
				+ "FROM ASIGNACION GROUP BY puertaId) ac ON p.puertaId = ac.puertaId WHERE p.peso_max >= ? AND p.longitud_max >= ? AND p.anchura_max >= ? AND p.altura_max >= ? "
				+ "AND NOT EXISTS (SELECT 1 FROM ASIGNACION a2 WHERE a2.puertaId = p.puertaId AND a2.hora_llegada = ?) "
				+ "AND NOT EXISTS (SELECT 1 FROM BLOQUEO b WHERE b.puertaid = p.puertaid AND b.hora_inicio = ?) "
				+ "ORDER BY asignaciones_count ASC";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
				
			stmt.setDouble(1, avion.getPeso());
			stmt.setDouble(2, avion.getLongitud());
			stmt.setDouble(3, avion.getAnchura());
			stmt.setDouble(4, avion.getAltura());
			stmt.setTimestamp(5, Timestamp.valueOf(hora_llegada));
			stmt.setTimestamp(6, Timestamp.valueOf(hora_llegada));
				
		    try (ResultSet rs= stmt.executeQuery()){
			
		    	while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}
			}
				

			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage()); 
				return null;
			}
			
			return puertas;
	}

	@Override
	public boolean modificarPuerta(TransferPuerta puertaModificada) {
		// TODO Auto-generated method stub
		
		String query = "UPDATE puerta "
					 + "SET peso_max = ?, anchura_max = ?, altura_max = ?, longitud_max = ?, "
					 + "tipo_puerta = ?, aerolinea_preferida = ?, vip = ? "
					 + "WHERE puertaId = ?";
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {  
			
			stmt.setDouble(1, puertaModificada.getPesoMax());
			stmt.setDouble(2, puertaModificada.getAnchuraMax());
			stmt.setDouble(3, puertaModificada.getAlturaMax());
			stmt.setDouble(4, puertaModificada.getLongitudMax());
			stmt.setString(5, puertaModificada.getTipoPuerta());
			stmt.setString(6, puertaModificada.getAerolineaPreferida());
			stmt.setBoolean(7, puertaModificada.isVip());
			stmt.setInt(8, puertaModificada.getPuertaID());			
			
			stmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
		
		return true;
	}

	@Override
	public boolean crearPuerta(TransferPuerta puerta) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO puerta (terminal,ubicacion,nivel,peso_max,anchura_max,altura_max,longitud_max,tipo_puerta,aerolinea_preferida,vip) "
					+  "VALUES(?,?,?,?,?,?,?,?,?,?)";
	
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {  
			
			
			stmt.setInt(1, puerta.getTerminal());
			stmt.setString(2, puerta.getUbicacion());
			stmt.setInt(3, puerta.getNivel());
			stmt.setDouble(4, puerta.getPesoMax());
			stmt.setDouble(5, puerta.getAnchuraMax());
			stmt.setDouble(6, puerta.getAlturaMax());
			stmt.setDouble(7, puerta.getLongitudMax());
			stmt.setString(8, puerta.getTipoPuerta());
			stmt.setString(9, puerta.getAerolineaPreferida());
			stmt.setBoolean(10, puerta.isVip());
			
			stmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
		
		return true;
	
	
	}

	@Override
	public boolean crearBloqueoEnPuerta(TransferBloqueo bloqueo) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO bloqueo VALUES (?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {  
			
			stmt.setInt(1, bloqueo.getPuertaId());
			stmt.setTimestamp(2, Timestamp.valueOf(bloqueo.getHoraInicio()));
			stmt.setTimestamp(3, Timestamp.valueOf(bloqueo.getHoraFinal()));
			stmt.setString(4, bloqueo.getMotivo());
			stmt.setString(5, bloqueo.getComentario());
			
			stmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
		
		return true;
	}

	@Override
	public boolean borrarBloqueoEnPuerta(TransferBloqueo bloqueo) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM bloqueo WHERE puertaid = ? AND hora_inicio = ? AND hora_final = ?";
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
				stmt.setInt(1, bloqueo.getPuertaId());
				stmt.setTimestamp(2, Timestamp.valueOf(bloqueo.getHoraInicio()));
				stmt.setTimestamp(3, Timestamp.valueOf(bloqueo.getHoraFinal()));
				stmt.executeUpdate();
				return true;
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
	}

	@Override
	public boolean borrarPuerta(int puerta_id) {
		String query = "DELETE FROM puerta WHERE puertaid = ?";
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
				stmt.setInt(1,puerta_id);
				stmt.executeUpdate();
				return true;
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return false;
		}
	}

	@Override
	public List<TransferBloqueo> getBloqueosEnPuerta(int puerta_id) {
        
		List<TransferBloqueo> bloqueos = new LinkedList<TransferBloqueo>();
		
		String query = "SELECT * FROM bloqueo WHERE puertaid = ? "
				 + "ORDER BY hora_inicio ASC";
		
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
				
				stmt.setInt(1, puerta_id);
				
				try (ResultSet rs= stmt.executeQuery()){
					while (rs.next()) {
						bloqueos.add(getBloqueoFromRow(rs));
					}
				}
				

			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage()); 
				return null;
			}
		
		return bloqueos;
	}

	@Override
	public boolean borrarBloqueosEnPuerta(int puerta_id) {
		String query = "DELETE FROM bloqueo WHERE puertaid = ?";
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

	@Override
	public List<TransferPuerta> getPuertasDisponiblesEnHoraDeLlegadaMayorPeso(TransferAvion avion,
			LocalDateTime hora_llegada) {
		List<TransferPuerta> puertas = new LinkedList<TransferPuerta>();
		// TODO Auto-generated method stub
		
		String query = "SELECT * FROM PUERTA p WHERE p.peso_max >= ? AND p.longitud_max >= ? AND p.anchura_max >= ? AND p.altura_max >= ?"
				+ " AND NOT EXISTS (SELECT 1 FROM ASIGNACION a WHERE a.puertaId = p.puertaId AND a.hora_llegada = ?)"
				+ " AND NOT EXISTS (SELECT 1 FROM BLOQUEO b WHERE b.puertaid = p.puertaid AND b.hora_inicio = ?)"
				+ " ORDER BY p.peso_max DESC";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
				
			stmt.setDouble(1, avion.getPeso());
			stmt.setDouble(2, avion.getLongitud());
			stmt.setDouble(3, avion.getAnchura());
			stmt.setDouble(4, avion.getAltura());
			stmt.setTimestamp(5, Timestamp.valueOf(hora_llegada));
			stmt.setTimestamp(6, Timestamp.valueOf(hora_llegada));
				
		    try (ResultSet rs= stmt.executeQuery()){
			
		    	while (rs.next()) {
					puertas.add(getPuertaFromRow(rs));
				}
			}
				

			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage()); 
				return null;
			}
			
			return puertas;
	}

	@Override
	public TransferBloqueo getBloqueoDePuertaEnHora(int puerta_id, LocalDateTime hora_inicio, LocalDateTime hora_fin) {
		// TODO Auto-generated method stub
		TransferBloqueo tb = null;
		
		String query = "SELECT * FROM bloqueo WHERE puertaid = ? AND (hora_inicio = ? OR hora_final = ?)";
		
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement  stmt = conn.prepareStatement(query)){
				stmt.setInt(1, puerta_id);
				stmt.setTimestamp(2, Timestamp.valueOf(hora_inicio));
				stmt.setTimestamp(3, Timestamp.valueOf(hora_fin));
				try (ResultSet rs= stmt.executeQuery()){
					if (rs.next())tb = getBloqueoFromRow(rs);					
				}
				return tb;
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			return null;
		}
	}


}
