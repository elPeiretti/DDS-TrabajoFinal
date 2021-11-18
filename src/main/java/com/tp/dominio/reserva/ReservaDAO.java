package com.tp.dominio.reserva;

import java.time.Instant;
import java.util.List;

public interface ReservaDAO {

	List<Reserva> getReservasInRange(Instant fecha_desde, Instant fecha_hasta);

    List<Reserva> getReservasInRange(Instant fechaInicio, Instant fechaFin, String numeroHabitacion);

}
