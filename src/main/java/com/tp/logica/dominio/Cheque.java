package com.tp.logica.dominio;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.cheque")

public class Cheque extends MedioDePago {
	@Column (name =  "nro_cheque")
	private String nroCheque;
	@Column (name =  "plaza")
	private String plaza;
	@Column (name =  "fecha_cobro")
	private LocalDate fechaCobro;
	@Enumerated(EnumType.STRING)
	@Column (name =  "estado")
	private EstadoCheque estado;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_banco", referencedColumnName = "id_banco")
	private Banco banco;
}
