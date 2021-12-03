package com.tp.logica.dominio;

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

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public boolean equals(Object p){
		return p instanceof TipoDocumento && this.idTipoDocumento.equals(((TipoDocumento) p).idTipoDocumento);
	}
	
	
	
	
}
