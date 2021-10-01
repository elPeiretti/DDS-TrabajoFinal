package dominio;

import java.time.Instant;
import java.util.List;

public class Ocupacion {
	private Instant fechaIngreso;
	private Instant fechaEgreso;
	private Pasajero responsable;
	private List<Pasajero> acompaniantes;
}
