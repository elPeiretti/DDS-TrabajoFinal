package com.tp.persistencia.dao;

import java.time.LocalDate;
import java.util.List;

import com.tp.logica.dominio.Ocupacion;
import com.tp.logica.dominio.Reserva;
import com.tp.logica.excepciones.NuevaOcupacionException;

public interface OcupacionDAO {

	public List<Ocupacion> getOcupacionesInRange(LocalDate fecha_desde, LocalDate fecha_hasta);

	public void insertarOcupacionyCancelarReservas(Ocupacion ocupacion, List<Reserva> reservas) throws NuevaOcupacionException;

	public Ocupacion getUltimaOcupacion(String habitacion);
	public Ocupacion getOcupacionById(Integer idOcupacion);

    public void insertarOcupacion(Ocupacion ocup);

}
