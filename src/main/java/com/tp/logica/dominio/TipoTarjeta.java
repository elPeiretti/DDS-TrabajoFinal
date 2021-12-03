package com.tp.logica.dominio;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.tipo_tarjeta")

public class TipoTarjeta {
	@Column (name = "tipo")
	private String tipo;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_tipo_tarjeta")
	private Integer idTipoTarjeta;
}
