package modelo;

import dao.UsuarioJDBC;

public class TarjetaDeCredito {
	
	private int idTarjeta;
	
	private String numeroDeTarjeta;
	
	private int idUsuario;
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}
	
	
	// GETTERS
	public int getIdTarjeta() {
		return idTarjeta;
	}
	public String getNumeroDeTarjeta() {
		return numeroDeTarjeta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	
	// SETTERS
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}
	public void setNumeroDeTarjeta(String numeroDeTarjeta) {
		this.numeroDeTarjeta = numeroDeTarjeta;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
