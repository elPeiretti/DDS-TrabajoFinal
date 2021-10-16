package com.tp.dominio.pasajero;

import java.util.List;
import java.util.Map;

import com.tp.dto.BusqPasajeroDTO;

public interface PasajeroDAO {

	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant);
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios);
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios);
	public void insertarPasajero(Pasajero pasajero);
}
