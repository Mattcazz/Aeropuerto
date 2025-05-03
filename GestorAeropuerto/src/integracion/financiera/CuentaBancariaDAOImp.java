package integracion.financiera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.DbConnection;
import negocio.financiera.CuentaBancaria;

public class CuentaBancariaDAOImp implements CuentaBancariaDAO {

	private Connection conexion;

	public CuentaBancariaDAOImp() {
		try {
			this.conexion = DbConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void guardar(CuentaBancaria cuenta) {
		String sql = "INSERT INTO cuenta_bancaria (iban, nombre, saldo, banco) VALUES (?, ?, ?, ?)";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, cuenta.getIban());
			stmt.setString(2, cuenta.getNombre());
			stmt.setFloat(3, cuenta.getSaldo());
			stmt.setString(4, cuenta.getBanco());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public CuentaBancaria buscarPorNombre(String nombre) {
		String sql = "SELECT * FROM cuenta_bancaria WHERE nombre = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new CuentaBancaria(rs.getString("iban"), rs.getString("nombre"), rs.getFloat("saldo"),
						rs.getString("banco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CuentaBancaria> obtenerTodas() {
		List<CuentaBancaria> cuentas = new ArrayList<>();
		String sql = "SELECT * FROM cuenta_bancaria";
		try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				cuentas.add(new CuentaBancaria(rs.getString("iban"), rs.getString("nombre"), rs.getFloat("saldo"),
						rs.getString("banco")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuentas;
	}
	
	@Override
	public void actualizarSaldo(String nombreCuenta, float nuevoSaldo) {
	    String sql = "UPDATE cuenta_bancaria SET saldo = ? WHERE nombre = ?";
	    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        stmt.setFloat(1, nuevoSaldo);
	        stmt.setString(2, nombreCuenta);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
