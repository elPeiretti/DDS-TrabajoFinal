package com.tp.dominio.geo;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.pais")

public class Pais {
	
	@Column(name = "nombre")
	private String nombre;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pais")
	private Integer idPais;
	
	public Pais() {}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getIdPais() {
		return idPais;
	}	
}
