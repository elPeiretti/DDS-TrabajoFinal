package com.tp.dominio.factura.items;

import java.util.List;

public interface ServicioDAO {

    List<Servicio> getServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

    Long getCountServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

    Servicio getServicioById(Integer idServicio);

}
