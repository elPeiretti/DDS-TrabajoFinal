package com.tp.interfaz.dto;

import com.tp.logica.dominio.EstadoHabitacion;

public class HabitacionDTO implements Comparable<HabitacionDTO>{

	private String numero;	
	private EstadoHabitacion estado;
	private String tipoHabitacion;
	private TipoHabitacionDTO tipoHabitacionDTO;
	private Integer idHabitacion;
	
	public HabitacionDTO(){}

	public HabitacionDTO(String numero, EstadoHabitacion estado, String tipoHab, TipoHabitacionDTO tipoHabDTO, Integer id){
		this.numero = numero;
		this.estado = estado;
		this.tipoHabitacion = tipoHab;
		this.tipoHabitacionDTO = tipoHabDTO;
		this.idHabitacion = id;
	}

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
	public int compareTo(HabitacionDTO o) {
		if(this.tipoHabitacion.compareTo( o.tipoHabitacion)==0){
			return this.numero.compareTo( o.numero);
		}
		return this.tipoHabitacion.compareTo( o.tipoHabitacion);
	}

	public String getTipoHabitacion(){
		return tipoHabitacion;
	}

	public TipoHabitacionDTO getTipoHabitacionDTO(){
		return tipoHabitacionDTO;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Integer getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(Integer id) {
		idHabitacion = id;
	}
}
