package com.tp.gestores;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.tp.dominio.factura.items.Servicio;
import com.tp.dominio.factura.items.ServicioDAO;
import com.tp.dominio.factura.items.ServicioSqlDAO;
import com.tp.dto.HabitacionDTO;
import com.tp.dto.ServicioDTO;
import com.tp.dominio.factura.items.Servicio;
import com.tp.dominio.habitacion.Habitacion;
import com.tp.dto.OcupacionDTO;

public class GestorServicios {
	private String warning;//dejo esto para recordar que hay que revisar el calculo de la duracion de la estadia
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

	public static Servicio generarServicioRecargo(Habitacion hab,OcupacionDTO ocupacion_actual) {
		Servicio recargo = new Servicio();
		recargo.setCantidad(1);
		recargo.setCantidadPagada(0);
		recargo.setDescripcion("Recargo por salida tardía de habitación: "+hab.getNumero());
		recargo.setFechaDeRealizacion(LocalDate.now());
		recargo.setPrecioUnitario(0.5*calcularCostoEstadia(hab.getTipoHabitacion().getCosto().get(0).getCosto(),ocupacion_actual));
		return recargo;
	}
	private static Double calcularCostoEstadia(Double costoPorNoche,OcupacionDTO ocupacion) {
		return costoPorNoche*ocupacion.getFechaIngreso().until(ocupacion.getFechaEgreso(),ChronoUnit.DAYS);
	}
}
