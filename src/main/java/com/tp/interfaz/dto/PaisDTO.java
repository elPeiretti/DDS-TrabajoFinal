package com.tp.interfaz.dto;



public class PaisDTO {
	
	private String nombre;
	
	private Integer idPais;

	public PaisDTO (Integer idPais, String nombre) {
		this.idPais = idPais;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	
	public String toString() {
		return nombre;
	}
	
}
