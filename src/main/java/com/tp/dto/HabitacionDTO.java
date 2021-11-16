package com.tp.dto;

import com.tp.dominio.habitacion.EstadoHabitacion;
import com.tp.dominio.habitacion.TipoHabitacion;

public class HabitacionDTO implements Comparable{

	private String numero;	
	private EstadoHabitacion estado;
	private String tipoHabitacion;
	
	public HabitacionDTO(String numero, EstadoHabitacion estado, String tipoHabitacion) {
		this.numero = numero;
		this.estado = estado;
		this.tipoHabitacion = tipoHabitacion;
	}

	public void setEstado(EstadoHabitacion estado) {
		this.estado = estado;	
	}

	public String getNumero(){
		return numero;
	}
	public EstadoHabitacion getEstado(){
		return estado;
	}

	@Override
	public int compareTo(Object o) {
		if(this.tipoHabitacion.compareTo( ((HabitacionDTO) o).tipoHabitacion)==0){
			return this.numero.compareTo( ((HabitacionDTO) o).numero);
		}
		return this.tipoHabitacion.compareTo( ((HabitacionDTO) o).tipoHabitacion);
	}
	
}
