package com.tp.logica.dominio;

import java.time.LocalDate;

import java.util.Collection;

import javax.persistence.*;

import com.tp.interfaz.dto.FacturaDTO;

@Entity
@Table(name = "tpdds.factura")

public class Factura {
	
    @Enumerated(EnumType.STRING)
	@Column (name = "tipo_factura")
	private TipoFactura tipo;
	@Column (name =  "importe_factura")
	private Double importeTotal;//mal
	@Column (name =  "fecha_de_emision")
	private LocalDate fechaDeEmision;
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

	public Factura(){}

	public Factura(FacturaDTO facturaDto) {
		this.tipo = facturaDto.getTipo();
		this.importeTotal = facturaDto.getImporteTotal();
		this.fechaDeEmision = facturaDto.getFechaEmision();
    }

	public TipoFactura getTipo() {
		return tipo;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	public void setFechaDeEmision(LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Double getTotalIVA() {
		return totalIVA;
	}

	public void setTotalIVA(Double totalIVA) {
		this.totalIVA = totalIVA;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public ResponsablePagoTercero getResponsableTercero() {
		return responsableTercero;
	}

	public void setResponsableTercero(ResponsablePagoTercero responsableTercero) {
		this.responsableTercero = responsableTercero;
	}

	public Pasajero getResponsablePasajero() {
		return responsablePasajero;
	}

	public void setResponsablePasajero(Pasajero responsablePasajero) {
		this.responsablePasajero = responsablePasajero;
	}

	public Collection<ItemFactura> getItems() {
		return items;
	}

	public void setItems(Collection<ItemFactura> items) {
		this.items = items;
	}

	

}
