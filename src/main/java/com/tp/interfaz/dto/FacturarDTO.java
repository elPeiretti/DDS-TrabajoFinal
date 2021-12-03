package com.tp.interfaz.dto;

import javax.swing.SortOrder;

public class FacturarDTO {
	public enum columnaOrden {NOMBRES,APELLIDO,NRODOC,TIPODOC}
	private columnaOrden columna;
	private SortOrder sortOrder;
	private String numeroHabitacion;
	private OcupacionDTO ocupacion;
	private String horaSalida;
	private ResponsablePagoTerceroDTO responsable;
	public FacturarDTO() {
		setColumna(columnaOrden.APELLIDO);
		setSortOrder(SortOrder.ASCENDING);
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public columnaOrden getColumna() {
		return columna;
	}

	public void setColumna(columnaOrden columna) {
		this.columna = columna;
	}
	public OcupacionDTO getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(OcupacionDTO ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String hora_salida) {
		this.horaSalida = hora_salida;
	}
	public ResponsablePagoTerceroDTO getResponsable() {
		return responsable;
	}
	public void setResponsable(ResponsablePagoTerceroDTO responsable) {
		this.responsable = responsable;
	}
	public String getNumeroHabitacion() {
		return numeroHabitacion;
	}
	public void setNumeroHabitacion(String idHabitacion) {
		this.numeroHabitacion = idHabitacion;
	}
}
