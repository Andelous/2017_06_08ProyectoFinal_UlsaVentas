package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FavoritoJDBC {
	// INSERT
	public static synchronized boolean insertarFavorito(int idUsuario, int idPublicacion) {
		boolean favoritoInsertado = false;
		
		String query = 
			"INSERT INTO Favorito " +
				"(idUsuario, idPublicacion) " +
			"VALUES " + 
				"(?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			ps.setInt(2, idPublicacion);
			
			int filasInsertadas = ps.executeUpdate();
			
			favoritoInsertado = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return favoritoInsertado;
	}
	
	// DELETE
	public static synchronized boolean eliminarFavorito(int idUsuario, int idPublicacion) {
		boolean favoritoEliminado = false;
		
		String query = 
			"DELETE FROM Favorito WHERE idUsuario = ? AND idPublicacion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			ps.setInt(2, idPublicacion);
			
			int filasEliminadas = ps.executeUpdate();
			
			favoritoEliminado = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return favoritoEliminado;
	}
}
