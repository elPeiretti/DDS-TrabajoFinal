package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.responsable_pago_tercero")

public class ResponsablePagoTercero {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_responsable_tercero")
	private Integer idResponsable;
	@Column (name = "razon_social")
	private String razonSocial;
	@Column (name = "cuit")
	private String cuit;
	@Column (name = "telefono")
	private String telefono;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_direccion", referencedColumnName = "id_direccion")
	private Direccion direccion;
	
	public Integer getIdResponsable() {
		return idResponsable;
	}
	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	
}
