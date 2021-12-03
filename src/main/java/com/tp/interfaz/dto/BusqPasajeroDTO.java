package com.tp.interfaz.dto;

import javax.swing.SortOrder;

public class BusqPasajeroDTO {
	//* atributos copiados de PasajeroDTO
	//private Integer idPasajero;
	private String nombres;
	private String apellido;
	//private String cuit;
	private String nroDocumento;
	//private LocalDate fechaDeNacimiento;
	//private Integer nacionalidad; 
	//private String email;
	//private String telefono;
	//private String ocupacion;
	private TipoDocumentoDTO tipoDocumentoDTO;
	//private Integer idPosicionIVA;
	//private DireccionDTO direccionDTO; 
	//*
	public enum columnaOrden {NOMBRES,APELLIDO,NRODOC,TIPODOC}
	private columnaOrden columna;
	private SortOrder sortOrder;
	
	public BusqPasajeroDTO() {
		columna = columnaOrden.APELLIDO;
		sortOrder = SortOrder.ASCENDING;
	}
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public TipoDocumentoDTO getTipoDocumentoDTO() {
		return tipoDocumentoDTO;
	}
	public void setTipoDocumentoDTO(TipoDocumentoDTO tipoDocumentoDTO) {
		this.tipoDocumentoDTO = tipoDocumentoDTO;
	}
	public columnaOrden getColumna() {
		return columna;
	}
	public void setColumna(columnaOrden orden) {
		this.columna = orden;
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
