package com.tp.dominio;

import java.time.Instant;

public class NotaDeCredito {
	private Instant fechaDeEmision;
	private TipoNotaDeCredito tipo;
	private String idNota;
	private Double monto;//mal
	private Double totalIVA;//mal
	private Pasajero pasajeroResponsable;
	private ResponsablePagoTercero terceroResponsable;
}
