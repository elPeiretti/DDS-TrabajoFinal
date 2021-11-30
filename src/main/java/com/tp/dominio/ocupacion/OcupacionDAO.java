package com.tp.dominio.ocupacion;

import java.time.LocalDate;
import java.util.List;

import com.tp.dominio.reserva.Reserva;
import com.tp.excepciones.NuevaOcupacionException;

public interface OcupacionDAO {

	public List<Ocupacion> getOcupacionesInRange(LocalDate fecha_desde, LocalDate fecha_hasta);

	public void insertarOcupacionyCancelarReservas(Ocupacion ocupacion, List<Reserva> reservas) throws NuevaOcupacionException;

	public Ocupacion getUltimaOcupacion(String habitacion);
	public Ocupacion getOcupacionById(Integer idOcupacion);

}
