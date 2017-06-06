package modelo;

public class Usuario {
	
	private int idUsuario;
	
	private String usuario;
	private String contrasena;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	
	// GETTERS
	public String getUsuario() {
		return usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	
	// SETTERS
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
