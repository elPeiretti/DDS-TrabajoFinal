package com.tp.dominio.factura.items;

import java.util.List;

import com.tp.dto.ServicioDTO;

public interface ServicioDAO {

    List<Servicio> getServiciosNoFacturadosByIdHabitacion(Integer idHabitacion, Integer li, Integer cant);

    Long getCountServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

}
