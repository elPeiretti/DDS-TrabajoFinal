package com.tp.dominio;

import java.util.List;

public class Habitacion {
	private String numbero;
	private EstadoHabitacion estado;
	private TipoHabitacion tipo;
	private String idHabitacion;
	private List<Reserva> reservas;
	private List<Servicio> servicios;
}
