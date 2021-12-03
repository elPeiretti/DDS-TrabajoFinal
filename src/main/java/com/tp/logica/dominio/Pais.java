package com.tp.logica.dominio;

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
	
	public Pais(String nombre) {
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

	@Override
	public boolean equals(Object p){
		return p instanceof Pais && this.idPais.equals(((Pais) p).idPais);
	}
}
