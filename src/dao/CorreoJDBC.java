package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Correo;

public class CorreoJDBC {
	
	// SELECT
	public static synchronized Correo seleccionarCorreo(int idCorreo) {
		Correo c1 = null;
		
		String query = "SELECT * FROM Correo WHERE idCorreo = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idCorreo);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				c1 = crearCorreo(
					rs.getInt("idCorreo"), 
					rs.getString("direccionDeCorreo"), 
					rs.getInt("idUsuario"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return c1;
	}
	
	public static synchronized List<Correo> seleccionarCorreos(int idUsuario) {
		List<Correo> lc = null;
		
		String query = "SELECT * FROM Correo WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			lc = crearListaCorreos(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lc;
	}
	
	// INSERT
	public static synchronized boolean insertarCorreo(Correo c1) {
		boolean correoInsertado = false;
		
		String query = 
			"INSERT INTO Correo " +
				"(direccionDeCorreo, idUsuario) " +
			"VALUES " +
				"(?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, c1.getDireccionDeCorreo());
			ps.setInt(2, c1.getIdUsuario());
			
			int filasInsertadas = ps.executeUpdate();
			
			correoInsertado = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return correoInsertado;
	}
	
	// UPDATE
	public static synchronized boolean actualizarCorreo(Correo c1) {
		boolean correoActualizado = false;
		
		String query = 
			"UPDATE Correo SET " +
				"direccionDeCorreo = ? " +
			"WHERE idCorreo = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, c1.getDireccionDeCorreo());
			ps.setInt(2, c1.getIdCorreo());
			
			int filasActualizadas = ps.executeUpdate();
			
			correoActualizado = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return correoActualizado;
	}
	
	// DELETE
	public static synchronized boolean eliminarCorreo(int idCorreo) {
		boolean correoEliminado = false;
		
		String query = "DELETE FROM Correo WHERE idCorreo = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idCorreo);
			
			int filasEliminadas = ps.executeUpdate();
			
			correoEliminado = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return correoEliminado;
	}
	
	// Métodos misceláneos
	public static synchronized Correo crearCorreo(
		int idCorreo, 
		String direccionDeCorreo, 
		int idUsuario
	) {
		Correo c = new Correo();
		
		c.setIdCorreo(idCorreo);
		c.setIdUsuario(idUsuario);
		c.setDireccionDeCorreo(direccionDeCorreo);
		
		return c;
	}
	
	public static synchronized List<Correo> crearListaCorreos(ResultSet rs) throws SQLException {
		List<Correo> lc = new ArrayList<Correo>();
		
		while (rs.next()) {
			Correo c = crearCorreo(
				rs.getInt("idCorreo"), 
				rs.getString("direccionDeCorreo"), 
				rs.getInt("idUsuario"));
			
			lc.add(c);
		}
		
		return lc;
	}
}
