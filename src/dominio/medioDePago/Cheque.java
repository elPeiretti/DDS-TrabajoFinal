package dominio.medioDePago;

import java.time.LocalDate;

public class Cheque extends MedioDePago {
	private String nroCheque;
	private String plaza;
	private LocalDate fechaCobro;
	private EstadoCheque estado;
	private Banco banco;
}
