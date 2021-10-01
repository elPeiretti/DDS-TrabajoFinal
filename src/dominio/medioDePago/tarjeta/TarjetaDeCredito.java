package dominio.medioDePago.tarjeta;

import java.time.LocalDate;

import dominio.medioDePago.MedioDePago;

public class TarjetaDeCredito extends MedioDePago {
	private String nroTarjeta;
	private Integer cvv;
	private LocalDate fechaVencimiento;
	private Integer cuotas;
	private TipoTarjeta tipo;
}
