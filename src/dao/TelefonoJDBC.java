package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Telefono;

public class TelefonoJDBC {
	
	// SELECT
	public static synchronized Telefono seleccionarTelefono(int idTelefono) {
		Telefono t = null;
		
		String query = "SELECT * FROM Telefono WHERE idTelefono = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idTelefono);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				t = crearTelefono(
					rs.getInt("idTelefono"), 
					rs.getString("numero"), 
					rs.getInt("idUsuario"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return t;
	}
	
	public static synchronized List<Telefono> seleccionarTelefonos(int idUsuario) {
		List<Telefono> lt = null;
		
		String query = "SELECT * FROM Telefono WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			lt = crearListaTelefonos(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lt;
	}
	
	// INSERT
	public static synchronized boolean insertarTelefono(Telefono t) {
		boolean telefonoInsertado = false;
		
		String query = 
			"INSERT INTO Telefono " +
				"(numero, idUsuario) " + 
			"VALUES " + 
				"(?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, t.getNumero());
			ps.setInt(2, t.getIdUsuario());
			
			int filasInsertadas = ps.executeUpdate();
			
			telefonoInsertado = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return telefonoInsertado;
	}
	
	// UPDATE
	public static synchronized boolean actualizarTelefono(Telefono t) {
		boolean telefonoModificado = false;
		
		String query = 
			"UPDATE Telefono SET " +
				"numero = ? " +
			"WHERE idTelefono = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, t.getNumero());
			ps.setInt(2, t.getIdTelefono());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return telefonoModificado;
	}
	
	public static synchronized boolean eliminarTelefono(int idTelefono) {
		boolean telefonoEliminado = false;
		
		String query = "DELETE FROM Telefono WHERE idTelefono = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idTelefono);
			
			int filasEliminadas = ps.executeUpdate();
			
			telefonoEliminado = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return telefonoEliminado;
	}
	
	// Métodos misceláneos
	public static synchronized Telefono crearTelefono(
		int idTelefono,
		String numero,
		int idUsuario
	) {
		Telefono t = new Telefono();
		
		t.setIdTelefono(idTelefono);
		t.setIdUsuario(idUsuario);
		t.setNumero(numero);
		
		return t;
	}
	
	public static synchronized List<Telefono> crearListaTelefonos(ResultSet rs) throws SQLException {
		List<Telefono> lt = new ArrayList<Telefono>();
		
		while (rs.next()) {
			Telefono t = crearTelefono(
				rs.getInt("idTelefono"), 
				rs.getString("numero"), 
				rs.getInt("idUsuario"));
			
			lt.add(t);
		}
		
		return lt;
	}
}
