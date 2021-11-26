package com.tp.dominio.ocupacion;

import java.time.LocalDate;
import java.util.List;

import com.tp.dominio.pasajero.Pasajero;
import com.tp.dominio.reserva.Reserva;
import com.tp.dto.FacturarDTO;

public interface OcupacionDAO {

	public List<Ocupacion> getOcupacionesInRange(LocalDate fecha_desde, LocalDate fecha_hasta);

	public void insertarOcupacionyCancelarReservas(Ocupacion ocupacion, List<Reserva> reservas);

	public Ocupacion getUltimaOcupacion(String habitacion);
	public Ocupacion getOcupacionById(Integer idOcupacion);

}
