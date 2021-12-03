package com.tp.logica.dominio;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "tpdds.costo_por_noche")

public class CostoPorNoche {
	@Column (name = "fecha_inicio_vigencia")
	private LocalDate fechaInicioVigencia;
	@Column (name = "fecha_fin_vigencia")
	private LocalDate fechaFinVigencia;
	@Column (name = "costo")
	private Double costo;
	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_costo_por_noche")
	private Integer idCostoPorNoche;
	
	public CostoPorNoche() {}
	
	public CostoPorNoche (LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia, Double costo) {
		this.fechaInicioVigencia = fechaInicioVigencia;
		this.fechaFinVigencia = fechaFinVigencia;
		this.costo = costo;
	}
	
	public String toString() {
		return idCostoPorNoche.toString() + " " + fechaFinVigencia.toString() + " " + fechaInicioVigencia.toString();
	}
}
