package com.tp.dominio.habitacion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
	
	public CostoPorNoche() {}
	
	public CostoPorNoche (Instant fechaInicioVigencia, Instant fechaFinVigencia, Double costo) {
		this.fechaInicioVigencia = fechaInicioVigencia;
		this.fechaFinVigencia = fechaFinVigencia;
		this.costo = costo;
	}
	
	public String toString() {
		return idCostoPorNoche.toString() + " " + LocalDateTime.ofInstant(fechaFinVigencia, ZoneOffset.UTC).toString() + " " 
	+ LocalDateTime.ofInstant(fechaInicioVigencia, ZoneOffset.UTC).toString();
	}
}
