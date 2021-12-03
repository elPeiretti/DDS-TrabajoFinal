package com.tp.interfaz.dto;



public class CiudadDTO {
	
	private String nombre;

	private Integer idCiudad;

	public CiudadDTO(Integer idCiudad, String nombre) {
		super();
		this.nombre = nombre;
		this.idCiudad = idCiudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	} 
	
	public String toString() {
		return nombre;
	}
	
	
}
