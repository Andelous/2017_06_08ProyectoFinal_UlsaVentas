package modelo;

import java.sql.Date;

import dao.DireccionJDBC;
import dao.PublicacionJDBC;
import dao.TarjetaDeCreditoJDBC;
import dao.UsuarioJDBC;

public class Compra {
	
	private int idCompra;
	
	private int cantidad;
	private Date fecha;
	private String comentarioDeCompra;
	private String comentarioDeVenta;
	private int calificacionDeCompra;
	private int calificacionDeVenta;
	
	private int idUsuario;
	private int idPublicacion;
	private int idDireccion;
	private int idTarjeta;
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}
	
	public Publicacion getPublicacion() {
		return PublicacionJDBC.seleccionarPublicacion(this.idPublicacion);
	}
	
	public TarjetaDeCredito getTarjeta() {
		return TarjetaDeCreditoJDBC.seleccionarTarjetaDeCredito(this.idTarjeta);
	}
	
	public Direccion getDireccion() {
		return DireccionJDBC.seleccionarDireccion(this.idDireccion);
	}
	
	// GETTERS
	public int getIdCompra() {
		return idCompra;
	}
	public int getCantidad() {
		return cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public String getComentarioDeCompra() {
		return comentarioDeCompra;
	}
	public String getComentarioDeVenta() {
		return comentarioDeVenta;
	}
	public int getCalificacionDeCompra() {
		return calificacionDeCompra;
	}
	public int getCalificacionDeVenta() {
		return calificacionDeVenta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public int getIdPublicacion() {
		return idPublicacion;
	}
	public int getIdDireccion() {
		return idDireccion;
	}
	public int getIdTarjeta() {
		return idTarjeta;
	}
	
	// SETTERS
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setComentarioDeCompra(String comentarioDeCompra) {
		this.comentarioDeCompra = comentarioDeCompra;
	}
	public void setComentarioDeVenta(String comentarioDeVenta) {
		this.comentarioDeVenta = comentarioDeVenta;
	}
	public void setCalificacionDeCompra(int calificacionDeCompra) {
		this.calificacionDeCompra = calificacionDeCompra;
	}
	public void setCalificacionDeVenta(int calificacionDeVenta) {
		this.calificacionDeVenta = calificacionDeVenta;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}
}
