package modelo;

import dao.UsuarioJDBC;

public class Telefono {
	
	private int idTelefono;
	
	private String numero;
	
	private int idUsuario;
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}
	
	
	// GETTERS
	public int getIdTelefono() {
		return idTelefono;
	}
	public String getNumero() {
		return numero;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	
	// SETTERS
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
