package com.tp.logica.dominio;

import java.time.LocalDate;
import java.util.List;

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
	private LocalDate fechaDeRealizacion;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
	private Factura factura;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name =  "id_pago", referencedColumnName = "id_pago")
	private List<MedioDePago> medio;
}
