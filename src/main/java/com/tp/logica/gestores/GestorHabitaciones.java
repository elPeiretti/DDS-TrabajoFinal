package com.tp.logica.gestores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.SortOrder;

import com.tp.interfaz.dto.FacturarDTO;
import com.tp.interfaz.dto.FechaDTO;
import com.tp.interfaz.dto.HabitacionDTO;
import com.tp.interfaz.dto.OcupacionDTO;
import com.tp.interfaz.dto.PasajeroDTO;
import com.tp.interfaz.dto.ReservaDTO;
import com.tp.interfaz.dto.TipoHabitacionDTO;
import com.tp.logica.dominio.EstadoHabitacion;
import com.tp.logica.dominio.EstadoReserva;
import com.tp.logica.dominio.Habitacion;
import com.tp.logica.dominio.Ocupacion;
import com.tp.logica.dominio.Pasajero;
import com.tp.logica.dominio.Reserva;
import com.tp.logica.dominio.Servicio;
import com.tp.logica.excepciones.HabitacionNoExistenteException;
import com.tp.logica.excepciones.HabitacionSinOcupacionesException;
import com.tp.logica.excepciones.NuevaHabitacionException;
import com.tp.logica.excepciones.NuevaOcupacionException;
import com.tp.logica.excepciones.OcupacionNoCheckoutableException;
import com.tp.persistencia.dao.HabitacionDAO;
import com.tp.persistencia.dao.HabitacionSqlDAO;
import com.tp.persistencia.dao.OcupacionDAO;
import com.tp.persistencia.dao.OcupacionSqlDAO;
import com.tp.persistencia.dao.PasajeroDAO;
import com.tp.persistencia.dao.PasajeroSqlDAO;
import com.tp.persistencia.dao.ReservaDAO;
import com.tp.persistencia.dao.ReservaSqlDAO;

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
				EstadoHabitacion estado = h.getEstado().equals(EstadoHabitacion.MANTENIMIENTO)? EstadoHabitacion.MANTENIMIENTO : EstadoHabitacion.LIBRE;
				estadosIniciales.put(h.getNumero(), new HabitacionDTO(h.getNumero(), estado, h.getTipoHabitacion().getNombre()));
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
				if(!habitaciones.get(r.getHabitacion().getNumero()).getEstado().equals(EstadoHabitacion.MANTENIMIENTO))
					habitaciones.get(r.getHabitacion().getNumero()).setEstado(EstadoHabitacion.RESERVADA);

				fechaAux = fechaAux.plus(1, ChronoUnit.DAYS);
			}
		}
		
		for(Ocupacion o : listaOcupaciones) {

			fechaAux = fecha_desde.isBefore(o.getFechaIngreso())? o.getFechaIngreso():fecha_desde.plus(0,ChronoUnit.DAYS);
			int i = (int) ChronoUnit.DAYS.between(fecha_desde,fechaAux);
			while(ChronoUnit.DAYS.between(fechaAux,o.getFechaEgreso()) >= 0 && !(fechaAux.isAfter(fecha_hasta))) {
				Map<String, HabitacionDTO> habitaciones = resultado.get(i++).getHabitaciones();
				if(!habitaciones.get(o.getHabitacion().getNumero()).getEstado().equals(EstadoHabitacion.MANTENIMIENTO))
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
    
    public static void ocuparHabitacion(OcupacionDTO ocupacionDto) throws NuevaOcupacionException {
    	
    	HabitacionDAO habitacionDao = new HabitacionSqlDAO();
    	PasajeroDAO pasajeroDao = new PasajeroSqlDAO();
    	OcupacionDAO ocupacionDao = new OcupacionSqlDAO();
    	ReservaDAO reservaDao = new ReservaSqlDAO();
    	
    	Habitacion habitacion = habitacionDao.getHabitacionByNumero(ocupacionDto.getHabitacion().getNumero());
    	habitacion.setEstado(EstadoHabitacion.OCUPADA);
    	
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

		// lo de arriba hace lo mismo, pero permite ahorrarse el problema de tener multiples instancias de un mismo Pais, Provincia, Ciudad, PosicionIVA o TipoDocumento
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
    	try {
    		ocupacionDao.insertarOcupacionyCancelarReservas(ocupacion, listaReservas);
    	}catch(NuevaOcupacionException e) {
			throw e;
    	}
    }

	public static OcupacionDTO buscarUltimaOcupacion(String numero) throws HabitacionNoExistenteException, HabitacionSinOcupacionesException, OcupacionNoCheckoutableException {
		HabitacionDAO habitacionDAO = new HabitacionSqlDAO();
		
		if(habitacionDAO.getHabitacionByNumero(numero) == null){
			throw new HabitacionNoExistenteException();
		}

		OcupacionDAO ocupacionDAO = new OcupacionSqlDAO();
		Ocupacion oc = ocupacionDAO.getUltimaOcupacion(numero);
		if(oc == null) {
			throw new HabitacionSinOcupacionesException();
		}

		/*LocalDate fecha_checkout = oc.getFechaEgreso().plusDays(1);
		LocalDate fecha_ingreso = oc.getFechaIngreso();
		LocalDate hoy = LocalDate.now();

		if(!(hoy.isAfter(fecha_ingreso.minusDays(1)) && hoy.isBefore(fecha_checkout.plusDays(1)))){*/
		if(!oc.getHabitacion().getEstado().equals(EstadoHabitacion.OCUPADA)){
			throw new OcupacionNoCheckoutableException();
		}
		
		return convertToOcupacionDTO(oc);
	}

	private static OcupacionDTO convertToOcupacionDTO(Ocupacion oc) {
		OcupacionDTO ocDTO = new OcupacionDTO();
		ocDTO.setIdOcupacion(oc.getId());
		ocDTO.setFechaEgreso(oc.getFechaEgreso());
		ocDTO.setFechaIngreso(oc.getFechaIngreso());
		ocDTO.setResponsable(GestorPasajeros.convertToPasajeroDTO(List.of(oc.getResponsable())).get(0));
		ocDTO.setAcompaniantes(GestorPasajeros.convertToPasajeroDTO(oc.getAcompaniantes()));
		ocDTO.setHabitacion(convertToHabitacionDTO(oc.getHabitacion()));
		return ocDTO;
	}

	private static HabitacionDTO convertToHabitacionDTO(Habitacion habitacion) {
		HabitacionDTO habDTO = new HabitacionDTO();
		habDTO.setIdHabitacion(habitacion.getIdHabitacion());
		habDTO.setEstado(habitacion.getEstado());
		habDTO.setNumero(habitacion.getNumero());
		return habDTO;
	}
	public static void calcularEstadia(String hora_salida, OcupacionDTO ocupacion_actual) throws NuevaHabitacionException {
		HabitacionDAO habDao = new HabitacionSqlDAO();
		Habitacion hab = habDao.getHabitacionByIdWithCostoVigenteEn(ocupacion_actual.getHabitacion().getIdHabitacion(),ocupacion_actual.getFechaIngreso());
		Servicio estadia = GestorServicios.generarServicioEstadia(hab,ocupacion_actual);
		hab.addServicio(estadia);
		LocalTime localTimeSalida = LocalTime.parse(hora_salida, DateTimeFormatter.ofPattern("HH:mm"));
		if(localTimeSalida.isAfter(LocalTime.of(11, 0))) {
			Servicio recargo = GestorServicios.generarServicioRecargo(hab,localTimeSalida);
			hab.addServicio(recargo);
		}
		
		try {
			HabitacionDAO hDao = new HabitacionSqlDAO();
			hDao.insertarHabitacion(hab);
		}catch(NuevaHabitacionException e) {
			throw e;
		}
		
	}

	public static void liberarHabitacion(HabitacionDTO habitacion) {
		OcupacionDAO oDao = new OcupacionSqlDAO();
		Ocupacion ocup = oDao.getUltimaOcupacion(habitacion.getNumero());

		LocalDate fecha_checkout = ocup.getFechaEgreso().plusDays(1);
		LocalDate hoy = LocalDate.now();

		if(hoy.isBefore(fecha_checkout)){
			ocup.setFechaEgreso(hoy.minusDays(1).isBefore(ocup.getFechaIngreso())? ocup.getFechaIngreso() : hoy.minusDays(1));
		}

		ReservaDAO rDao = new ReservaSqlDAO();
		if(rDao.getReservasInRange(hoy, hoy, habitacion.getNumero()).size()!=0){
			ocup.getHabitacion().setEstado(EstadoHabitacion.RESERVADA);
		}
		else{
			ocup.getHabitacion().setEstado(EstadoHabitacion.LIBRE);
		}

		oDao.insertarOcupacion(ocup);
		
	}
	
}
