package com.tp.dominio.factura.items;

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
}
