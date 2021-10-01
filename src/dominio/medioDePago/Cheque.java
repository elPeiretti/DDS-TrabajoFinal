package dominio.medioDePago;

import java.time.Instant;

public class Cheque extends MedioDePago {
	private String nroCheque;
	private String plaza;
	private Instant fechaCobro;
	private EstadoCheque estado;
	private Banco banco;
}
