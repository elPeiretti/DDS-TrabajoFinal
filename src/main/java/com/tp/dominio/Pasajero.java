package com.tp.dominio;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.pasajero")

public class Pasajero {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_pasajero")
	private Integer idPasajero;
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "apellido")
	private String apellido;
	@Column (name = "cuit")
	private String cuit;
	@Column (name = "nro_documento")
	private String nroDocumento;
	@Column (name = "fecha_nacimiento")
	private Instant fechaDeNacimiento;
	@Column (name = "nacionalidad")
	private String nacionalidad; //es String en diagrama de clases pero la interfaz esta hecha con ComboBox. Podria usarse Pais pero en el diseño de entradas el ejemplo es "Argentino/a"
	@Column (name = "email")
	private String email;
	@Column (name = "telefono")
	private String telefono;
	@Column (name = "ocupacion")
	private String ocupacion;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
	private TipoDocumento tipoDocumento;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_posicion_iva", referencedColumnName = "id_posicion_iva")
	private PosicionIVA posicionIVA;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_direccion", referencedColumnName = "id_direccion")
	private Direccion direccion;
	
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getCuit() {
		return cuit;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public Instant getNacimiento() {
		return fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public PosicionIVA getPosicionIVA() {
		return posicionIVA;
	}
	public Direccion getDireccion() {
		return direccion;
	}
}
