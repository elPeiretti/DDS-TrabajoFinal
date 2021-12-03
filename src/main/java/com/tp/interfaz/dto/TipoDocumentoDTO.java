package com.tp.interfaz.dto;

public class TipoDocumentoDTO {

	private Integer idTipoDocumento;
	private String tipo;
	
	
	
	public TipoDocumentoDTO(Integer idTipoDocumento, String tipo) {
		this.idTipoDocumento = idTipoDocumento;
		this.tipo = tipo;
	}
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String nombre) {
		this.tipo = nombre;
	}
	
	public String toString() {
		return tipo;
	}
	
}
