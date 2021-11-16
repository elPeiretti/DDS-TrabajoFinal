package com.tp.dominio.habitacion;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.tipo_habitacion")
public class TipoHabitacion {
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "capacidad")
	private Integer capacidad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_tipo_habitacion")
	private Integer idTipoHabitacion;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_tipo_habitacion", referencedColumnName = "id_tipo_habitacion")
	private Collection<CostoPorNoche> costo;

	public String getNombre(){
		return nombre;
	}
}
