package dominio.medioDePago.tarjeta;

import java.time.Instant;

import dominio.medioDePago.MedioDePago;

public class TarjetaDeDebito extends MedioDePago {
	private String nroTarjeta;
	private Instant fechaVencimiento;
	private Integer cvv;
	private TipoTarjeta tipo;
}
