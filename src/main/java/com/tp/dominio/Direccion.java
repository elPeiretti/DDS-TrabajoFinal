package com.tp.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.direccion")

public class Direccion {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id_direccion")
	private Integer idDireccion;
	@Column(name = "codigo_postal")
	private String codigoPostal;
	@Column(name = "calle")
	private String calle;
	@Column(name = "nro_calle")
	private Integer nroCalle;
	@Column(name = "piso")
	private Integer piso;
	@Column(name = "dpto")
	private String nroDepartamento;
	@ManyToOne
	@JoinColumn (name = "id_ciudad", referencedColumnName = "id_ciudad")
	private Ciudad ciudad;
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public String getCalle() {
		return calle;
	}
	public Integer getNroCalle() {
		return nroCalle;
	}
	public Integer getPiso() {
		return piso;
	}
	public String getNroDepartamento() {
		return nroDepartamento;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public Pais getPais() {
		return ciudad.getPais();
	}
	public Provincia getProvincia() {
		return ciudad.getProvincia();
	}
}
