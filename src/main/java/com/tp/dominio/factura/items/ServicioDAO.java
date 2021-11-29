package com.tp.dominio.factura.items;

import java.util.List;

import com.tp.dto.ServicioDTO;

public interface ServicioDAO {

    List<Servicio> getServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

    Long getCountServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

}
