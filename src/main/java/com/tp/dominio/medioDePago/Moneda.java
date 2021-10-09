package com.tp.dominio.medioDePago;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.moneda")

public class Moneda {
	@Column (name =  "tipo")
	private String tipo;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name =  "id_moneda")
	private Integer idMoneda;
}
