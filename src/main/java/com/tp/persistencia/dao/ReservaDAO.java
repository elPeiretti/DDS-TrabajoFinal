package com.tp.persistencia.dao;

import java.time.LocalDate;
import java.util.List;

import com.tp.logica.dominio.Reserva;

public interface ReservaDAO {

	List<Reserva> getReservasInRange(LocalDate fecha_desde, LocalDate fecha_hasta);

    List<Reserva> getReservasInRange(LocalDate fechaInicio, LocalDate fechaFin, String numeroHabitacion);

}
