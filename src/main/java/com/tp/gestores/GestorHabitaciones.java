package com.tp.gestores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.tp.dominio.habitacion.EstadoHabitacion;
import com.tp.dominio.habitacion.Habitacion;
import com.tp.dominio.habitacion.HabitacionDAO;
import com.tp.dominio.habitacion.HabitacionSqlDAO;
import com.tp.dominio.ocupacion.Ocupacion;
import com.tp.dominio.ocupacion.OcupacionDAO;
import com.tp.dominio.ocupacion.OcupacionSqlDAO;
import com.tp.dominio.pasajero.Pasajero;
import com.tp.dominio.pasajero.PasajeroDAO;
import com.tp.dominio.pasajero.PasajeroSqlDAO;
import com.tp.dominio.reserva.EstadoReserva;
import com.tp.dominio.reserva.Reserva;
import com.tp.dominio.reserva.ReservaDAO;
import com.tp.dominio.reserva.ReservaSqlDAO;

import com.tp.dto.FechaDTO;
import com.tp.dto.HabitacionDTO;
import com.tp.dto.OcupacionDTO;
import com.tp.dto.ReservaDTO;

public class GestorHabitaciones {

	public static List<FechaDTO> buscarEstadoHabitaciones(LocalDate fecha_desde, LocalDate fecha_hasta) {
		
		HabitacionDAO daoHabitacion = new HabitacionSqlDAO();
		ReservaDAO daoReserva = new ReservaSqlDAO();
		OcupacionDAO daoOcupacion = new OcupacionSqlDAO();

		List<Habitacion> listaHabitaciones = daoHabitacion.getAllHabitaciones();
		List<Reserva> listaReservas = daoReserva.getReservasInRange(fecha_desde,fecha_hasta);
		List<Ocupacion> listaOcupaciones = daoOcupacion.getOcupacionesInRange(fecha_desde,fecha_hasta);
		
		return convertToEstadoHabitacionDTO(listaHabitaciones, listaReservas, listaOcupaciones, fecha_desde, fecha_hasta);
	}

	private static List<FechaDTO> convertToEstadoHabitacionDTO(List<Habitacion> listaHabitaciones, List<Reserva> listaReservas, List<Ocupacion> listaOcupaciones, LocalDate fecha_desde, LocalDate fecha_hasta) {
		
		LocalDate fechaAux = fecha_desde;
		
		List<FechaDTO> resultado = new ArrayList<FechaDTO>();
				
		while(ChronoUnit.DAYS.between(fechaAux,fecha_hasta) >= 0) {
			Map<String, HabitacionDTO> estadosIniciales = new HashMap<String, HabitacionDTO>();
			
			for(Habitacion h : listaHabitaciones) {
				estadosIniciales.put(h.getNumero(), new HabitacionDTO(h.getNumero(),EstadoHabitacion.LIBRE, h.getTipoHabitacion().getNombre()));
			}
			
			FechaDTO fechaDTO = new FechaDTO(fechaAux, estadosIniciales);
			resultado.add(fechaDTO);
			fechaAux = fechaAux.plus(1, ChronoUnit.DAYS);
		}
		
		for(Reserva r : listaReservas) {
			
			fechaAux = fecha_desde.isBefore(r.getFechaIngreso())? r.getFechaIngreso():fecha_desde.plus(0,ChronoUnit.DAYS);
			int i = (int) ChronoUnit.DAYS.between(fecha_desde,fechaAux);
			while(ChronoUnit.DAYS.between(fechaAux,r.getFechaEgreso()) >= 0 && !(fechaAux.isAfter(fecha_hasta))) {
				Map<String, HabitacionDTO> habitaciones = resultado.get(i++).getHabitaciones();
				habitaciones.get(r.getHabitacion().getNumero()).setEstado(EstadoHabitacion.RESERVADA);
				fechaAux = fechaAux.plus(1, ChronoUnit.DAYS);
			}
		}
		
		for(Ocupacion o : listaOcupaciones) {
			
			fechaAux = fecha_desde.isBefore(o.getFechaIngreso())? o.getFechaIngreso():fecha_desde.plus(0,ChronoUnit.DAYS);
			int i = (int) ChronoUnit.DAYS.between(fecha_desde,fechaAux);
			while(ChronoUnit.DAYS.between(fechaAux,o.getFechaEgreso()) >= 0 && !(fechaAux.isAfter(fecha_hasta))) {
				Map<String, HabitacionDTO> habitaciones = resultado.get(i++).getHabitaciones();
				habitaciones.get(o.getHabitacion().getNumero()).setEstado(EstadoHabitacion.OCUPADA);
				fechaAux = fechaAux.plus(1, ChronoUnit.DAYS);
			}
		}
			
		return resultado;
	}

    public static List<ReservaDTO> getReservasVigentesInRange(LocalDate fechaInicio, LocalDate fechaFin, String numeroHabitacion) {
		
		ReservaDAO daoReserva = new ReservaSqlDAO();
		List<Reserva> listaReservas = daoReserva.getReservasInRange(fechaInicio,fechaFin,numeroHabitacion);
		List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();

		for (Reserva r: listaReservas){
			reservas.add(new ReservaDTO(r));
		}
        return reservas;
    }

    public static HabitacionDTO getHabitacionByNumero(String numero) {
        HabitacionDAO hDao= new HabitacionSqlDAO();
		return new HabitacionDTO(hDao.getHabitacionByNumero(numero));
    }
    
    public static void ocuparHabitacion(OcupacionDTO ocupacionDto) {
    	
    	HabitacionDAO habitacionDao = new HabitacionSqlDAO();
    	PasajeroDAO pasajeroDao = new PasajeroSqlDAO();
    	OcupacionDAO ocupacionDao = new OcupacionSqlDAO();
    	ReservaDAO reservaDao = new ReservaSqlDAO();
    	
    	Habitacion habitacion = habitacionDao.getHabitacionByNumero(ocupacionDto.getHabitacion().getNumero());
    	
    	if(ocupacionDto.getFechaIngreso().equals(LocalDate.now())) {
    		habitacion.setEstado(EstadoHabitacion.OCUPADA);
    	}
    	
    	List<Integer> idPasajeros = ocupacionDto.getAcompaniantes().stream().map(p -> p.getIdPasajero()).collect(Collectors.toList());
    	idPasajeros.add(ocupacionDto.getResponsable().getIdPasajero());
    	 
    	List<Pasajero> resultado = pasajeroDao.getPasajerosById(idPasajeros);
    	
		List<Pasajero> acompaniantes = new ArrayList<Pasajero>();
		Pasajero responsable = null;
		for (Pasajero p : resultado){
			if (p.getIdPasajero().equals(ocupacionDto.getResponsable().getIdPasajero()))
				responsable = p;
			else{
				acompaniantes.add(p);
			}
		}

    	//Pasajero responsable = pasajeroDao.getPasajeroById(ocupacionDto.getResponsable().getIdPasajero());
    	
    	//List<Pasajero> acompaniantes = pasajeroDao.getPasajerosById(ocupacionDto.getAcompaniantes().stream().map(p -> p.getIdPasajero()).collect(Collectors.toList()));
    	/*
    	for(Pasajero a : acompaniantes) {
    		if(a.getPosicionIVA().equals(responsable.getPosicionIVA())) a.setPosicionIVA(responsable.getPosicionIVA());
    		if(a.getNacionalidad().equals(responsable.getNacionalidad())) a.setNacionalidad(responsable.getNacionalidad());
    		if(a.getTipoDocumento().equals(responsable.getTipoDocumento())) a.setTipoDocumento(responsable.getTipoDocumento());
    		if(a.getDireccion().getCiudad().equals(responsable.getDireccion().getCiudad())) a.getDireccion().setCiudad(responsable.getDireccion().getCiudad());
    	}*/
    	
    	Ocupacion ocupacion = new Ocupacion(ocupacionDto);
    	
    	ocupacion.setHabitacion(habitacion);
    	
    	ocupacion.setResponsable(responsable);
    	
    	ocupacion.setAcompaniantes(acompaniantes);
    	
    	List<Reserva> listaReservas = reservaDao.getReservasInRange(ocupacion.getFechaIngreso(), ocupacion.getFechaEgreso(), ocupacion.getHabitacion().getNumero());
    	
    	listaReservas.stream().forEach(r -> r.setEstado(EstadoReserva.CANCELADA));
    	
    	listaReservas.stream().forEach(r -> r.setHabitacion(ocupacion.getHabitacion()));
    	
    	ocupacionDao.insertarOcupacionyCancelarReservas(ocupacion, listaReservas);
    	
    }
	
}
