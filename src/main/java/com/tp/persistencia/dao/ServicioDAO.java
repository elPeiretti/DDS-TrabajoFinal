package com.tp.persistencia.dao;

import java.util.List;

import com.tp.logica.dominio.Servicio;

public interface ServicioDAO {

    List<Servicio> getServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

    Long getCountServiciosNoFacturadosByIdHabitacion(Integer idHabitacion);

    Servicio getServicioById(Integer idServicio);

}
