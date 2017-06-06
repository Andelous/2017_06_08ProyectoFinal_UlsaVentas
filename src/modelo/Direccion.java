package modelo;

import dao.UsuarioJDBC;

public class Direccion {
	
	private int idDireccion;
	
	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String colonia;
	private String codigoPostal;
	private String estado;
	private boolean isSucursal;
	
	private int idUsuario;
	
	
	// Comportamiento
	public Usuario getUsuario() {
		return UsuarioJDBC.seleccionarUsuario(this.idUsuario);
	}
	
	
	// GETTERS
	public int getIdDireccion() {
		return idDireccion;
	}
	public String getCalle() {
		return calle;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public String getColonia() {
		return colonia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public String getEstado() {
		return estado;
	}
	public boolean isSucursal() {
		return isSucursal;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	
	// SETTERS
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setSucursal(boolean isSucursal) {
		this.isSucursal = isSucursal;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
