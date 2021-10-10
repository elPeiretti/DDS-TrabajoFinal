package com.tp.dominio;

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
}
