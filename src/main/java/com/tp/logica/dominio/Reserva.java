package com.tp.logica.dominio;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.reserva")

public class Reserva {
	@Column (name = "fecha_ingreso")
	private LocalDate fechaIngreso;
	@Column (name = "fecha_egreso")
	private LocalDate fechaEgreso;
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
	
	
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
    public Integer getIdReserva() {
        return idReserva;
    }
    public ResponsableReserva getResponsable() {
        return responsable;
    }
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	
	
}
