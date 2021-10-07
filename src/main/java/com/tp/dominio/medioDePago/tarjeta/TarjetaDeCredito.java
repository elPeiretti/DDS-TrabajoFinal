package com.tp.dominio.medioDePago.tarjeta;

import java.time.Instant;

import com.tp.dominio.medioDePago.MedioDePago;

public class TarjetaDeCredito extends MedioDePago {
	private String nroTarjeta;
	private Integer cvv;
	private Instant fechaVencimiento;
	private Integer cuotas;
	private TipoTarjeta tipo;
}
