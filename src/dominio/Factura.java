package dominio;

import java.time.LocalDate;
import java.util.Collection;

public class Factura {
	private TipoFactura tipo;
	private Double importeTotal;//mal
	private LocalDate fechaDeEmision;
	private String idFactura;
	private Double totalIVA;
	private Pago pago;
	private ResponsablePagoTercero responsableTercero;
	private Pasajero responsablePasajero;
	private Collection<ItemFactura> items;
	
}
