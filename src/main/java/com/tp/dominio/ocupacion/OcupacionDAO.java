package com.tp.dominio.ocupacion;

import java.time.Instant;
import java.util.List;

import com.tp.dominio.reserva.Reserva;

public interface OcupacionDAO {

	public List<Ocupacion> getOcupacionesInRange(Instant fecha_desde, Instant fecha_hasta);

	public void insertarOcupacionyCancelarReservas(Ocupacion ocupacion, List<Reserva> reservas);

}
