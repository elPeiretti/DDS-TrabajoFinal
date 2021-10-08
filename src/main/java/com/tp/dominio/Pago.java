package com.tp.dominio;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.pago")

public class Pago {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_pago")
	private Integer idPago;
	@Column (name = "monto_acumulado")
	private Double montoAcumulado;//mal
	@Column (name = "vuelto")
	private Double vuelto;//mal
	@Column (name = "fecha_de_realizacion")
	private Instant fechaDeRealizacion;
	@OneToOne
	@JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
	private Factura factura;
}
