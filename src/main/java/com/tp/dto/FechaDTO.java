package com.tp.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Vector;

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

    public Vector<String> getDataAsStringVector() {
        Vector<String> data = new Vector<String>();
		data.add(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.ofInstant(fecha,ZoneId.of("America/Buenos_Aires"))).toString());
		habitaciones.values().stream().sorted().map(h -> h.getEstado().toString()).forEach(e -> data.add(e));
		return data;
    }
}
