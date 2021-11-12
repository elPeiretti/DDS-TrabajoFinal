package com.tp.gestores;

import java.time.Instant;
import java.util.List;

import com.tp.dominio.habitacion.Habitacion;
import com.tp.dominio.habitacion.HabitacionDAO;
import com.tp.dominio.habitacion.HabitacionSqlDAO;
import com.tp.dominio.ocupacion.Ocupacion;
import com.tp.dominio.ocupacion.OcupacionDAO;
import com.tp.dominio.ocupacion.OcupacionSqlDAO;
import com.tp.dominio.reserva.Reserva;
import com.tp.dominio.reserva.ReservaDAO;
import com.tp.dominio.reserva.ReservaSqlDAO;
import com.tp.dto.EstadoHabitacionDTO;

public class GestorHabitaciones {

	public static List<EstadoHabitacionDTO> buscarEstadoHabitaciones(Instant fecha_desde, Instant fecha_hasta) {
		
		HabitacionDAO daoHabitacion = new HabitacionSqlDAO();
		ReservaDAO daoReserva = new ReservaSqlDAO();
		OcupacionDAO daoOcupacion = new OcupacionSqlDAO();
		
		List<Habitacion> listaHabitaciones = daoHabitacion.getAllHabitaciones();
		List<Reserva> listaReservas = daoReserva.getReservasInRange(fecha_desde,fecha_hasta);
		List<Ocupacion> listaOcupaciones = daoOcupacion.getOcupacionesInRange(fecha_desde,fecha_hasta);
		
		return convertToEstadoHabitacionDTO(listaHabitaciones, listaReservas, listaOcupaciones);
	}

	private static List<EstadoHabitacionDTO> convertToEstadoHabitacionDTO(List<Habitacion> listaHabitaciones,
			List<Reserva> listaReservas, List<Ocupacion> listaOcupaciones) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
