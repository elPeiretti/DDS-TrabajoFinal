package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.provincia")

public class Provincia {
	@Column (name = "nombre")
	private String nombre;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_provincia")
	private Integer idProvincia; 
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_pais", referencedColumnName = "id_pais")
	private Pais pais;
	
	public Provincia() {}
	
	public Provincia(String nombre, Pais pais) {
		this.nombre = nombre;
		this.pais = pais;
	}
	
	public Integer getIdProvincia() {
		return idProvincia;
	}	
	
	public String getNombre() {
		return nombre;
	}
	
	public Pais getPais() {
		return pais;
	}
}
