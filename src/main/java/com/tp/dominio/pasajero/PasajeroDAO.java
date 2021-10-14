package com.tp.dominio.pasajero;

import java.util.List;
import java.util.Map;

public interface PasajeroDAO {

	public List<Pasajero> getPasajerosByCriteria(Map<String,Object> criterios, Integer li, Integer cant);
	public List<Pasajero> getPasajerosByCriteria(Map<String,Object> criterios);
	public Long getCountPasajerosByCriteria(Map<String,Object> criterios);
	public void insertarPasajero(Pasajero pasajero);
}
