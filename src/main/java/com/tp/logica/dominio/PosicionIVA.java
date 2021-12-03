package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.posicion_iva")

public class PosicionIVA {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_posicion_iva")
	private Integer idPosicionIVA;
	@Column (name = "posicion")
	private String posicion;
	
	
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
	
	public boolean equals(Object p){
		return p instanceof PosicionIVA && this.idPosicionIVA.equals(((PosicionIVA) p).idPosicionIVA);
	}
	
	
}
