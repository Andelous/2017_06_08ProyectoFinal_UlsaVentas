package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.TarjetaDeCredito;

public class TarjetaDeCreditoJDBC {
	
	// SELECT
	public static synchronized TarjetaDeCredito seleccionarTarjetaDeCredito(int idTarjeta) {
		TarjetaDeCredito tdc = null;
		
		String query = "SELECT * FROM TarjetaDeCredito WHERE idTarjeta = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idTarjeta);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				tdc = crearTarjetaDeCredito(
					rs.getInt("idTarjeta"), 
					rs.getString("numeroDeTarjeta"),
					rs.getInt("idUsuario"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return tdc;
	}
	
	public static synchronized List<TarjetaDeCredito> seleccionarTarjetasDeCredito(int idUsuario) {
		List<TarjetaDeCredito> ltdc = null;
		
		String query = "SELECT * FROM TarjetaDeCredito WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			ltdc = crearListaTarjetasDeCredito(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return ltdc;
	}
	
	// INSERT
	public static synchronized boolean insertarTarjeta(TarjetaDeCredito tdc) {
		boolean tarjetaInsertada = false;
		
		String query = 
			"INSERT INTO TarjetaDeCredito " +
				"(numeroDeTarjeta, idUsuario) " +
			"VALUES " + 
				"(?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, tdc.getNumeroDeTarjeta());
			ps.setInt(2, tdc.getIdUsuario());
			
			int filasInsertadas = ps.executeUpdate();
			
			tarjetaInsertada = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return tarjetaInsertada;
	}
	
	// UPDATE
	public static synchronized boolean actualizarTarjeta(TarjetaDeCredito tdc) {
		boolean tarjetaActualizada = false;
		
		String query = 
			"UPDATE TarjetaDeCredito SET " + 
				"numeroDeTarjeta = ? " + 
			"WHERE idTarjeta = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, tdc.getNumeroDeTarjeta());
			ps.setInt(2, tdc.getIdTarjeta());
			
			int filasActualizadas = ps.executeUpdate();
			
			tarjetaActualizada = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return tarjetaActualizada;
	}
	
	// DELETE
	public static synchronized boolean eliminarTarjeta(int idTarjeta) {
		boolean tarjetaEliminada = false;
		
		String query = "DELETE FROM TarjetaDeCredito WHERE idTarjeta = ?";

		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idTarjeta);
			
			int filasEliminadas = ps.executeUpdate();
			
			tarjetaEliminada = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return tarjetaEliminada;
	}
	
	// Métodos misceláneos
	public static synchronized TarjetaDeCredito crearTarjetaDeCredito(int idTarjeta, String numeroDeTarjeta, int idUsuario) {
		TarjetaDeCredito tdc = new TarjetaDeCredito();
		
		tdc.setIdTarjeta(idTarjeta);
		tdc.setIdUsuario(idUsuario);
		tdc.setNumeroDeTarjeta(numeroDeTarjeta);
		
		return tdc;
	}
	
	public static synchronized List<TarjetaDeCredito> crearListaTarjetasDeCredito(ResultSet rs) throws SQLException {
		List<TarjetaDeCredito> ltdc = new ArrayList<TarjetaDeCredito>();
		
		while (rs.next()) {
			TarjetaDeCredito tdc = crearTarjetaDeCredito(
				rs.getInt("idTarjeta"), 
				rs.getString("numeroDeTarjeta"),
				rs.getInt("idUsuario"));
			
			ltdc.add(tdc);
		}
		
		return ltdc;
	}
}
