package com.tp.dominio.reserva;

import java.time.Instant;

import javax.persistence.*;

import com.tp.dominio.habitacion.Habitacion;

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
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_habitacion", referencedColumnName = "id_habitacion")
	private Habitacion habitacion;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_responsable_reserva", referencedColumnName = "id_responsable_reserva")
	private ResponsableReserva responsable;
}
