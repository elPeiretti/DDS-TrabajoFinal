package com.tp.interfaz.dto;

public class TipoHabitacionDTO {
    private String tipo;
    private Integer capacidad;
    private Integer id;

    public TipoHabitacionDTO(String tipo, Integer capacidad){
        this.tipo = tipo;
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getId() {
        return id;
    }    
}
