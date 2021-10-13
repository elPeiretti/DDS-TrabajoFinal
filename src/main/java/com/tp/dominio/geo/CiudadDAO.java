package com.tp.dominio.geo;

import java.util.List;

public interface CiudadDAO {

	List<Ciudad> getByProvincia(Integer idProvincia);

}
