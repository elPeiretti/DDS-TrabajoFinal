package com.tp.dominio.pasajero;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.posicion_iva")

public class PosicionIVA {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_posicion_iva")
	private Integer idPosicionIVA;
	@Column (name = "posicion")
	private String posicion;
}
