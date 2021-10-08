package com.tp.dominio;

import javax.persistence.*;

@Entity
@Table (name = "tpdds.tipo_documento")
public class TipoDocumento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_tipo_documento")
	private Integer idTipoDocumento;
	
	@Column (name = "tipo")
	private String tipo;
}
