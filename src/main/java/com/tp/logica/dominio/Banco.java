package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.banco")

public class Banco {
	@Column (name = "nombre")
	private String nombre;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_banco")
	private Integer idBanco;
}
