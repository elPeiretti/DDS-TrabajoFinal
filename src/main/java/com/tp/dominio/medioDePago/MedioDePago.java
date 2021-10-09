package com.tp.dominio.medioDePago;

import javax.persistence.*;

@MappedSuperclass
public abstract class MedioDePago {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_medio")
	private Integer idMedio;
	
	@Column (name = "monto")
	private Double monto;
	
	@ManyToOne
	@JoinColumn (name = "id_moneda", referencedColumnName = "id_moneda")
	private Moneda moneda;
	
	@Column (name = "cotizacion_en_pesos")
	private Double cotizacionEnPesos;
}
