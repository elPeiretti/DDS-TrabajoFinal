package com.tp.dominio.geo;

import java.util.List;

public interface ProvinciaDAO {

	List<Provincia> getByPais(Integer idPais);

}
