package com.tp.logica.dominio;

import java.util.LinkedList;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.tp.interfaz.dto.OcupacionDTO;

@Entity
@Table(name = "tpdds.ocupacion")
//@SecondaryTable(name = "tpdds.acompaniante")
public class Ocupacion {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_ocupacion")
	private Integer idOcupacion;
	@Column (name = "fecha_ingreso")
	private LocalDate fechaIngreso;
	@Column (name = "fecha_egreso")
	private LocalDate fechaEgreso;
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
	public Integer getId() {
		return idOcupacion;
	}
	
	public Ocupacion() {
	}
	
	public Ocupacion(OcupacionDTO ocupacionDto) {
		this.fechaIngreso = ocupacionDto.getFechaIngreso();
		this.fechaEgreso = ocupacionDto.getFechaEgreso();
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}
	public Pasajero getResponsable() {
		return responsable;
	}
	public List<Pasajero> getAcompaniantes() {
		return acompaniantes;
	}
	public List<Pasajero> getPasajeros() {
		List<Pasajero> lista = new LinkedList<Pasajero>();
		lista.add(responsable);
		acompaniantes.stream().forEach(a -> lista.add(a));
		return lista;
	}
	public void setIdOcupacion(Integer idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
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
