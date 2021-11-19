package com.tp.dominio.reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaDAO {

	List<Reserva> getReservasInRange(LocalDate fecha_desde, LocalDate fecha_hasta);

    List<Reserva> getReservasInRange(LocalDate fechaInicio, LocalDate fechaFin, String numeroHabitacion);

}
