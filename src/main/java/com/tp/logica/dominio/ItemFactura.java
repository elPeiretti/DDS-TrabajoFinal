package com.tp.logica.dominio;

import javax.persistence.*;

import com.tp.interfaz.dto.ItemFacturaDTO;

@Entity
@Table(name = "tpdds.item_factura")

public class ItemFactura {
	
    @Column (name = "cantidad")
	private Integer cantidad;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_item_factura")
	private Integer idItemFactura;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_servicio", referencedColumnName = "id_servicio")
	private Servicio servicio;

	public ItemFactura(){}
	
	public ItemFactura(ItemFacturaDTO itemDTO) {
		this.cantidad = itemDTO.getCantidad();
    }

	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getIdItemFactura() {
		return idItemFactura;
	}
	public void setIdItemFactura(Integer idItemFactura) {
		this.idItemFactura = idItemFactura;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	
}
