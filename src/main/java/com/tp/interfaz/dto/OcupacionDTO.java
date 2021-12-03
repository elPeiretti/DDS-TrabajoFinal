package com.tp.interfaz.dto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class OcupacionDTO {
	private Integer idOcupacion;

	private LocalDate fechaIngreso;
	
	private LocalDate fechaEgreso;
	
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

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
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
	public List<PasajeroDTO> getPasajeros() {
		List<PasajeroDTO> lista = new LinkedList<PasajeroDTO>();
		acompaniantes.stream().forEach(a -> lista.add(a));
		if(!lista.contains(responsable))
			lista.add(responsable);
		return lista;
	}
}
