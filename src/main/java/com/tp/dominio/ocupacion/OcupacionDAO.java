package com.tp.dominio.ocupacion;

import java.time.Instant;
import java.util.List;

public interface OcupacionDAO {

	List<Ocupacion> getOcupacionesInRange(Instant fecha_desde, Instant fecha_hasta);

}
