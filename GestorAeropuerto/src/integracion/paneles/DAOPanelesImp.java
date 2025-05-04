package integracion.paneles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import integracion.DbConnection;

import java.util.ArrayList;

import negocio.operaciones.TransferAsignacion;
import negocio.paneles.TransferInfoVuelos;
import negocio.paneles.TransferPaneles;

public class DAOPanelesImp implements DAOPaneles {
	
	@Override
	public List<TransferInfoVuelos> buscaVuelos() {
		
		List<TransferInfoVuelos> listaInfoVuelos = new ArrayList<>();
		
		String query = "SELECT A.vueloid, A.puertaid, A.hora_llegada, A.hora_salida, P.terminal, V.avionid, V.origen, V.destino, Av.aerolinea  FROM asignacion A JOIN puerta P ON A.puertaid = P.puertaid JOIN vuelo V ON A.vueloid = V.vueloid"
				+ " JOIN avion Av ON Av.avionid = V.avionid;";
	
		try(Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query)) {
	
			try (ResultSet rs= stmt.executeQuery()){
				while (rs.next()) {
					
					TransferInfoVuelos transfer = new TransferInfoVuelos();
					transfer.setAerolinea(rs.getString("aerolinea"));
					transfer.setAvion(rs.getString("avionid"));
					transfer.setDestino(rs.getString("destino"));
					transfer.setHora_llegada(rs.getTimestamp("hora_llegada").toLocalDateTime().toLocalTime());
					transfer.setHora_salida(rs.getTimestamp("hora_salida").toLocalDateTime().toLocalTime());
					transfer.setOrigen(rs.getString("origen"));
					transfer.setPanel(1);
					transfer.setPuerta(rs.getInt("puertaid"));
					transfer.setTerminal(rs.getInt("terminal"));
					transfer.setVuelo(rs.getString("vueloid"));
					
					
					listaInfoVuelos.add(transfer);
				}
			}
	
		} catch(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage()); 
		return null;
		}
		return listaInfoVuelos;
	}
		
	public List<TransferPaneles> buscaPaneles() {
		
		List<TransferPaneles> paneles = new ArrayList<>();
		String query = "SELECT * FROM PANELES";
	
		try(Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query)) {
	
			try (ResultSet rs= stmt.executeQuery()){
				while (rs.next()) {
					
					TransferPaneles transfer = new TransferPaneles();
					
					transfer.setAviso(rs.getString("aviso"));
					transfer.setEncendido(rs.getInt("encendido"));
					transfer.setId(rs.getInt("id"));
					transfer.setN_columnas(rs.getInt("n_columnas"));
					transfer.setN_lineas(rs.getInt("n_lineas"));
					transfer.setTerminal(rs.getInt("terminal"));
					transfer.setTieneAviso(rs.getInt("tiene_aviso"));
					
					paneles.add(transfer);
				}
			}
	
		} catch(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage()); 
		return null;
	}

	return paneles;
	
}
	
	public void añadir_panel(TransferPaneles transfer) {
		String insert = "INSERT INTO Paneles (id, encendido, n_columnas, n_lineas, terminal, tiene_aviso, aviso) VALUES (?,?,?,?,?,?,?);";
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(insert)) {
			
				stmt.setInt(1, transfer.getId());
				stmt.setInt(2, transfer.getEncendido());
				stmt.setInt(3, transfer.getN_columnas());
				stmt.setInt(4, transfer.getN_lineas());
				stmt.setInt(5, transfer.getTerminal());
				stmt.setInt(6, transfer.getTieneAviso());
				stmt.setString(7,  transfer.getAviso());
				
				stmt.executeUpdate();
				
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ya existe un panel con ese ID");

		}
	}
	
	public void eliminar_panel(int id_panel) {
		String delete = "DELETE FROM Paneles WHERE id = ?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(delete)) {
			
				stmt.setInt(1, id_panel);
				stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void encender_apagar_panel(int id_panel, Boolean encender) {
		String update = "";
		if (encender) { update = "UPDATE Paneles SET encendido = 1 WHERE id = ?";}
		else {update = "UPDATE Paneles SET encendido = 0 WHERE id = ?";}
		 
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(update)) {
			
				stmt.setInt(1, id_panel);
				stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminar_aviso(int id_panel) {
		String update = "UPDATE Paneles SET aviso = ' ', tiene_aviso = 0 WHERE id = ?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(update)) {
			
				stmt.setInt(1, id_panel);
				stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void añadir_aviso(int id_panel, String aviso) {
		String update = "UPDATE Paneles SET aviso = ?, tiene_aviso = 1 WHERE id = ?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(update)) {
			
				stmt.setString(1, aviso);
				stmt.setInt(2, id_panel);
				stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}