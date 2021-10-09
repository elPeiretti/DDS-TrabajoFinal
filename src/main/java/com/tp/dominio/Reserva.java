package com.tp.dominio;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.reserva")

public class Reserva {
	@Column (name = "fecha_ingreso")
	private Instant fechaIngreso;
	@Column (name = "fecha_egreso")
	private Instant fechaEgreso;
	@Enumerated(EnumType.STRING)
	@Column (name = "estado")
	private EstadoReserva estado;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_reserva")
	private Integer idReserva;
	@ManyToOne
	@JoinColumn (name = "id_habitacion", referencedColumnName = "id_habitacion")
	private Habitacion habitacion;
	@ManyToOne
	@JoinColumn (name = "id_responsable_reserva", referencedColumnName = "id_responsable_reserva")
	private ResponsableReserva responsable;
}
