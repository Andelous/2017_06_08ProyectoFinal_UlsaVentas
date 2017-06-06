package modelo;

import dao.UsuarioJDBC;

public class Publicacion {
	
	private int idPublicacion;
	
	private String titulo;
	private String descripcion;
	private double precio;
	private int cantidad;
	private int vendidos;
	private String direccionImagen;
	
	private int idUsuario;
	
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}

	
	
	// GETTERS
	public int getIdPublicacion() {
		return idPublicacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getVendidos() {
		return vendidos;
	}

	public String getDireccionImagen() {
		return direccionImagen;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	// SETTERS
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setVendidos(int vendidos) {
		this.vendidos = vendidos;
	}

	public void setDireccionImagen(String direccionImagen) {
		this.direccionImagen = direccionImagen;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
