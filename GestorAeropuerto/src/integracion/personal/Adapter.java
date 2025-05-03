package integracion.personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Adapter { // Objeto adapter para la clase PreparedStatement
	private PreparedStatement ps;
	private String tipoConsulta;
	private boolean returnGeneratedKeys;
	private int filas;
	private int index = 1;
	
	public Adapter(Connection con, String query) throws SQLException {
		this.tipoConsulta = query.split("\\s+")[0]; // Primera palabra de la consulta
		this.returnGeneratedKeys = false;
		ps = con.prepareStatement(query);
		this.filas = 0;
	}
	
	public Adapter(Connection con, String query, boolean returnGeneratedKeys) throws SQLException {
		this.tipoConsulta = query.split("\\s+")[0]; // Primera palabra de la consulta
		this.returnGeneratedKeys = returnGeneratedKeys;
		if (returnGeneratedKeys) {
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);			
		} else ps = con.prepareStatement(query);	
		
		this.filas = 0;
	}
	
	public Adapter setInt(int val) throws SQLException {
		this.ps.setInt(this.index++, val);
		return this;
	}
	
	public Adapter setDouble(double val) throws SQLException {
		this.ps.setDouble(this.index++, val);
		return this;
	}
	
	public Adapter setString(String val) throws SQLException{
		this.ps.setString(this.index++, val);
		return this;
	}
	
	public Adapter setBoolean(boolean val) throws SQLException {
		this.ps.setBoolean(this.index++, val);
		return this;
	}
	
	public ResultSet execute() throws SQLException {
		if (this.tipoConsulta.equalsIgnoreCase("INSERT") || 
				this.tipoConsulta.equalsIgnoreCase("UPDATE")) {
			this.filas = this.ps.executeUpdate();
			if (this.filas > 0 && this.returnGeneratedKeys) return this.ps.getGeneratedKeys();
			else return null;
		}
		
		return this.ps.executeQuery();
	}
	
	public int getFilas() {
		return filas;
	}
	
	public void close() throws SQLException {
		this.ps.close();
	}
}
