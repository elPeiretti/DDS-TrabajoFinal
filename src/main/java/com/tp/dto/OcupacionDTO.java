package com.tp.dto;

import java.time.Instant;
import java.util.List;


public class OcupacionDTO {
	private Integer idOcupacion;
	
	private Instant fechaIngreso;
	
	private Instant fechaEgreso;
	
	private Integer idHabitacion;
	
	private PasajeroDTO responsable;
	
	private List<PasajeroDTO> acompaniantes;

	public Integer getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(Integer idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public Instant getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Instant fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Instant getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Instant fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Integer getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(Integer idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public PasajeroDTO getResponsable() {
		return responsable;
	}

	public void setResponsable(PasajeroDTO responsable) {
		this.responsable = responsable;
	}

	public List<PasajeroDTO> getAcompaniantes() {
		return acompaniantes;
	}

	public void setAcompaniantes(List<PasajeroDTO> acompaniantes) {
		this.acompaniantes = acompaniantes;
	}
	
}
