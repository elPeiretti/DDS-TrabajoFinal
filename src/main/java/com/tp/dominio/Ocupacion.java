package com.tp.dominio;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;

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
	@ManyToOne
	@JoinColumn (name = "id_habitacion", referencedColumnName = "id_habitacion")
	private Habitacion habitacion;
	@ManyToOne
	@JoinColumn (name = "id_pasajero_responsable", referencedColumnName = "id_pasajero")
	private Pasajero responsable;
	@ManyToMany
	@JoinTable(
			name = "acompaniante",
			joinColumns =  @JoinColumn(name = "id_ocupacion", referencedColumnName = "id_ocupacion"),
			inverseJoinColumns =  @JoinColumn(name = "id_pasajero", referencedColumnName = "id_pasajero")
	)
	private List<Pasajero> acompaniantes;
}
