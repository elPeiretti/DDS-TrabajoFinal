package com.tp.logica.dominio;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.servicio")
public class Servicio {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_servicio")
	private Integer idServicio;
	@Column (name = "fecha_realizacion")
	private LocalDate fechaDeRealizacion;
	@Column (name = "precio_unitario")
	private Double precioUnitario;
	@Column (name = "cantidad")
	private Integer cantidad;
	@Column (name = "descripcion")
	private String descripcion;
	@Column (name = "cantidad_pagada")
	private Integer cantidadPagada;
	
	public Integer getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}
	public LocalDate getFechaDeRealizacion() {
		return fechaDeRealizacion;
	}
	public void setFechaDeRealizacion(LocalDate fechaDeRealizacion) {
		this.fechaDeRealizacion = fechaDeRealizacion;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantidadPagada() {
		return cantidadPagada;
	}
	public void setCantidadPagada(Integer cantidadPagada) {
		this.cantidadPagada = cantidadPagada;
	}
    public void aumentarCantidadPagada(Integer cantidad) {
		this.cantidadPagada+=cantidad;
    }

	
}
