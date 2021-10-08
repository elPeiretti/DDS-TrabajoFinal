package com.tp.dominio;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.costo_por_noche")

public class CostoPorNoche {
	@Column (name = "fecha_inicio_vigencia")
	private Instant fechaInicioVigencia;
	@Column (name = "fecha_fin_vigencia")
	private Instant fechaFinVigencia;
	@Column (name = "costo")
	private Double costo;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_costo_por_noche")
	private Integer idCostoPorNoche;
}
