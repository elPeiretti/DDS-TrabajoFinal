package com.tp.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.provincia")

public class Provincia {
	@Column (name = "nombre")
	private String nombre;
	@Id
	@Column (name = "id_provincia")
	private Integer idProvincia; 
	@ManyToOne
	@JoinColumn (name = "id_pais", referencedColumnName = "id_pais")
	private Pais pais;
	
	
	public Pais getPais() {
		return pais;
	}
}
