package com.tp.dominio.geo;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.pais")

public class Pais {
	
	public Pais() {}
	
	public Pais(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "nombre")
	private String nombre;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pais")
	private Integer idPais;
	
	public Integer getIdPais() {
		return idPais;
	}	
}
