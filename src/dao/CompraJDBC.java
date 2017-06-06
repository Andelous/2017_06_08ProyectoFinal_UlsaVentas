package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modelo.Compra;

public class CompraJDBC {
	
	// SELECT
	public static synchronized Compra seleccionarCompra(int idCompra) {
		Compra c1 = null;
		
		String query = "SELECT * FROM Compra WHERE idCompra = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idCompra);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				c1 = crearCompra(
					rs.getInt("idCompra"), 
					rs.getInt("cantidad"), 
					rs.getDate("fecha"), 
					rs.getString("comentarioDeCompra"), 
					rs.getString("comentarioDeVenta"), 
					rs.getInt("calificacionDeCompra"), 
					rs.getInt("calificacionDeVenta"), 
					rs.getInt("idUsuario"), 
					rs.getInt("idPublicacion"), 
					rs.getInt("idDireccion"), 
					rs.getInt("idTarjeta"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return c1;
	}
	
	public static synchronized List<Compra> seleccionarComprasPorUsuario(int idUsuario) {
		List<Compra> lc = null;
		
		String query = "SELECT * FROM Compra WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			lc = crearListaCompras(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return lc;
	}
	
	public static synchronized List<Compra> seleccionarComprasPorPublicacion(int idPublicacion) {
		List<Compra> lc = null;
		
		String query = "SELECT * FROM Compra WHERE idPublicacion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idPublicacion);
			
			rs = ps.executeQuery();
			
			lc = crearListaCompras(rs);
			
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
	public static synchronized boolean insertarCompra(Compra c1) {
		boolean compraInsertada = false;
		
		String query = 
			"INSERT INTO Compra " +
				"(cantidad, fecha, comentarioDeCompra, comentarioDeVenta, calificacionDeCompra, calificacionDeVenta, idUsuario, idPublicacion, idDireccion, idTarjeta) " +
			"VALUES " + 
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, c1.getCantidad());
			ps.setDate(2, c1.getFecha());
			ps.setString(3, c1.getComentarioDeCompra());
			ps.setString(4, c1.getComentarioDeVenta());
			ps.setInt(5, c1.getCalificacionDeCompra());
			ps.setInt(6, c1.getCalificacionDeVenta());
			ps.setInt(7, c1.getIdUsuario());
			ps.setInt(8, c1.getIdPublicacion());
			ps.setInt(9, c1.getIdDireccion());
			ps.setInt(10, c1.getIdTarjeta());
			
			int filasInsertadas = ps.executeUpdate();
			
			compraInsertada = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return compraInsertada;
	}
	
	// UPDATE
	public static synchronized boolean actualizarCompra(Compra c1) {
		boolean compraActualizada = false;
		
		String query = 
			"UPDATE Compra SET " +
				"cantidad = ?, " +
				"fecha = ?, " +
				"comentarioDeCompra = ?, " +
				"comentarioDeVenta = ?, " +
				"calificacionDeCompra = ?, " +
				"calificacionDeVenta = ?, " +
				"idUsuario = ?, " +
				"idPublicacion = ?, " +
				"idDireccion = ?, " +
				"idTarjeta = ? " +
			"WHERE idCompra = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, c1.getCantidad());
			ps.setDate(2, c1.getFecha());
			ps.setString(3, c1.getComentarioDeCompra());
			ps.setString(4, c1.getComentarioDeVenta());
			ps.setInt(5, c1.getCalificacionDeCompra());
			ps.setInt(6, c1.getCalificacionDeVenta());
			ps.setInt(7, c1.getIdUsuario());
			ps.setInt(8, c1.getIdPublicacion());
			ps.setInt(9, c1.getIdDireccion());
			ps.setInt(10, c1.getIdTarjeta());
			ps.setInt(11, c1.getIdCompra());
			
			int filasActualizadas = ps.executeUpdate();
			
			compraActualizada = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return compraActualizada;
	}
	
	// DELETE
	public static synchronized boolean eliminarCompra(Compra c1) {
		boolean compraEliminada = false;
		
		String query = "DELETE FROM Compra WHERE idCompra = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, c1.getIdCompra());
			
			int filasEliminadas = ps.executeUpdate();
			
			compraEliminada = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return compraEliminada;
	}
	
	// Métodos misceláneos
	public static synchronized Compra crearCompra(
		int idCompra,
		int cantidad,
		Date fecha,
		String comentarioDeCompra,
		String comentarioDeVenta,
		int calificacionDeCompra,
		int calificacionDeVenta,
		int idUsuario,
		int idPublicacion,
		int idDireccion,
		int idTarjeta
	) {
		Compra c = new Compra();
		
		c.setIdCompra(idCompra);
		c.setCantidad(cantidad);
		c.setFecha(fecha);
		c.setComentarioDeCompra(comentarioDeCompra);
		c.setComentarioDeVenta(comentarioDeVenta);
		c.setCalificacionDeCompra(calificacionDeCompra);
		c.setCalificacionDeVenta(calificacionDeVenta);
		c.setIdUsuario(idUsuario);
		c.setIdPublicacion(idPublicacion);
		c.setIdDireccion(idDireccion);
		c.setIdTarjeta(idTarjeta);
		
		return c;
	}
	
	public static synchronized List<Compra> crearListaCompras(ResultSet rs) throws SQLException {
		List<Compra> lc = new ArrayList<Compra>();
		
		while (rs.next()) {
			Compra c = crearCompra(
				rs.getInt("idCompra"), 
				rs.getInt("cantidad"), 
				rs.getDate("fecha"), 
				rs.getString("comentarioDeCompra"), 
				rs.getString("comentarioDeVenta"), 
				rs.getInt("calificacionDeCompra"), 
				rs.getInt("calificacionDeVenta"), 
				rs.getInt("idUsuario"), 
				rs.getInt("idPublicacion"), 
				rs.getInt("idDireccion"), 
				rs.getInt("idTarjeta"));
			
			lc.add(c);
		}
		
		return lc;
	}
}
