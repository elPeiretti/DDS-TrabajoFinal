package com.tp.logica.gestores;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.tp.interfaz.dto.HabitacionDTO;
import com.tp.interfaz.dto.OcupacionDTO;
import com.tp.interfaz.dto.ServicioDTO;
import com.tp.logica.dominio.Habitacion;
import com.tp.logica.dominio.Servicio;
import com.tp.persistencia.dao.ServicioDAO;
import com.tp.persistencia.dao.ServicioSqlDAO;

public class GestorServicios {
    public static List<ServicioDTO> getServiciosNoFacturadosByHabitacion(HabitacionDTO habitacionDto) {
        ServicioDAO sDao = new ServicioSqlDAO();
        List<Servicio> servicios = sDao.getServiciosNoFacturadosByIdHabitacion(habitacionDto.getIdHabitacion());
        List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
        for(Servicio s : servicios){
            serviciosDTO.add(new ServicioDTO(s.getIdServicio(), s.getDescripcion(), s.getPrecioUnitario(), s.getCantidad(), s.getCantidadPagada()));
        }
		return serviciosDTO;
    }

    public static Long getCantServiciosNoFacturadosByHabitacion(HabitacionDTO habitacionDto) {
        ServicioDAO sDao = new ServicioSqlDAO();
        return sDao.getCountServiciosNoFacturadosByIdHabitacion(habitacionDto.getIdHabitacion());
    }

    public static Servicio getServicioById(Integer idServicio) {
        ServicioDAO sDao = new ServicioSqlDAO();
        return sDao.getServicioById(idServicio);
    }

	public static Servicio generarServicioEstadia(Habitacion hab, OcupacionDTO ocupacion_actual) {
		Servicio estadia = new Servicio();
		estadia.setCantidad(1);
		estadia.setCantidadPagada(0);
		estadia.setDescripcion("Estadía en habitación: "+hab.getNumero());
		estadia.setFechaDeRealizacion(ocupacion_actual.getFechaIngreso());
		estadia.setPrecioUnitario(calcularCostoEstadia(hab.getTipoHabitacion().getCosto().get(0).getCosto(),ocupacion_actual));
		return estadia;
	}

	public static Servicio generarServicioRecargo(Habitacion hab, LocalTime hora_salida) {
		Servicio recargo = new Servicio();
		recargo.setCantidad(1);
		recargo.setCantidadPagada(0);
		recargo.setDescripcion("Recargo por salida tardía de habitación: "+hab.getNumero());
		recargo.setFechaDeRealizacion(LocalDate.now());

		if(hora_salida.isAfter(LocalTime.of(18, 0))) // pasadas las 18 se cobra el dia completo
			recargo.setPrecioUnitario(hab.getTipoHabitacion().getCosto().get(0).getCosto());
		else{ // si se van ente las 11.01 y las 18 solo se les cobra el 50% del dia
			recargo.setPrecioUnitario(0.5*hab.getTipoHabitacion().getCosto().get(0).getCosto());
		}
		return recargo;
	}
	private static Double calcularCostoEstadia(Double costoPorNoche,OcupacionDTO ocupacion) {
		return costoPorNoche*(ocupacion.getFechaIngreso().until(ocupacion.getFechaEgreso(),ChronoUnit.DAYS)+1);
	}
}
