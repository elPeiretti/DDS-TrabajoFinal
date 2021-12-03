package com.tp.logica.dominio;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.tarjeta_de_debito")

public class TarjetaDeDebito extends MedioDePago {
	@Column (name = "nro_tarjeta")
	private String nroTarjeta;
	@Column (name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;
	@Column (name = "cvv")
	private String cvv;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_tipo_tarjeta", referencedColumnName = "id_tipo_tarjeta")
	private TipoTarjeta tipo;
}
