package com.tp.dominio.pasajero;

import java.util.List;
import java.util.Map;

public interface PasajeroDAO {

	public List<Pasajero> getPasajerosByCriteria(Map<String,String> criterios, Integer li, Integer cant);
	
	public Long getCountPasajerosByCriteria(Map<String,String> criterios);
	
}
