package com.tp.logica.dominio;

import java.time.LocalDate;

import javax.persistence.*;

import com.tp.interfaz.dto.PasajeroDTO;

@Entity
@Table(name = "tpdds.pasajero")

public class Pasajero {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_pasajero")
	private Integer idPasajero;

	@Column (name = "nombres")
	private String nombres;

	@Column (name = "apellido")
	private String apellido;

	@Column (name = "cuit")
	private String cuit;

	@Column (name = "nro_documento")
	private String nroDocumento;

	@Column (name = "fecha_nacimiento")
	private LocalDate fechaDeNacimiento;

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "nacionalidad", referencedColumnName = "id_pais")
	private Pais nacionalidad; //es String en diagrama de clases pero la interfaz esta hecha con ComboBox. Podria usarse Pais pero en el diseño de entradas el ejemplo es "Argentino/a"
	
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

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_direccion", referencedColumnName = "id_direccion")
	private Direccion direccion;
	
	public Pasajero(){}

	public Pasajero(PasajeroDTO p) {
		this.apellido = p.getApellido();
		this.nombres = p.getNombres();
		this.cuit = p.getCuit();
		this.email = p.getEmail();
		this.fechaDeNacimiento = p.getFechaDeNacimiento();
		this.idPasajero = p.getIdPasajero();
		this.nroDocumento = p.getNroDocumento();
		this.telefono = p.getTelefono();
		this.ocupacion = p.getOcupacion();
    }

    public Integer getIdPasajero() {
		return idPasajero;
	}
	public String getNombres() {
		return nombres;
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
	public LocalDate getNacimiento() {
		return fechaDeNacimiento;
	}
	public Pais getNacionalidad() {
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

    public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
    }
	public void setTipoDocumento(TipoDocumento td) {
		this.tipoDocumento = td;
	}
	public void setDireccion(Direccion direc) {
		this.direccion = direc;
	}
    public void setPosicionIVA(PosicionIVA pIva) {
		this.posicionIVA = pIva;
    }
}
