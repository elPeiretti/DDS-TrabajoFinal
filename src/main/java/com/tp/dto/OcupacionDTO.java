package com.tp.dto;

import java.time.Instant;
import java.util.List;


public class OcupacionDTO {
	private Integer idOcupacion;

	private Instant fechaIngreso;
	
	private Instant fechaEgreso;
	
	private PasajeroDTO responsable;
	
	private List<PasajeroDTO> acompaniantes;

	private HabitacionDTO habitacion;

	public OcupacionDTO(){
		this.habitacion = new HabitacionDTO();
	}

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

    public HabitacionDTO getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionDTO h) {
		this.habitacion = h;
    }

}
