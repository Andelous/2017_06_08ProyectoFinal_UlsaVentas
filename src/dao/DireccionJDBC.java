package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Direccion;

public class DireccionJDBC {
	
	// SELECT
	public static synchronized Direccion seleccionarDireccion(int idDireccion) { 
		Direccion d = null;
		
		String query = "SELECT * FROM Direccion WHERE idDireccion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idDireccion);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				d =
					crearDireccion(
						rs.getInt("idDireccion"), 
						rs.getString("calle"), 
						rs.getString("numeroExterior"), 
						rs.getString("numeroInterior"), 
						rs.getString("colonia"), 
						rs.getString("codigoPostal"), 
						rs.getString("estado"), 
						rs.getBoolean("isSucursal"), 
						rs.getInt("idUsuario"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return d;
	}
	
	public static synchronized List<Direccion> seleccionarDirecciones(int idUsuario) { 
		List<Direccion> ld = null;
		
		String query = "SELECT * FROM Direccion WHERE idUsuario = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idUsuario);
			
			rs = ps.executeQuery();
			
			ld = crearListaDireccion(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return ld;
	}
	
	// INSERT
	public static synchronized boolean insertarDireccion(Direccion d) {
		boolean direccionInsertada = false;
		
		String query =
			"ISNERT INTO Direccion " +
				"(calle, numeroExterior, numeroInterior, colonia, codigoPostal, estado, isSucursal, idUsuario)" +
			"VALUES " +
				"(?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, d.getCalle());
			ps.setString(2, d.getNumeroExterior());
			ps.setString(3, d.getNumeroInterior());
			ps.setString(4, d.getColonia());
			ps.setString(5, d.getCodigoPostal());
			ps.setString(6, d.getEstado());
			ps.setBoolean(7, d.isSucursal());
			ps.setInt(8, d.getIdUsuario());
			
			int filasInsertadas = ps.executeUpdate();
			
			direccionInsertada = filasInsertadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return direccionInsertada;
	}
	
	// UPDATE
	public static synchronized boolean actualizarDireccion(Direccion d) {
		boolean direccionActualizada = false;
		
		String query =
			"UPDATE Direccion SET " +
				"calle = ?, " +
				"numeroExterior = ?, " +
				"numeroInterior = ?, " + 
				"colonia = ?, " + 
				"codigoPostal = ?, "+
				"estado = ?, " +
				"isSucursal = ?, " +
				"idUsuario = ? " +
			"WHERE idDireccion = ? ";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setString(1, d.getCalle());
			ps.setString(2, d.getNumeroExterior());
			ps.setString(3, d.getNumeroInterior());
			ps.setString(4, d.getColonia());
			ps.setString(5, d.getCodigoPostal());
			ps.setString(6, d.getEstado());
			ps.setBoolean(7, d.isSucursal());
			ps.setInt(8, d.getIdUsuario());
			ps.setInt(9, d.getIdDireccion());
			
			int filasActualizadas = ps.executeUpdate();
			
			direccionActualizada = filasActualizadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return direccionActualizada;
	}
	
	
	// DELETE
	public static synchronized boolean eliminarDireccion(int idDireccion) {
		boolean direccionEliminada = false;
		
		String query = "DELETE FROM Direccion WHERE idDireccion = ?";
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = Conexion.getConexion();
			ps = c.prepareStatement(query);
			
			ps.setInt(1, idDireccion);
			
			int filasEliminadas = ps.executeUpdate();
			
			direccionEliminada = filasEliminadas == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			Conexion.deshacerCambios(c);
		} finally {
			Conexion.cerrarPreparedStatement(ps);
			Conexion.cerrarConexion(c);
		}
		
		return direccionEliminada;
	}
	
	// Métodos misceláneos
	public static synchronized Direccion crearDireccion(
		int idDireccion,
		String calle,
		String numeroExterior,
		String numeroInterior,
		String colonia,
		String codigoPostal,
		String estado,
		boolean isSucursal,
		int idUsuario
	) {
		Direccion d = new Direccion();
		
		d.setIdDireccion(idDireccion);
		d.setCalle(calle);
		d.setNumeroExterior(numeroExterior);
		d.setNumeroInterior(numeroInterior);
		d.setColonia(colonia);
		d.setCodigoPostal(codigoPostal);
		d.setEstado(estado);
		d.setSucursal(isSucursal);
		d.setIdUsuario(idUsuario);
		
		return d;
	}
	
	public static synchronized List<Direccion> crearListaDireccion(ResultSet rs) throws SQLException {
		List<Direccion> ld = new ArrayList<Direccion>();
		
		while (rs.next()) {
			Direccion d = 
				crearDireccion(
					rs.getInt("idDireccion"), 
					rs.getString("calle"), 
					rs.getString("numeroExterior"), 
					rs.getString("numeroInterior"), 
					rs.getString("colonia"), 
					rs.getString("codigoPostal"), 
					rs.getString("estado"), 
					rs.getBoolean("isSucursal"), 
					rs.getInt("idUsuario"));
			
			ld.add(d);
		}
		
		return ld;
	}
}
