package modelo;

import dao.UsuarioJDBC;

public class Correo {
	
	private int idCorreo;
	
	private String direccionDeCorreo;
	
	private int idUsuario;
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}
	
	
	// GETTERS
	public int getIdCorreo() {
		return idCorreo;
	}
	public String getDireccionDeCorreo() {
		return direccionDeCorreo;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	
	// SETTERS
	public void setIdCorreo(int idCorreo) {
		this.idCorreo = idCorreo;
	}
	public void setDireccionDeCorreo(String direccionDeCorreo) {
		this.direccionDeCorreo = direccionDeCorreo;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
