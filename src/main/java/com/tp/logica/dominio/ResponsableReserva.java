package com.tp.logica.dominio;

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
	@OneToMany (mappedBy = "responsable", cascade = CascadeType.ALL) 
	private List<Reserva> reservas;
	
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
}
