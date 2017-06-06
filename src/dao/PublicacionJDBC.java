package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Publicacion;

public class PublicacionJDBC {
	
	// SELECT
	public static synchronized Publicacion seleccionarPublicacion(int idPublicacion) {
		String query = "SELECT * FROM Publicacion WHERE idPublicacion = ?";
		
		Publicacion p = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idPublicacion);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				p = crearPublicacion(
					rs.getInt("idPublicacion"),
					rs.getString("titulo"),
					rs.getString("descripcion"),
					rs.getDouble("precio"),
					rs.getInt("cantidad"),
					rs.getInt("vendidos"),
					rs.getString("direccionImagen"),
					rs.getInt("idUsuario"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return p;
	}
	
	public static synchronized List<Publicacion> seleccionarPublicaciones(int idUsuario) {
		String query = "SELECT * FROM Publicacion WHERE idUsuario = ?";
		
		List<Publicacion> lp = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			lp = crearListaPublicaciones(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lp;
	}
	
	public static synchronized List<Publicacion> seleccionarPublicaciones(String coincidencia) {
		String query = "SELECT * FROM Publicacion WHERE titulo LIKE '%?%'";
		
		List<Publicacion> lp = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, coincidencia);
			
			rs = ps.executeQuery();
			
			lp = crearListaPublicaciones(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lp;
	}
	
	public static synchronized List<Publicacion> seleccionarPublicacionesFavoritas(int idUsuario) {
		String query = "SELECT P.* FROM Publicacion P, Favorito F WHERE F.idUsuario = ? AND F.idPublicacion = P.idPublicacion";
		
		List<Publicacion> lp = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			lp = crearListaPublicaciones(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lp;
	}
	
	// INSERT
	public static synchronized boolean insertarPublicacion(Publicacion p) {
		boolean publicacionInsertada = false;
		
		String query = 
			"INSERT INTO Publicacion " +
				"(titulo, descripcion, precio, cantidad, vendidos, direccionImagen, idUsuario) " +
			"VALUES " +
				"(?, ?, ?, ?, ?, ?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getCantidad());
			ps.setInt(5, p.getVendidos());
			ps.setString(6, p.getDireccionImagen());
			ps.setInt(7, p.getIdUsuario());
			
			int filasInsertadas = ps.executeUpdate();
			
			publicacionInsertada = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return publicacionInsertada;
	}
	
	// UPDATE
	public static synchronized boolean actualizarPublicacion(Publicacion p) {
		boolean publicacionActualizada = false;
		
		String query = 
			"UPDATE Publicacion SET " +
				"titulo = ?, " +
				"descripcion = ?, " +
				"precio = ?, " +
				"cantidad = ?, " + 
				"vendidos = ?, " +
				"direccionImagen = ? " +
			"WHERE idPublicacion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getCantidad());
			ps.setInt(5, p.getVendidos());
			ps.setString(6, p.getDireccionImagen());
			ps.setInt(7, p.getIdPublicacion());
			
			int filasActualizadas = ps.executeUpdate();
			
			publicacionActualizada = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return publicacionActualizada;
	}
	
	// DELETE
	public static synchronized boolean eliminarPublicacion(int idPublicacion) {
		boolean publicacionEliminada = false;
		
		String query = "DELETE FROM Publicacion WHERE idPublicacion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idPublicacion);
			
			int filasEliminadas = ps.executeUpdate();
			
			publicacionEliminada = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return publicacionEliminada;
	}
	
	// Métodos misceláneos
	public static synchronized Publicacion crearPublicacion(
		int idPublicacion,
		String titulo,
		String descripcion,
		double precio,
		int cantidad,
		int vendidos,
		String direccionImagen,
		int idUsuario
	) {
		Publicacion p = new Publicacion();
		
		p.setIdPublicacion(idPublicacion);
		p.setTitulo(titulo);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setCantidad(cantidad);
		p.setVendidos(vendidos);
		p.setDireccionImagen(direccionImagen);
		p.setIdUsuario(idUsuario);
		
		return p;
	}
	
	public static synchronized List<Publicacion> crearListaPublicaciones(ResultSet rs) throws SQLException {
		List<Publicacion> lp = new ArrayList<Publicacion>();
		
		while (rs.next()) {
			Publicacion p = crearPublicacion(
				rs.getInt("idPublicacion"),
				rs.getString("titulo"),
				rs.getString("descripcion"),
				rs.getDouble("precio"),
				rs.getInt("cantidad"),
				rs.getInt("vendidos"),
				rs.getString("direccionImagen"),
				rs.getInt("idUsuario"));
			
			lp.add(p);
		}
		
		return lp;
	}
}
