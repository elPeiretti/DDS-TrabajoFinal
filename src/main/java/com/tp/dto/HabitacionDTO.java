package com.tp.dto;

import com.tp.dominio.habitacion.EstadoHabitacion;

public class HabitacionDTO {

	private String numero;	
	private EstadoHabitacion estado;
	
	public HabitacionDTO(String numero, EstadoHabitacion estado) {
		this.numero = numero;
		this.estado = estado;
	}

	public void setEstado(EstadoHabitacion estado) {
		this.estado = estado;	
	}
	
}
