package com.tp.logica.dominio;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tpdds.nota_de_credito")

public class NotaDeCredito {
	@Column (name = "fecha_de_emision")
	private LocalDate fechaDeEmision;
	@Enumerated(EnumType.STRING)
	@Column (name = "tipo_nota")
	private TipoNotaDeCredito tipo;
	@Id
	@GeneratedValue  (strategy = GenerationType.IDENTITY)
	@Column (name = "id_nota")
	private Integer idNota;
	@Column (name = "monto")
	private Double monto;//mal
	@Column (name = "total_iva")
	private Double totalIVA;//mal
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_pasajero", referencedColumnName = "id_pasajero")
	private Pasajero pasajeroResponsable;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_responsable_tercero", referencedColumnName = "id_responsable_tercero")
	private ResponsablePagoTercero terceroResponsable;
}
