package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static Connection conn;
	
	private static final String URL = "jdbc:postgresql://localhost:5432/aeropuerto";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nubarron569";

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }

}
