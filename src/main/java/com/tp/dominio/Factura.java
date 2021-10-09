package com.tp.dominio;

import java.time.Instant;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.factura")

public class Factura {
	@Column (name = "tipo_factura")
	private TipoFactura tipo;
	@Column (name =  "importe_factura")
	private Double importeTotal;//mal
	@Column (name =  "fecha_de_emision")
	private Instant fechaDeEmision;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name =  "id_factura")
	private Integer idFactura;
	@Column (name =  "total_iva")
	private Double totalIVA;
	@OneToOne (mappedBy = "factura")
	private Pago pago;
	@ManyToOne
	@JoinColumn (name =  "id_responsable_tercero", referencedColumnName = "id_responsable_tercero")
	private ResponsablePagoTercero responsableTercero;
	@ManyToOne
	@JoinColumn (name =  "id_pasajero", referencedColumnName = "id_pasajero")
	private Pasajero responsablePasajero;
	@OneToMany
	@JoinColumn (name =  "id_factura", referencedColumnName = "id_factura")
	private Collection<ItemFactura> items;
	@ManyToOne
	@JoinColumn (name =  "id_nota", referencedColumnName = "id_nota")
	private NotaDeCredito notaCredito;
}
