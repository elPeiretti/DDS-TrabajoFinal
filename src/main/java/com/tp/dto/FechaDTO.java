package com.tp.dto;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FechaDTO {

	private Instant fecha;
	
	private Map<String, HabitacionDTO> habitaciones;
	
	public FechaDTO(Instant fecha, Map<String,HabitacionDTO> habitaciones) {
		this.fecha = fecha;
		this.habitaciones = habitaciones;
	}
	
	public Map<String,HabitacionDTO> getHabitaciones() {
		return habitaciones;
	}
}
