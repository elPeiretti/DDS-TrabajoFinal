package com.tp.interfaz.dto;


public class PosicionIVADTO {

	private Integer idPosicionIVA;
	
	private String posicion;
	
	public PosicionIVADTO(Integer idPosicionIVA, String posicion) {
		super();
		this.idPosicionIVA = idPosicionIVA;
		this.posicion = posicion;
	}

	public Integer getIdPosicionIVA() {
		return idPosicionIVA;
	}

	public void setIdPosicionIVA(Integer idPosicionIVA) {
		this.idPosicionIVA = idPosicionIVA;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}	
	
	public String toString() {
		return posicion;
	}
	
}
