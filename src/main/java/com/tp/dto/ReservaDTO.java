package com.tp.dto;

import java.time.Instant;

import com.tp.dominio.reserva.Reserva;

public class ReservaDTO {
    Integer idReserva;
    Integer idHabitacion;
    String numeroHabitacion;
    String nombreResponsable;
	String apellidoResponsable;
    Instant inicioReserva;
    Instant finReserva;

    public ReservaDTO() {}
    public ReservaDTO(Reserva r) {
        idReserva = r.getIdReserva();
        idHabitacion = r.getHabitacion().getIdHabitacion();
        numeroHabitacion = r.getHabitacion().getNumero();
        nombreResponsable = r.getResponsable().getNombre();
        apellidoResponsable = r.getResponsable().getApellido();
        inicioReserva = r.getFechaIngreso();
        finReserva = r.getFechaEgreso();
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public Instant getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(Instant inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public Instant getFinReserva() {
        return finReserva;
    }

    public void setFinReserva(Instant finReserva) {
        this.finReserva = finReserva;
    }
    
}
