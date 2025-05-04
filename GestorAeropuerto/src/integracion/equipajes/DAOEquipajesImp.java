package integracion.equipajes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integracion.DbConnection;
import negocio.equipajes.TransferEquipaje;

public class DAOEquipajesImp implements DAOEquipajes {
	
	public DAOEquipajesImp() { }
	
	
	@Override
	public void anadirEquipaje(TransferEquipaje equ) {
		// TODO Auto-generated method stub
		String sentencia = "INSERT INTO equipajes (id_vuelo, peso) VALUES (?,?)";
		
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sentencia)){
			stmt.setString(1, equ.getIdVuelo());
			stmt.setDouble(2, equ.getPeso());
			int rs = stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public List<TransferEquipaje> obtenerPorIdVuelo(String id_vuelo) {
		// TODO Auto-generated method stub
		List<TransferEquipaje> lista=new ArrayList<>();

		String sentencia= "SELECT id, id_vuelo, peso FROM equipajes WHERE id_vuelo = ?";
		
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sentencia)){
			stmt.setString(1, id_vuelo);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("id");
				String vuelo = rs.getString("id_vuelo");
				double peso = rs.getDouble("peso");
				
				lista.add(new TransferEquipaje(id, id_vuelo, peso));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}


	@Override
	public void modificarEquipaje(TransferEquipaje equ) {
		// TODO Auto-generated method stub
		String sentencia = "UPDATE equipajes SET peso = ? WHERE id = ?";
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sentencia)){
			stmt.setDouble(1, equ.getPeso());
			stmt.setInt(2, equ.getId());
			int rs = stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void eliminarEquipaje(TransferEquipaje equ) {
		// TODO Auto-generated method stub
		String sentencia = "DELETE FROM equipajes WHERE id = ?";
		try (Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sentencia)){
			stmt.setInt(1, equ.getId());
			int rs = stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
