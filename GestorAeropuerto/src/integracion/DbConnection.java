package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {

	private static Connection conn;
	
	private static final String URL = "jdbc:postgresql://localhost:5432/Aeropuerto";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Carmona-01";
	
//	private static final String URL = "jdbc:postgresql://localhost:5432/GestorAeropuerto";
//	private static final String USER = "nucieda";
//	private static final String PASSWORD = "pipi2003f";
	
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }

}