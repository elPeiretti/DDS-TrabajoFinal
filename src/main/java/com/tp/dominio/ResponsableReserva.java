package com.tp.dominio;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.responsable_reserva")

public class ResponsableReserva {
	@Column (name = "telefono")
	private String telefono;
	@Column (name = "apellido")
	private String apellido;
	@Column (name = "nombre")
	private String nombre;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_responsable_reserva")
	private Integer idResponsableReserva;
	@OneToMany (mappedBy = "responsable")
	private List<Reserva> reservas;
}
