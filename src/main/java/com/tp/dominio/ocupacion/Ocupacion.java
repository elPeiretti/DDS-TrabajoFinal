package com.tp.dominio.ocupacion;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.tp.dominio.habitacion.Habitacion;
import com.tp.dominio.pasajero.Pasajero;

@Entity
@Table(name = "tpdds.ocupacion")
//@SecondaryTable(name = "tpdds.acompaniante")
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
	@ManyToMany (cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name = "tpdds.acompaniante",
			joinColumns =  @JoinColumn(name = "id_ocupacion", referencedColumnName = "id_ocupacion"),
			inverseJoinColumns =  @JoinColumn(name = "id_pasajero", referencedColumnName = "id_pasajero")
	)
	private List<Pasajero> acompaniantes;
	public Integer getId() {
		return idOcupacion;
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

	public List<Pasajero> getPasajeros() {
		List<Pasajero> lista = new LinkedList<Pasajero>();
		lista.add(responsable);
		acompaniantes.stream().forEach(a -> lista.add(a));
		return lista;
	}
	
}
