package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

public class UsuarioJDBC {
	
	// SELECT
	public static synchronized Usuario seleccionarUsuario(String usuario, String contrasena) {
		String query = "SELECT * FROM Usuario WHERE usuario = ? AND contrasena = ?";
		
		Usuario u = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, usuario);
			ps.setString(2, contrasena);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				u = crearUsuario(
					rs.getInt("idUsuario"),
					rs.getString("usuario"),
					rs.getString("contrasena"), 
					rs.getString("nombres"),
					rs.getString("apellidoPaterno"),
					rs.getString("apellidoMaterno"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return u;
	}
	
	public static synchronized Usuario seleccionarUsuario(int idUsuario) {
		String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
		
		Usuario u = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				u = crearUsuario(
					rs.getInt("idUsuario"),
					rs.getString("usuario"),
					rs.getString("contrasena"), 
					rs.getString("nombres"),
					rs.getString("apellidoPaterno"),
					rs.getString("apellidoMaterno"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return u;
	}
	
	
	// INSERT
	public static synchronized boolean insertarUsuario(Usuario u) {
		boolean usuarioInsertado = false;
		
		String query = 
			"INSERT INTO Usuario " + 
				"(usuario, contrasena, nombres, apellidoPaterno, apellidoMaterno) " +
			"VALUES " +
				"(?, ?, ?, ?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getContrasena());
			ps.setString(3, u.getNombres());
			ps.setString(4, u.getApellidoPaterno());
			ps.setString(5, u.getApellidoMaterno());
			
			int filasInsertadas = ps.executeUpdate();
			
			usuarioInsertado = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return usuarioInsertado;
	}
	
	// UPDATE
	public static synchronized boolean actualizarUsuario(Usuario u) {
		boolean usuarioActualizado = false;
		
		String query = 
			"UPDATE Usuario SET " + 
				"usuario = ?, " +
				"contrasena = ?, " +
				"nombres = ?, " +
				"apellidoPaterno = ?, " +
				"apellidoMaterno = ? " +
			"WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getContrasena());
			ps.setString(3, u.getNombres());
			ps.setString(4, u.getApellidoPaterno());
			ps.setString(5, u.getApellidoMaterno());
			ps.setInt(6, u.getIdUsuario());
			
			int filasActualizadas = ps.executeUpdate();
			
			usuarioActualizado = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return usuarioActualizado;
	}
	
	public static synchronized boolean eliminarUsuario(int idUsuario) {
		boolean usuarioEliminado = false;
		
		String query = "DELETE FROM Usuario WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			int filasEliminadas = ps.executeUpdate();
			
			usuarioEliminado = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return usuarioEliminado;
	}
	
	// Métodos misceláneos
	public static synchronized Usuario crearUsuario(
		int idUsuario,
		String usuario,
		String contrasena,
		String nombres,
		String apellidoPaterno,
		String apellidoMaterno
	) {
		Usuario u = new Usuario();
		
		u.setIdUsuario(idUsuario);
		u.setUsuario(usuario);
		u.setContrasena(contrasena);
		u.setNombres(nombres);
		u.setApellidoMaterno(apellidoMaterno);
		u.setApellidoPaterno(apellidoPaterno);
		
		return u;
	}
}
