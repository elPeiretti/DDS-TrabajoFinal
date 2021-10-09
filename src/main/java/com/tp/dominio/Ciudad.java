package com.tp.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.ciudad")

public class Ciudad {
	
	@Column (name = "nombre")
	private String nombre;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_ciudad")
	private Integer idCiudad; 
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_provincia", referencedColumnName = "id_provincia")
	private Provincia provincia;
	
	public Ciudad(String nombre, Provincia provincia) {
		this.nombre = nombre;
		this.provincia = provincia;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	public Pais getPais() {
		return provincia.getPais();
	}
}
