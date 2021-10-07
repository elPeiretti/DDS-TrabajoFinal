package com.tp.dominio;

import java.time.Instant;

public class Reserva {
	private Instant fechaIngreso;
	private Instant fechaEgreso;
	private EstadoReserva estado;
	private String idReserva;
	private Habitacion habitacion;
	private ResponsableReserva responsable;
}
