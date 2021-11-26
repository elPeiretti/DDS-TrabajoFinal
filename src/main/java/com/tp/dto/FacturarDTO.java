package com.tp.dto;

import javax.swing.SortOrder;

public class FacturarDTO {
	public enum columnaOrden {NOMBRES,APELLIDO,NRODOC,TIPODOC}
	private columnaOrden columna;
	private SortOrder sortOrder;
	private String habitacion;
	private Integer idOcupacion;
	private Integer cantOcupantes;
	public FacturarDTO() {
		setColumna(columnaOrden.APELLIDO);
		setSortOrder(SortOrder.ASCENDING);
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public Integer getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(Integer idOcupacion) {
		this.idOcupacion = idOcupacion;
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

	public Integer getCantOcupantes() {
		return cantOcupantes;
	}

	public void setCantOcupantes(Integer cantOcupantes) {
		this.cantOcupantes = cantOcupantes;
	}
}
