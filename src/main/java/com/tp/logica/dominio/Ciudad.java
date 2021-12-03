package com.tp.logica.dominio;

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
	
	
	public Ciudad() {}
	
	public Ciudad(String nombre, Provincia provincia) {
		this.nombre = nombre;
		this.provincia = provincia;
	}
	
	public Integer getIdCiudad() {
		return idCiudad;
	}	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public String getNombre() {
		return nombre;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	public Pais getPais() {
		return provincia.getPais();
	}
	
	public boolean equals(Object p) {
		return p instanceof Ciudad && this.idCiudad.equals(((Ciudad) p).idCiudad);	
	}
	
}
