package integracion.personal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import integracion.DbConnection;
import negocio.personal.Codigos;
import negocio.personal.TransferEmpleado;

public class DAOEmpleadoImp implements DAOEmpleado {

	public TransferEmpleado listarPorDni(String dni) {	
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.listarEmpleadoPorDni); 
			ps.setString(dni);
			
			TransferEmpleado t = null;
			ResultSet rs = ps.execute();
			if (rs.next()) {
				t = new TransferEmpleado(
						rs.getInt("id"), 
						rs.getBoolean("activo"), 
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("rol"),
						rs.getString("turno"),
						rs.getString("funcion"),
						rs.getDouble("nomina")
				);		
			}
			
			rs.close();
			ps.close();
			con.close();
			
			return t;
			
		} catch (SQLException e) {
			return null;
		}
	}

	public int altaEmpleado(TransferEmpleado t) {
		int id;
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.altaEmpleado, true); 
			
			ps.setBoolean(t.isActivo())
				.setString(t.getDni())
				.setString(t.getNombre())
				.setString(t.getRol())
				.setString(t.getTurno())
				.setString(t.getFuncion())
				.setDouble(t.getNomina());
			
			ResultSet rs = ps.execute();
			
			if (rs.next()) {
				id = rs.getInt("id");
			}
			else {
				id = Codigos.ERROR_INTERNO;
			}
			
			ps.close();
			rs.close();
			con.close();
			
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return Codigos.ERROR_INTERNO;
		}
	}


	public boolean reactivarEmpleado(int id) {
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.reactivarEmpleado); 
			
			ps.execute();
			
			int filas = ps.getFilas();
			
			ps.close();
			con.close();
			
			return filas == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public TransferEmpleado listarPorID(int id) {
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.listarEmpleadoPorId); 
			
			ps.setInt(id);
			
			ResultSet rs = ps.execute();
			TransferEmpleado t = null;
			if (rs.next()) {
				t = new TransferEmpleado(
						rs.getInt("id"), 
						rs.getBoolean("activo"), 
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("rol"),
						rs.getString("turno"),
						rs.getString("funcion"),
						rs.getDouble("nomina")
				);		
			}
			rs.close();
			ps.close();
			con.close();
			
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean modificarEmpleado(TransferEmpleado t) {
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.modificarEmpleado); 
			ps.setBoolean(t.isActivo())
				.setString(t.getDni())
				.setString(t.getNombre())
				.setString(t.getRol())
				.setString(t.getTurno())
				.setString(t.getFuncion())
				.setDouble(t.getNomina())
				.setString(t.getDni());
			
			ps.execute();
			int filas = ps.getFilas();
					
			
			ps.close();
			con.close();
			
			return filas == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public Collection<TransferEmpleado> listarTodos() {
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.listarTodos); 
			ResultSet rs = ps.execute();
			List<TransferEmpleado> lista = new ArrayList<>();
			
			while (rs.next()) {
				lista.add(new TransferEmpleado(
						rs.getInt("id"), 
						rs.getBoolean("activo"), 
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("rol"),
						rs.getString("turno"),
						rs.getString("funcion"),
						rs.getDouble("nomina")
				));		
			}
			
			rs.close();
			ps.close();
			con.close();
			
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public boolean eliminarEmpleado(int id) {
		try {
			Connection con = DbConnection.getConnection();
			Adapter ps = new Adapter(con, Consultas.eliminarEmpleado); 
			ps.setInt(id);
			
			ps.execute();
			int filas = ps.getFilas();
			
			ps.close();
			con.close();
			
			return filas == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
