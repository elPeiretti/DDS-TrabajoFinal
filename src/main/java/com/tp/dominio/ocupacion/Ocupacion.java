package com.tp.dominio.ocupacion;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;

import com.tp.dominio.habitacion.Habitacion;
import com.tp.dominio.pasajero.Pasajero;
import com.tp.dto.OcupacionDTO;

@Entity
@Table(name = "tpdds.ocupacion")

public class Ocupacion {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_ocupacion")
	private Integer idOcupacion;
	@Column (name = "fecha_ingreso")
	private Instant fechaIngreso;
	@Column (name = "fecha_egreso")
	private Instant fechaEgreso;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_habitacion", referencedColumnName = "id_habitacion")
	private Habitacion habitacion;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_pasajero_responsable", referencedColumnName = "id_pasajero")
	private Pasajero responsable;
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "tpdds.acompaniante",
			joinColumns =  @JoinColumn(name = "id_ocupacion", referencedColumnName = "id_ocupacion"),
			inverseJoinColumns =  @JoinColumn(name = "id_pasajero", referencedColumnName = "id_pasajero")
	)
	private List<Pasajero> acompaniantes;
	
	public Ocupacion() {
	}
	
	public Ocupacion(OcupacionDTO ocupacionDto) {
		this.fechaIngreso = ocupacionDto.getFechaIngreso();
		this.fechaEgreso = ocupacionDto.getFechaEgreso();
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public Instant getFechaIngreso() {
		return fechaIngreso;
	}

	public Instant getFechaEgreso() {
		return fechaEgreso;
	}

	public void setIdOcupacion(Integer idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public void setFechaIngreso(Instant fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setFechaEgreso(Instant fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public void setResponsable(Pasajero responsable) {
		this.responsable = responsable;
	}

	public void setAcompaniantes(List<Pasajero> acompaniantes) {
		this.acompaniantes = acompaniantes;
	}
	
}
