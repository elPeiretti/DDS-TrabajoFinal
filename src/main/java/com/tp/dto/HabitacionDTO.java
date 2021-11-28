package com.tp.dto;

import com.tp.dominio.habitacion.EstadoHabitacion;
import com.tp.dominio.habitacion.Habitacion;
import com.tp.dominio.habitacion.TipoHabitacion;

public class HabitacionDTO implements Comparable{

	private String numero;	
	private EstadoHabitacion estado;
	private String tipoHabitacion;
	private TipoHabitacionDTO tipoHabitacionDTO;
	private Integer idHabitacion;
	
	public HabitacionDTO(){}

	public HabitacionDTO(Habitacion h){
		this.numero = h.getNumero();
		this.estado = h.getEstado();
		this.tipoHabitacion = h.getTipoHabitacion().getNombre();
		this.tipoHabitacionDTO = new TipoHabitacionDTO(tipoHabitacion, h.getTipoHabitacion().getCapacidad());
		this.idHabitacion = h.getIdHabitacion();
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
	public int compareTo(Object o) {
		if(this.tipoHabitacion.compareTo( ((HabitacionDTO) o).tipoHabitacion)==0){
			return this.numero.compareTo( ((HabitacionDTO) o).numero);
		}
		return this.tipoHabitacion.compareTo( ((HabitacionDTO) o).tipoHabitacion);
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
