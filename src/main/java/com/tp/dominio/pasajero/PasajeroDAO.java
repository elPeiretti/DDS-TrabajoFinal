package com.tp.dominio.pasajero;

import java.util.List;

import com.tp.dto.BusqPasajeroDTO;
import com.tp.dto.PasajeroDTO;

public interface PasajeroDAO {

	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant);
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios);
	public List<Pasajero> getPasajerosByCriteria(BusqPasajeroDTO criterios, Integer li, Integer cant,
												PasajeroDTO responsable);
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios);
	public Long getCountPasajerosByCriteria(BusqPasajeroDTO criterios, PasajeroDTO responsable);
	
	public void insertarPasajero(Pasajero pasajero);
	
	
}
