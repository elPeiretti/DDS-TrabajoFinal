package com.tp.dominio.factura;

import java.time.Instant;

import java.util.Collection;

import javax.persistence.*;

import com.tp.dominio.factura.cancelacion.NotaDeCredito;
import com.tp.dominio.factura.cancelacion.Pago;
import com.tp.dominio.factura.items.ItemFactura;
import com.tp.dominio.pasajero.Pasajero;

@Entity
@Table(name = "tpdds.factura")

public class Factura {
	@Enumerated(EnumType.STRING)
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
	@OneToOne (mappedBy = "factura", cascade = CascadeType.ALL)
	private Pago pago;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name =  "id_responsable_tercero", referencedColumnName = "id_responsable_tercero")
	private ResponsablePagoTercero responsableTercero;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name =  "id_pasajero", referencedColumnName = "id_pasajero")
	private Pasajero responsablePasajero;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name =  "id_factura", referencedColumnName = "id_factura")
	private Collection<ItemFactura> items;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name =  "id_nota", referencedColumnName = "id_nota")
	private NotaDeCredito notaCredito;
}
