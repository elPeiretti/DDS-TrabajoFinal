package com.tp.interfaz.dto;

public class DireccionDTO {
	private Integer idDireccion;
	private String codigoPostal;
	private String calle;
	private Integer nroCalle;
	private Integer piso;
	private String nroDepartamento;
	private Integer idCiudad;
	
	public DireccionDTO(Integer idDireccion, String codigoPostal, String calle, Integer nroCalle, Integer piso,
			String nroDepartamento, Integer idCiudad, Integer idProvincia, Integer idPais) {
		super();
		this.idDireccion = idDireccion;
		this.codigoPostal = codigoPostal;
		this.calle = calle;
		this.nroCalle = nroCalle;
		this.piso = piso;
		this.nroDepartamento = nroDepartamento;
		this.idCiudad = idCiudad;
		this.idProvincia = idProvincia;
		this.idPais = idPais;
	}
	public Integer getIdDireccion() {
		return idDireccion;
	}
	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Integer getNroCalle() {
		return nroCalle;
	}
	public void setNroCalle(Integer nroCalle) {
		this.nroCalle = nroCalle;
	}
	public Integer getPiso() {
		return piso;
	}
	public void setPiso(Integer piso) {
		this.piso = piso;
	}
	public String getNroDepartamento() {
		return nroDepartamento;
	}
	public void setNroDepartamento(String nroDepartamento) {
		this.nroDepartamento = nroDepartamento;
	}
	public Integer getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}
	public Integer getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	private Integer idProvincia;
	private Integer idPais;
}
