package modelo;

import java.sql.Date;

import dao.PublicacionJDBC;
import dao.UsuarioJDBC;

public class Comentario {
	
	private int idComentario;
	
	private String comentario;
	private Date fecha;
	
	private int idUsuario;
	private int idPublicacion;
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}
	
	public Publicacion getPublicacion() {
		return PublicacionJDBC.seleccionarPublicacion(this.idPublicacion);
	}

	
	// GETTERS
	public int getIdComentario() {
		return idComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	// SETTERS
	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
}
