package com.tp.interfaz.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Vector;

public class FechaDTO {

	private LocalDate fecha;
	
	private Map<String, HabitacionDTO> habitaciones;
	
	public FechaDTO(LocalDate fecha, Map<String,HabitacionDTO> habitaciones) {
		this.fecha = fecha;
		this.habitaciones = habitaciones;
	}
	
	public Map<String,HabitacionDTO> getHabitaciones() {
		return habitaciones;
	}

    public Vector<String> getDataAsStringVector() {
        Vector<String> data = new Vector<String>();
		data.add(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha));
		habitaciones.values().stream().sorted().map(h -> h.getEstado().toString()).forEach(e -> data.add(e));
		return data;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
