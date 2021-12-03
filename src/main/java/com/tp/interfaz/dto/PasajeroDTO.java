package com.tp.interfaz.dto;

import java.time.LocalDate;
import java.util.Vector;

public class PasajeroDTO {
	private Integer idPasajero;
	private String nombres;
	private String apellido;
	private String cuit;
	private String nroDocumento;
	private LocalDate fechaDeNacimiento;
	private Integer nacionalidad; 
	private String email;
	private String telefono;
	private String ocupacion;
	private TipoDocumentoDTO tipoDocumentoDTO;
	private PosicionIVADTO posicionIVA;
	private DireccionDTO direccionDTO; 
	
	public PasajeroDTO(Integer idPasajero, String nombre, String apellido, String cuit, String nroDocumento,
			LocalDate fechaDeNacimiento, Integer nacionalidad, String email, String telefono, String ocupacion,
			TipoDocumentoDTO tipoDocumentoDTO, PosicionIVADTO posicionIVA, DireccionDTO direccionDTO) {
		super();
		this.idPasajero = idPasajero;
		this.nombres = nombre;
		this.apellido = apellido;
		this.cuit = cuit;
		this.nroDocumento = nroDocumento;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.email = email;
		this.telefono = telefono;
		this.ocupacion = ocupacion;
		this.tipoDocumentoDTO = tipoDocumentoDTO;
		this.posicionIVA = posicionIVA;
		this.direccionDTO = direccionDTO;
	}
	
	
	public Integer getIdPasajero() {
		return idPasajero;
	}
	public void setIdPasajero(Integer idPasajero) {
		this.idPasajero = idPasajero;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombre) {
		this.nombres = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public Integer getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Integer nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public TipoDocumentoDTO getTipoDocumentoDTO() {
		return tipoDocumentoDTO;
	}
	public void setTipoDocumentoDTO(TipoDocumentoDTO tipoDocumentoDTO) {
		this.tipoDocumentoDTO = tipoDocumentoDTO;
	}
	
	public PosicionIVADTO getPosicionIVA() {
		return posicionIVA;
	}
	public void setPosicionIVA(PosicionIVADTO posicionIVA) {
		this.posicionIVA = posicionIVA;
	}

	public DireccionDTO getDireccionDTO() {
		return direccionDTO;
	}
	public void setDireccionDTO(DireccionDTO direccionDTO) {
		this.direccionDTO = direccionDTO;
	}
	
	public Vector<Object> asVector() {
		Vector<Object> resultado = new Vector<Object>();
		
		resultado.add(apellido);
		resultado.add(nombres);
		resultado.add(tipoDocumentoDTO.getTipo());
		resultado.add(nroDocumento);
		
		return resultado;
	}
	
	public boolean equals(Object o2) {
		return o2 instanceof PasajeroDTO && ((PasajeroDTO) o2).idPasajero.equals(idPasajero);
	}
	
	
	
}
