package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Comentario;

public class ComentarioJDBC {
	
	// SELECT
	public static synchronized Comentario selccionarComentario(int idComentario) {
		Comentario c1 = null;
		
		String query = "SELECT * FROM Comentario WHERE idComentario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idComentario);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				c1 = crearComentario(
					rs.getInt("idComentario"), 
					rs.getString("comentario"), 
					rs.getDate("fecha"), 
					rs.getInt("idUsuario"), 
					rs.getInt("idPublicacion"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return c1;
	}
	
	public static synchronized List<Comentario> seleccionarComentariosPorPublicacion(int idPublicacion) {
		List<Comentario> lc = null;
		
		String query = "SELECT * FROM Comentario WHERE idPublicacion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idPublicacion);
			
			rs = ps.executeQuery();
			
			lc = crearListaComentarios(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lc;
	}
	
	public static synchronized List<Comentario> seleccionarComentariosPorUsuario(int idUsuario) {
		List<Comentario> lc = null;
		
		String query = "SELECT * FROM Comentario WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			lc = crearListaComentarios(rs);
			
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
	public static synchronized boolean insertarComentario(Comentario c1) {
		boolean comentarioInsertado = false;
		
		String query = 
			"INSERT INTO Comentario " +
				"(comentario, fecha, idUsuario, idPublicacion) " +
			"VALUES " + 
				"(?, ?, ?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, c1.getComentario());
			ps.setDate(2, c1.getFecha());
			ps.setInt(3, c1.getIdUsuario());
			ps.setInt(4, c1.getIdPublicacion());
			
			int filasInsertadas = ps.executeUpdate();
			
			comentarioInsertado = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return comentarioInsertado;
	}
	
	// UPDATE
	public static synchronized boolean actualizarComentario(Comentario c1) {
		boolean comentarioActualizado = false;
		
		String query = 
			"UPDATE Comentario SET " +
				"comentario = ?, " +
				"fecha = ? " +
				"WHERE idComentario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, c1.getComentario());
			ps.setDate(2, c1.getFecha());
			ps.setInt(3, c1.getIdComentario());
			
			int filasActualizadas = ps.executeUpdate();
			
			comentarioActualizado = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return comentarioActualizado;
	}
	
	// DELETE
	public static synchronized boolean eliminarComentario(int idComentario) {
		boolean comentarioEliminado = false;
		
		String query = "DELETE FROM Comentario WHERE idComentario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idComentario);
			
			int filasEliminadas = ps.executeUpdate();
			
			comentarioEliminado = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return comentarioEliminado;
	}
	
	// Métodos misceláneos
	public static synchronized Comentario crearComentario(
		int idComentario,
		String comentario,
		Date fecha,
		int idUsuario,
		int idPublicacion
	) {
		Comentario c = new Comentario();
		
		c.setComentario(comentario);
		c.setFecha(fecha);
		c.setIdComentario(idComentario);
		c.setIdPublicacion(idPublicacion);
		c.setIdUsuario(idUsuario);
		
		return c;
	}
	
	public static synchronized List<Comentario> crearListaComentarios(ResultSet rs) throws SQLException {
		List<Comentario> lc = new ArrayList<Comentario>();
		
		while (rs.next()) {
			Comentario c = crearComentario(
				rs.getInt("idComentario"), 
				rs.getString("comentario"), 
				rs.getDate("fecha"), 
				rs.getInt("idUsuario"), 
				rs.getInt("idPublicacion"));
			
			lc.add(c);
		}
		
		return lc;
	}
}
