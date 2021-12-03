package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedioDePago {
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE)
	@Column (name = "id_medio")
	private Integer idMedio;
	
	@Column (name = "monto")
	private Double monto;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_moneda", referencedColumnName = "id_moneda")
	private Moneda moneda;
	
	@Column (name = "cotizacion_en_pesos")
	private Double cotizacionEnPesos;
}
