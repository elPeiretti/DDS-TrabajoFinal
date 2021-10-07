package com.tp.dominio.medioDePago.tarjeta;

import java.time.Instant;

import com.tp.dominio.medioDePago.MedioDePago;


public class TarjetaDeDebito extends MedioDePago {
	private String nroTarjeta;
	private Instant fechaVencimiento;
	private Integer cvv;
	private TipoTarjeta tipo;
}
