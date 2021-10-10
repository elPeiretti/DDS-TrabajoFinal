package com.tp.dominio;

import javax.persistence.*;

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
}
