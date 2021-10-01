package dominio.medioDePago.tarjeta;

import java.time.LocalDate;

import dominio.medioDePago.MedioDePago;

public class TarjetaDeDebito extends MedioDePago {
	private String nroTarjeta;
	private LocalDate fechaVencimiento;
	private Integer cvv;
	private TipoTarjeta tipo;
}
