package com.tp.dto;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tp.dominio.direccion.Direccion;
import com.tp.dominio.pasajero.PosicionIVA;
import com.tp.dominio.pasajero.TipoDocumento;

public class PasajeroDTO {
	private Integer idPasajero;
	private String nombres;
	private String apellido;
	private String cuit;
	private String nroDocumento;
	private Instant fechaDeNacimiento;
	private String nacionalidad; 
	private String email;
	private String telefono;
	private String ocupacion;
	private TipoDocumentoDTO tipoDocumentoDTO;
	private Integer idPosicionIVA;
	private DireccionDTO direccionDTO; 
	
	public PasajeroDTO(Integer idPasajero, String nombre, String apellido, String cuit, String nroDocumento,
			Instant fechaDeNacimiento, String nacionalidad, String email, String telefono, String ocupacion,
			TipoDocumentoDTO tipoDocumentoDTO, Integer idPosicionIVA, DireccionDTO direccionDTO) {
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
		this.idPosicionIVA = idPosicionIVA;
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
	public Instant getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Instant fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
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
	public Integer getIdPosicionIVA() {
		return idPosicionIVA;
	}
	public void setIdPosicionIVA(Integer idPosicionIVA) {
		this.idPosicionIVA = idPosicionIVA;
	}
	public DireccionDTO getDireccionDTO() {
		return direccionDTO;
	}
	public void setDireccionDTO(DireccionDTO direccionDTO) {
		this.direccionDTO = direccionDTO;
	}
	
	
	
}
