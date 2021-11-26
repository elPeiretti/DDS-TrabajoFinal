package com.tp.gestores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.SortOrder;

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
import com.tp.dto.BusqPasajeroDTO;
import com.tp.dto.FacturarDTO;
import com.tp.dto.FechaDTO;
import com.tp.dto.HabitacionDTO;
import com.tp.dto.PasajeroDTO;
import com.tp.dto.OcupacionDTO;
import com.tp.dto.ReservaDTO;
import com.tp.dto.TipoHabitacionDTO;
import com.tp.excepciones.HabitacionNoExistenteException;
import com.tp.excepciones.HabitacionNoOcupadaException;
import com.tp.excepciones.HabitacionSinOcupacionesException;
import com.tp.interfaces.misc.Mensaje;

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

	public static List<PasajeroDTO> ordenarLista(List<PasajeroDTO> lista, FacturarDTO criterios){
		Comparator<PasajeroDTO> comp;
		switch(criterios.getColumna()) {
		case NOMBRES:
			comp = (p1,p2) -> p1.getNombres().compareTo(p2.getNombres());
			break;
		case APELLIDO:
			comp = (p1,p2) -> p1.getApellido().compareTo(p2.getApellido());
			break;
		case NRODOC:
			comp = (p1,p2) -> p1.getNroDocumento().compareTo(p2.getNroDocumento());
			break;
		case TIPODOC:
			comp = (p1,p2) -> p1.getTipoDocumentoDTO().getTipo().compareTo(p2.getTipoDocumentoDTO().getTipo());
			break;
		default:
			comp = (p1,p2) -> p1.getNombres().compareTo(p2.getNombres());
		}
		if(criterios.getSortOrder().equals(SortOrder.DESCENDING)) comp = comp.reversed();
		lista.sort(comp);
		return lista;
	}
	
    public static HabitacionDTO getHabitacionByNumero(String numero) throws HabitacionNoExistenteException {
        HabitacionDAO hDao= new HabitacionSqlDAO();
		Habitacion h = hDao.getHabitacionByNumero(numero);
		if(h==null){
			throw new HabitacionNoExistenteException();
		}
		TipoHabitacionDTO tipoDto = new TipoHabitacionDTO(h.getTipoHabitacion().getNombre(), h.getTipoHabitacion().getCapacidad());
		HabitacionDTO hDto = new HabitacionDTO(numero, h.getEstado(), h.getTipoHabitacion().getNombre(), tipoDto, h.getIdHabitacion());
		
		return hDto;
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

	public static void cargarOcupacionActual(FacturarDTO criterios_actuales) throws HabitacionNoExistenteException, HabitacionSinOcupacionesException, HabitacionNoOcupadaException {
		HabitacionDAO habitacionDAO = new HabitacionSqlDAO();
		
		if(habitacionDAO.getHabitacionByNumero(criterios_actuales.getHabitacion()) == null){
			throw new HabitacionNoExistenteException();
		}

		OcupacionDAO ocupacionDAO = new OcupacionSqlDAO();
		Ocupacion oc = ocupacionDAO.getUltimaOcupacion(criterios_actuales.getHabitacion());
		if(oc == null) {
			throw new HabitacionSinOcupacionesException();
		}
		if(oc.getHabitacion().getEstado() != EstadoHabitacion.OCUPADA) {
			throw new HabitacionNoOcupadaException();
		}
		criterios_actuales.setIdOcupacion(oc.getId());
		criterios_actuales.setCantOcupantes(oc.getPasajeros().size());
	}

	public static List<PasajeroDTO> getOcupantesBy(FacturarDTO criterios_actuales, Integer li, Integer cant) {
		OcupacionDAO ocupacionDAO = new OcupacionSqlDAO();
		Ocupacion oc = ocupacionDAO.getOcupacionById(criterios_actuales.getIdOcupacion());
		List<Pasajero> ocupantes = oc.getPasajeros();
		List<PasajeroDTO> ocupantesDTO = ordenarLista(GestorPasajeros.convertToPasajeroDTO(ocupantes),criterios_actuales);
		Integer ult = (ocupantesDTO.size() < 8 + li) ? ocupantesDTO.size() : 8 + li;
		return ocupantesDTO.subList(li, ult);
	}
	
}
