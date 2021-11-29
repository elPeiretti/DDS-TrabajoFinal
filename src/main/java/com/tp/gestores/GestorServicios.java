package com.tp.gestores;

import java.util.ArrayList;
import java.util.List;

import com.tp.dominio.factura.items.Servicio;
import com.tp.dominio.factura.items.ServicioDAO;
import com.tp.dominio.factura.items.ServicioSqlDAO;
import com.tp.dto.HabitacionDTO;
import com.tp.dto.ServicioDTO;

public class GestorServicios {
    public static List<ServicioDTO> getServiciosNoFacturadosByHabitacion(HabitacionDTO habitacionDto, Integer li, Integer cant) {
        ServicioDAO sDao = new ServicioSqlDAO();
        List<Servicio> servicios = sDao.getServiciosNoFacturadosByIdHabitacion(habitacionDto.getIdHabitacion(),li,cant);
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
}
