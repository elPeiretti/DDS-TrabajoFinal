package com.tp.dominio;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "tpdds.habitacion")

public class Habitacion {
	@Column (name =  "numero")
	private String numero;
	@Enumerated(EnumType.STRING)
	@Column (name =  "estado")
	private EstadoHabitacion estado;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_tipo_habitacion", referencedColumnName = "id_tipo_habitacion")
	private TipoHabitacion tipo;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name =  "id_habitacion")
	private Integer idHabitacion;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_habitacion", referencedColumnName = "id_habitacion")
	private List<Reserva> reservas;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_habitacion", referencedColumnName = "id_habitacion")
	private List<Servicio> servicios;
}
