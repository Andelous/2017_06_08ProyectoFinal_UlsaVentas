package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class Conexion {
	
	private static String url = "jdbc:mysql://localhost:3306/UlsaVentas";
	private static String usuario = "root";
	private static String contrasena = "Mexico.2017";
	
	public static synchronized Connection getConexion() {
		Connection c = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(url, usuario, contrasena);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static synchronized void cerrarConexion(Connection c) {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void cerrarPreparedStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void cerrarResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void deshacerCambios(Connection c) {
		try {
			c.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
