package com.tp.logica.dominio;

import java.util.List;

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
	private List<CostoPorNoche> costo;

	public List<CostoPorNoche> getCosto() {
		return costo;
	}
	public void setCosto(List<CostoPorNoche> costo) {
		this.costo = costo;
	}
	public String getNombre(){
		return nombre;
	}
    public Integer getCapacidad() {
        return capacidad;
    }
}
