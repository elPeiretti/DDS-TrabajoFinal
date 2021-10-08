package com.tp.dominio;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.servicio")
public class Servicio {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_servicio")
	private Integer idServicio;
	@Column (name = "fecha_realizacion")
	private Instant fechaDeRealizacion;
	@Column (name = "precio_unitario")
	private Double precioUnitario;
	@Column (name = "cantidad")
	private Integer cantidad;
	@Column (name = "descripcion")
	private String descripcion;
	@Column (name = "cantidad_pagada")
	private Integer cantidadPagada;
}
